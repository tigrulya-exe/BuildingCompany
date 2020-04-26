package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
@Table(name = "Machinery")
class ConstructionMachinery(var type: String, var licencePlateNumber: String) : Identifiable() {
    @ManyToOne
    @JoinTable(
        name = "BuildingObjectMachinery",
        joinColumns = [JoinColumn(name = "MachineId")],
        inverseJoinColumns = [JoinColumn(name = "ObjectId")]
    )
    lateinit var buildingObject: BuildingObject
}