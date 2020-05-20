package nsu.manasyan.buildingcompany.security

import nsu.manasyan.buildingcompany.security.jwt.JwtAuthenticationProvider
import nsu.manasyan.buildingcompany.security.jwt.JwtRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter

@Configuration
class SecurityConfig(
    private val jwtAuthenticationProvider: JwtAuthenticationProvider,
    private val jwtRequestFilter: JwtRequestFilter,
    private val exceptionHandlerFilter: ExceptionHandlerFilter
) : WebSecurityConfigurerAdapter() {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(jwtAuthenticationProvider)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
//        http.logout().disable();
        http.httpBasic().disable()
        http.addFilterBefore(jwtRequestFilter, AbstractPreAuthenticatedProcessingFilter::class.java)
        http.addFilterBefore(exceptionHandlerFilter, JwtRequestFilter::class.java)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.anonymous()
            .and().authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers("/api/**").hasAuthority("DEFAULT")
            .antMatchers("/api/v1/users/**").hasAuthority("ADMIN")
    }
}