package nsu.manasyan.buildingcompany.model

import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import javax.persistence.*

@Entity
@Table(name = "Machinery")
class Machinery(var type: String, var licencePlateNumber: String) : Identifiable() {
    @ManyToOne
    @JoinColumn(name = "BuildingObjectId", referencedColumnName = "id")
    var buildingObject: BuildingObject? = null
}