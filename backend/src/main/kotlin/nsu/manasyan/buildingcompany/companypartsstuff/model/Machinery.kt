package nsu.manasyan.buildingcompany.companypartsstuff.model

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "Machinery")
class Machinery(var type: String, var licencePlateNumber: String) : Identifiable() {
    @ManyToOne
    @JoinColumn(name = "BuildingObjectId", referencedColumnName = "id")
    var buildingObject: BuildingObject? = null
}