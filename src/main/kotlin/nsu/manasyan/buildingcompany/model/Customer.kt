package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
@Table(name = "Customers")
class Customer(
    var name: String,
    @Id @GeneratedValue var id: Int? = null
){
    @OneToMany(mappedBy = "customer")
    lateinit var buildingObjects: MutableSet<BuildingObject>
}