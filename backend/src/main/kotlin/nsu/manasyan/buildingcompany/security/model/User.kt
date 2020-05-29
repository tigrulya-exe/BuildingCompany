package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import javax.persistence.*

@Entity
@Table(name = "Users")
class User(
    var nickname: String,
    var email: String,
    var fullName: String,
    var password: String
) : Identifiable() {

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinTable(name = "UsersRoles")
    var roles: MutableSet<UserRole> = mutableSetOf()

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE], orphanRemoval = true)
    var tokens: MutableSet<Token> = mutableSetOf()
}