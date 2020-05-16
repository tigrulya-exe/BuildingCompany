package nsu.manasyan.buildingcompany.security

import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.services.UsersService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationProvider(
    private val jwtProvider: JwtProvider,
    private val usersService: UsersService
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication? {
        val jwtAuthentication = authentication as JwtAuthentication
        val jwt = jwtAuthentication.jwt
        val userId: Int = jwtProvider.getUserId(jwt).toInt()
        val user: User = usersService.getEntity(userId)

        if (!jwtProvider.validateToken(jwt, user)) {
            return null
        }

        jwtAuthentication.user = user
        return jwtAuthentication
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return JwtAuthentication::class.java == authentication
    }
}