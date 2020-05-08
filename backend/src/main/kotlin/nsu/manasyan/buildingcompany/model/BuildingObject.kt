package nsu.manasyan.buildingcompany.model

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class BuildingObject(var name: String) : Identifiable() {
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    lateinit var customer: Customer

    @ManyToOne
    @JoinColumn(name = "areaId", referencedColumnName = "id")
    lateinit var area: Area

    @OneToMany(mappedBy = "buildingObject")
    lateinit var machinery: MutableSet<Machinery>
}