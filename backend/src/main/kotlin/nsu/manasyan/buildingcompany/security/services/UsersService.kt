package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.security.JwtProvider
import nsu.manasyan.buildingcompany.security.model.AuthorizationTokensDto
import nsu.manasyan.buildingcompany.security.model.Credentials
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.model.UserRole
import nsu.manasyan.buildingcompany.security.repositories.RoleRepository
import nsu.manasyan.buildingcompany.security.repositories.UserRepository
import nsu.manasyan.buildingcompany.services.AbstractCrudService
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsersService(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val roleRepository: RoleRepository,
    private val eventMulticaster: ApplicationEventMulticaster,
    private val tokenService: TokenService
) : AbstractCrudService<User>(userRepository) {

    @Transactional
    override fun addEntity(entity: User) {
        checkUniqueParams(entity)
        entity.password = bCryptPasswordEncoder.encode(entity.password)
        entity.roles.add(roleRepository.findByRole(UserRole.Role.UNCONFIRMED))
        logger().info("User ${entity.nickname} signed up")
        super.addEntity(entity)
    }

    fun authenticate(credentials: Credentials): AuthorizationTokensDto {
        val user = findUserByNickname(credentials.login)
        // TODO: добавить проверку на подтверждение почты
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

    private fun checkUniqueParams(user: User) {
        if (userRepository.findByEmailIgnoreCase(user.email).isPresent) {
            throw IllegalArgumentException("User with such email already exists")
        }

        if (userRepository.findByNicknameIgnoreCase(user.nickname).isPresent) {
            throw IllegalArgumentException("User with such nickname already exists")
        }
    }
}