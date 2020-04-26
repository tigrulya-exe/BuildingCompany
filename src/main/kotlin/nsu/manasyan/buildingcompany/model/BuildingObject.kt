package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
class BuildingObject(
    var name: String,
    @ManyToOne
    @JoinTable(
        name = "CustomerBuildingObject",
        joinColumns = [JoinColumn(name = "BuildingObjectId")],
        inverseJoinColumns = [JoinColumn(name = "CustomerId")]
    )
    var customer: Customer,
    @Id @GeneratedValue
    var id: Int? = null
)