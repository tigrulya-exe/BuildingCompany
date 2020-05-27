package nsu.manasyan.buildingcompany.security.jwt

import nsu.manasyan.buildingcompany.security.model.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class JwtAuthentication(val jwt: String) : Authentication {
    private var isUserAuthenticated: Boolean = false

    lateinit var user: User

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.roles ?: mutableListOf()
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isUserAuthenticated = isAuthenticated
    }

    override fun getName() = user.nickname

    override fun getPrincipal() = user

    override fun isAuthenticated() = isUserAuthenticated

    override fun getCredentials() = null

    override fun getDetails() = null
}