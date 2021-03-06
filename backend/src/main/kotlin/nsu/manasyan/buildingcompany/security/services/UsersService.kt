package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.security.dto.AuthorizationTokensDto
import nsu.manasyan.buildingcompany.security.events.PasswordRestoreEvent
import nsu.manasyan.buildingcompany.security.events.RegistrationCompleteEvent
import nsu.manasyan.buildingcompany.security.jwt.JwtProvider
import nsu.manasyan.buildingcompany.security.model.Credentials
import nsu.manasyan.buildingcompany.security.model.Token
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.repositories.RoleRepository
import nsu.manasyan.buildingcompany.security.repositories.UserRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsersService(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val roleRepository: RoleRepository,
    private val rolesService: RolesService,
    private val eventPublisher: ApplicationEventPublisher,
    private val tokenService: TokenService,
    private val refreshTokenService: RefreshTokenService
) : AbstractCrudService<User>(userRepository) {

    fun signUp(user: User) {
        checkUniqueParams(user)
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.roles = mutableSetOf()
        user.roles.add(roleRepository.findByRole("UNCONFIRMED"))

        logger().info("User ${user.nickname} signed up")
        eventPublisher.publishEvent(RegistrationCompleteEvent(userRepository.save(user)))
    }

    override fun addEntity(entity: User) {
        entity.password = bCryptPasswordEncoder.encode(entity.password)
        super.addEntity(entity)
    }

    override fun updateEntity(entity: User) {
        val dbInstance = getEntity(entity.id!!)
        entity.roles = dbInstance.roles
        entity.tokens = dbInstance.tokens
        if (dbInstance.password != entity.password) {
            entity.password = bCryptPasswordEncoder.encode(entity.password)
        }
        super.updateEntity(entity)
    }

    @Transactional
    fun authenticate(credentials: Credentials): AuthorizationTokensDto {
        val user = getUserByNickname(credentials.login)

        if (!isConfirmed(user)) {
            throw IllegalArgumentException("Unconfirmed")
        }

        if (!bCryptPasswordEncoder.matches(credentials.password, user.password)) {
            throw IllegalArgumentException("Wrong credentials")
        }
        logger().info("User ${user.nickname} logged in")
        val refreshToken = refreshTokenService.generateToken(user)
        val jwt = jwtProvider.generateToken(user.id!!, refreshToken.id!!)
        return AuthorizationTokensDto(
            jwt,
            refreshToken.stringRepresentation
        )
    }

    @Transactional
    fun updateTokens(refreshTokenString: String): AuthorizationTokensDto {
        val newRefresh = refreshTokenService.updateToken(refreshTokenString)
        val user = newRefresh.user
        val jwt = jwtProvider.generateToken(user.id!!, newRefresh.id!!)
        logger().info("User ${user.nickname} refreshed tokens")
        return AuthorizationTokensDto(
            jwt,
            newRefresh.stringRepresentation
        )
    }

    fun restorePassword(email: String) {
        val user = getUserByEmail(email)
        logger().info("User $email sent request to restore password")
        eventPublisher.publishEvent(PasswordRestoreEvent(user))
    }

    fun validateRestoreToken(token: String) {
        tokenService.validateToken(token, Token.Type.PASSWORD_RESTORE)
        logger().info("Restore token $token was validated")
    }

    fun getUserByNickname(nickname: String): User {
        return userRepository
            .findByNicknameIgnoreCase(nickname)
            .orElseThrow { throw IllegalArgumentException("Wrong credentials") }
    }

    fun getUserByEmail(email: String): User {
        return userRepository
            .findByEmailIgnoreCase(email)
            .orElseThrow { throw IllegalArgumentException("Wrong credentials") }
    }

    @Transactional
    fun changePassword(stringToken: String, newPassword: String) {
        val token = tokenService.validateToken(stringToken, Token.Type.PASSWORD_RESTORE)
        val user = tokenService.getUser(stringToken, Token.Type.PASSWORD_RESTORE)

        user.password = bCryptPasswordEncoder.encode(newPassword)
        tokenService.removeToken(token.id!!)
        logger().info("User ${user.nickname} changed password")
    }

    @Transactional
    fun confirmEmail(stringToken: String) {
        val token = tokenService.validateToken(stringToken, Token.Type.EMAIL_CONFIRM)
        val user = tokenService.getUser(stringToken, Token.Type.EMAIL_CONFIRM)
        if (isConfirmed(user)) {
            throw IllegalArgumentException("User already confirmed")
        }

        val unconfirmedRole = roleRepository.findByRole("UNCONFIRMED")
        val defaultRole = roleRepository.findByRole("DEFAULT")
        user.roles.remove(unconfirmedRole)
        user.roles.add(defaultRole)

        tokenService.removeToken(token.id!!)
        logger().info("User ${user.nickname} confirmed his email ${user.email}")
    }

    private fun isConfirmed(user: User): Boolean {
        return !user.roles.map { r -> r.role }.contains("UNCONFIRMED")
    }

    private fun checkUniqueParams(user: User) {
        if (userRepository.findByEmailIgnoreCase(user.email).isPresent) {
            throw IllegalArgumentException("User with such email already exists")
        }

        if (userRepository.findByNicknameIgnoreCase(user.nickname).isPresent) {
            throw IllegalArgumentException("User with such nickname already exists")
        }
    }

    fun getProfile(): User {
        return getCurrentUser()
    }

    fun getCurrentUser(): User {
        return userRepository
            .findCurrentUser()
            .orElseThrow { IllegalArgumentException("Unauthorized") }
    }

    @Transactional
    fun addRoles(ids: List<Int>, userId: Int) {
        val roles = ids.map { rolesService.getEntity(it) }
        val user = getEntity(userId)
        user.roles.addAll(roles)
        userRepository.save(user)
    }

    @Transactional
    fun removeRole(userId: Int, roleId: Int) {
        val user = getEntity(userId)
        user.roles.removeIf { it.id == roleId }
    }
}