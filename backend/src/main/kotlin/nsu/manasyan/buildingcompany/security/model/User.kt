package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.security.model.UserRole
import javax.persistence.*

@Entity
@Table(name = "Users")
class User(
    var nickname: String,
    var email: String,
    var fullName: String,
    var password: String
) : Identifiable(){

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UsersRoles")
    var roles : MutableSet<UserRole> = mutableSetOf()
}