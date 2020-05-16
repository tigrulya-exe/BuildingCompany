package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.security.model.UserRole
import javax.persistence.Entity
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "Users")
class User(
    var nickname: String,
    var email: String,
    var fullName: String,
    var password: String
) : Identifiable(){

    @ManyToMany
    @JoinTable(name = "UsersRoles")
    var roles = mutableSetOf<UserRole>()
}