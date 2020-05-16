package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.security.model.Credentials
import nsu.manasyan.buildingcompany.security.JwtProvider
import nsu.manasyan.buildingcompany.security.model.AuthorizationTokensDto
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.repositories.UserRepository
import nsu.manasyan.buildingcompany.services.AbstractCrudService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException


@Service
class UsersService(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider
) : AbstractCrudService<User>(userRepository){

    override fun addEntity(entity: User) {
        super.addEntity(entity)
    }

    fun authenticate(credentials: Credentials): AuthorizationTokensDto{
        val user = findUserByNickname(credentials.login)
        // TODO: добавить проверку на подтверждение почты
        if(!bCryptPasswordEncoder.matches(credentials.password, user.password)){
            throw IllegalArgumentException("Wrong credentials")
        }

        val jwt = jwtProvider.generateToken(user.id!!, 777)
        return AuthorizationTokensDto(jwt, "")
    }

    fun findUserByNickname(nickname: String) : User{
        return userRepository
            .findByNicknameIgnoreCase(nickname)
            .orElseThrow { throw IllegalArgumentException("Wrong credentials") }
    }

}