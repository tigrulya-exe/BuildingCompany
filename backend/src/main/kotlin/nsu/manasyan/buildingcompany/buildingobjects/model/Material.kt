package nsu.manasyan.buildingcompany.buildingobjects.model

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "Materials")
class Material(var name: String) : Identifiable()

