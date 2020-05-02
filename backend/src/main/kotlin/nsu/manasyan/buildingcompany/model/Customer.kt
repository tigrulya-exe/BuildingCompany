package nsu.manasyan.buildingcompany.model

import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "Customers")
class Customer(var name: String) : Identifiable() {
    @OneToMany(mappedBy = "customer")
    lateinit var buildingObjects: MutableSet<BuildingObject>
}