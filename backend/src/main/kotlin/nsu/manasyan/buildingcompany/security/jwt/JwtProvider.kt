package nsu.manasyan.buildingcompany.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import nsu.manasyan.buildingcompany.security.AuthorizationTokenProvider
import nsu.manasyan.buildingcompany.security.model.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.convert.DurationUnit
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtProvider(
    private val clock: Clock,

    @Value("\${jwt.secret}")
    private val secret: String,

    @DurationUnit(ChronoUnit.HOURS)
    @Value("\${jwt.expiration}")
    private var duration: Duration

) : AuthorizationTokenProvider {
    companion object {
        private const val AUTH_HEADER = "Authorization"

        private const val BEARER_PREFIX = "Bearer "
    }

    override fun getUserId(token: String): String {
        return getAllClaims(token).subject
    }

    fun getRefreshTokenId(token: String): String? {
        return getAllClaims(token).id
    }

    override fun getExpirationDate(token: String): Date {
        return getAllClaims(token).expiration
    }

    fun getTokenFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTH_HEADER)
        return if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken.substring(BEARER_PREFIX.length)
        } else null
    }

    private fun getAllClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
    }

    fun isTokenExpired(token: String): Boolean {
        return getExpirationDate(token).before(Date(clock.millis()))
    }

    override fun generateToken(userId: Int, refreshTokenId: Int): String {
        val claims: Map<String, Any> = HashMap()
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userId.toString())
            .setId(refreshTokenId.toString())
            .setIssuedAt(Date(clock.millis()))
            .setExpiration(Date(clock.millis() + duration.toMillis()))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    override fun validateToken(token: String, user: User): Boolean {
        return !isTokenExpired(token)
    }
}