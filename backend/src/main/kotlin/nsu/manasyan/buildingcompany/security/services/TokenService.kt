package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.security.model.Token
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.repositories.TokenRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.convert.DurationUnit
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Clock
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class TokenService(
    protected val repository: TokenRepository,

    private val clock: Clock,

    @DurationUnit(ChronoUnit.DAYS)
    @Value("\${token.expiration}")
    private val duration: Duration
) {
    fun getUser(stringToken: String, type: Token.Type) : User{
        val token = getTokenByStringAndType(stringToken, type)
        return token.user
    }

    fun isTokenExpired(token: Token): Boolean {
        return token.expirationDate.before(Date(clock.millis()))
    }

    fun validateToken(stringToken: String, type: Token.Type): Token{
        val token = getTokenByStringAndType(stringToken, type)
        if(isTokenExpired(token)){
            throw IllegalArgumentException("Wrong token")
        }

        return token
    }

    fun removeToken(id: Int){
        repository.deleteById(id)
    }

    @Transactional
    fun generateToken(user: User, type: Token.Type): Token{
        val stringToken = UUID.randomUUID().toString()
        val expirationDate = Date(clock.millis() + duration.toMillis())
        val token = Token(stringToken, user, expirationDate)
        token.type = type

        return repository.save(token)
    }

    protected fun getTokenByStringAndType(stringToken: String, type: Token.Type): Token {
        return repository
            .findByStringRepresentationAndType(stringToken, type)
            .orElseThrow{ throw java.lang.IllegalArgumentException("Wrong token") }
    }
}