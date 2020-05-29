package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "Roles")
class UserRole(
    @Enumerated(EnumType.STRING)
    var role: Role
) : GrantedAuthority, Identifiable() {
    enum class Role {
        UNCONFIRMED,
        DEFAULT,
        ADMIN
    }

    @ManyToMany(mappedBy = "roles")
    var users = mutableSetOf<User>()

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RolesPermissions")
    var permissions = mutableSetOf<Permission>()

    override fun getAuthority() = role.name
}

@Entity
@Table(name = "Permissions")
class Permission(var stringRepresentation: String) : GrantedAuthority, Identifiable() {
    override fun getAuthority() = stringRepresentation

    @ManyToMany(mappedBy = "permissions")
    var roles = mutableSetOf<UserRole>()
}