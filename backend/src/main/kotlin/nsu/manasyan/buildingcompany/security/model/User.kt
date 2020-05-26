package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.model.Identifiable
import javax.persistence.*

@Entity
@Table(name = "Users")
class User(
    var nickname: String,
    var email: String,
    var fullName: String,
    var password: String
) : Identifiable() {

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    @JoinTable(name = "UsersRoles")
    var roles: MutableSet<UserRole> = mutableSetOf()
}