package nsu.manasyan.buildingcompany.buildingobjects.model

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "ResidentialHouses")
class ResidentialHouse(
    name: String,
    var flatCount: Int,
    var floorCount: Int
) : BuildingObject(name)