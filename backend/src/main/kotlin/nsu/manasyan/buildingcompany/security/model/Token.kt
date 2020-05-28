package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Tokens")
class Token(
    var stringRepresentation: String,
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    var user: User,
    @Temporal(TemporalType.TIMESTAMP)
    var expirationDate: Date
) : Identifiable() {
    enum class Type {
        REFRESH,
        PASSWORD_RESTORE,
        EMAIL_CONFIRM
    }

    @Enumerated(EnumType.STRING)
    lateinit var type: Type
}