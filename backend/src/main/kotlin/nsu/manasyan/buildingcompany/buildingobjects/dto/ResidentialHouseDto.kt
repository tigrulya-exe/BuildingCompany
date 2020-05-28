package nsu.manasyan.buildingcompany.buildingobjects.dto

import nsu.manasyan.buildingcompany.NoArgConstructor

@NoArgConstructor
class ResidentialHouseDto(
    name: String,
    customerId: Int?,
    areaId: Int,
    var flatCount: Int,
    var floorCount: Int
) : BuildingObjectDto(name, customerId, areaId)