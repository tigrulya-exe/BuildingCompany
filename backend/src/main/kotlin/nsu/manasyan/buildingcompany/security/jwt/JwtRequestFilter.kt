package nsu.manasyan.buildingcompany.security.jwt

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val stringJwt = jwtProvider.getTokenFromRequest(request)
        stringJwt?.let {
            SecurityContextHolder.getContext().authentication =
                JwtAuthentication(it)
        }
        filterChain.doFilter(request, response)
    }
}
