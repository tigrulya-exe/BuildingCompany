package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.security.model.Token
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.repositories.TokenRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.convert.DurationUnit
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class TokenService(
    private val repository: TokenRepository,

    private val clock: Clock,

    @DurationUnit(ChronoUnit.DAYS)
    @Value("\${token.expiration}")
    private val duration: Duration
) {
    fun getUser(stringToken: String, type: Token.Type) : User{
        val token = repository
            .findByStringRepresentationAndType(stringToken, type)
            .orElseThrow{ throw IllegalArgumentException() }

        return token.user
    }

    fun isTokenExpired(token: Token): Boolean {
        return token.expirationDate.before(Date(clock.millis()))
    }
}