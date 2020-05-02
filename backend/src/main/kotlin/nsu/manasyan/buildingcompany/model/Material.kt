package nsu.manasyan.buildingcompany.model

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "Materials")
class Material(var name: String) : Identifiable()

