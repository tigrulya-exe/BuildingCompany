package nsu.manasyan.buildingcompany.buildingobjects.model

import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "Bridges")
class Bridge(
    name: String,
    var widthInMetres: Float,
    var typeOfSpan: String,
    var numberOfTrafficLanes: Int
) : BuildingObject(name)