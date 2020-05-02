package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
class BuildingObject(var name: String) : Identifiable() {
    @ManyToOne
    @JoinTable(
        name = "CustomerBuildingObject",
        joinColumns = [JoinColumn(name = "BuildingObjectId")],
        inverseJoinColumns = [JoinColumn(name = "CustomerId")]
    )
    lateinit var customer: Customer

    @OneToMany(mappedBy = "buildingObject")
    lateinit var machinery: MutableSet<Machinery>
}