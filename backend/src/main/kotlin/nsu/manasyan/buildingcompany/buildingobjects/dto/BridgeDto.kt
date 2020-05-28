package nsu.manasyan.buildingcompany.buildingobjects.dto

import nsu.manasyan.buildingcompany.NoArgConstructor

@NoArgConstructor
class BridgeDto(
    name: String,
    customerId: Int?,
    areaId: Int,
    var widthInMetres: Float,
    var typeOfSpan: String,
    var numberOfTrafficLanes: Int
) : BuildingObjectDto(name, customerId, areaId)