package nsu.manasyan.buildingcompany.model

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "WorkTypes")
class WorkType(var name: String) : Identifiable()