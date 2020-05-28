package nsu.manasyan.buildingcompany.buildingobjects.model

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "WorkTypes")
class WorkType(var name: String) : Identifiable(){
    @ManyToMany(mappedBy = "workTypes")
    var buildingObjects = mutableSetOf<BuildingObject>()
}