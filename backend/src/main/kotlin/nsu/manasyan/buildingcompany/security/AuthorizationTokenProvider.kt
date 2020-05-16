package nsu.manasyan.buildingcompany.security

import nsu.manasyan.buildingcompany.security.model.User
import org.springframework.stereotype.Component
import java.util.*

@Component
interface AuthorizationTokenProvider {
    fun generateToken(tokenId: Int, refreshTokenId: Int): String?

    fun validateToken(token: String, user: User): Boolean

    fun getExpirationDate(token: String): Date?

    fun getUserId(token: String): String
}