package nsu.manasyan.buildingcompany.customers.model

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "Customers")
class Customer(var name: String) : Identifiable() {
    @OneToMany(mappedBy = "customer")
    lateinit var buildingObjects: MutableSet<BuildingObject>
}