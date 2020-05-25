package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
class BuildingObject(var name: String) : Identifiable() {
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    var customer: Customer? = null

    @ManyToOne
    @JoinColumn(name = "areaId", referencedColumnName = "id")
    lateinit var area: Area

    @ManyToMany
    @JoinTable(name = "BuildingObjectWorkTypes")
    var workTypes = mutableSetOf<WorkType>()

    @OneToMany(mappedBy = "buildingObject")
    var machinery = mutableSetOf<Machinery>()
}