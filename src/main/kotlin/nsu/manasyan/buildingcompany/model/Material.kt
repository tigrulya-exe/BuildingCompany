package nsu.manasyan.buildingcompany.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Materials")
class Material(
    @Id @GeneratedValue
    var id: Int? = null,
    var name: String
)

