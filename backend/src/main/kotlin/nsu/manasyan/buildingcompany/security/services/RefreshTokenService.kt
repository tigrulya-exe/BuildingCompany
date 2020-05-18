package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.security.model.Token
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.repositories.TokenRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.convert.DurationUnit
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import java.time.Clock
import java.time.Duration
import java.time.temporal.ChronoUnit

@Service
class RefreshTokenService(
    repository: TokenRepository,
    clock: Clock,
    @DurationUnit(ChronoUnit.DAYS)
    @Value("\${refresh-token.expiration}")
    private val duration: Duration
) : TokenService(repository, clock, duration) {

    @Transactional
    fun generateToken(user: User): Token {
        repository.deleteByUserAndType(user, Token.Type.REFRESH)
        return generateToken(user, Token.Type.REFRESH)
    }

    @Transactional
    fun updateToken(stringToken: String): Token {
        val token = getTokenByStringAndType(stringToken, Token.Type.REFRESH)
        validateToken(stringToken, Token.Type.REFRESH)
        return generateToken(token.user)
    }

    fun getUser(stringToken: String): User{
        return super.getUser(stringToken, Token.Type.REFRESH)
    }

    fun validateById(tokenId: Int){
        val token = repository
            .findById(tokenId)
            .orElseThrow{ throw IllegalArgumentException("Wrong refresh") }
        validateToken(token.stringRepresentation, Token.Type.REFRESH)
    }
}