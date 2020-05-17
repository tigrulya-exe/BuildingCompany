package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.security.jwt.JwtProvider
import nsu.manasyan.buildingcompany.security.events.RegistrationCompleteEvent
import nsu.manasyan.buildingcompany.security.model.*
import nsu.manasyan.buildingcompany.security.repositories.RoleRepository
import nsu.manasyan.buildingcompany.security.repositories.UserRepository
import nsu.manasyan.buildingcompany.services.AbstractCrudService
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
    private val eventPublisher: ApplicationEventPublisher,
    private val tokenService: TokenService
) : AbstractCrudService<User>(userRepository) {

//    @Transactional
    fun signUp(user: User) {
        checkUniqueParams(user)
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.roles.add(roleRepository.findByRole(UserRole.Role.UNCONFIRMED))

        logger().info("User ${user.nickname} signed up")
        val t = userRepository.save(user)
        eventPublisher.publishEvent(RegistrationCompleteEvent(t))
//        eventPublisher.publishEvent(RegistrationCompleteEvent(userRepository.save(user)))

    }

    fun authenticate(credentials: Credentials): AuthorizationTokensDto {
        val user = findUserByNickname(credentials.login)

        if (!isConfirmed(user)) {
            throw IllegalArgumentException("Unconfirmed")
        }

        if (!bCryptPasswordEncoder.matches(credentials.password, user.password)) {
            throw IllegalArgumentException("Wrong credentials")
        }
        logger().info("User ${user.nickname} logged in")
        val jwt = jwtProvider.generateToken(user.id!!, 777)
        return AuthorizationTokensDto(jwt, "")
    }

    fun findUserByNickname(nickname: String): User {
        return userRepository
            .findByNicknameIgnoreCase(nickname)
            .orElseThrow { throw IllegalArgumentException("Wrong credentials") }
    }

    @Transactional
    fun confirmEmail(token: String) {
        val user = tokenService.getUser(token, Token.Type.EMAIL_CONFIRM)
        if(isConfirmed(user)){
            throw IllegalArgumentException("User already confirmed")
        }

        val unconfirmedRole = roleRepository.findByRole(UserRole.Role.UNCONFIRMED)
        val defaultRole = roleRepository.findByRole(UserRole.Role.DEFAULT)
        user.roles.remove(unconfirmedRole)
        user.roles.add(defaultRole)
        logger().info("User ${user.nickname} confirmed his email ${user.email}")
    }

    private fun isConfirmed(user: User): Boolean {
        return !user.roles.map { r -> r.role }.contains(UserRole.Role.UNCONFIRMED)
    }

    private fun checkUniqueParams(user: User) {
        if (userRepository.findByEmailIgnoreCase(user.email).isPresent) {
            throw IllegalArgumentException("User with such email already exists")
        }

        if (userRepository.findByNicknameIgnoreCase(user.nickname).isPresent) {
            throw IllegalArgumentException("User with such nickname already exists")
        }
    }
}