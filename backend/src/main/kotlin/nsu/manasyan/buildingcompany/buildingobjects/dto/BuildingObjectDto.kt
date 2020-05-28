package nsu.manasyan.buildingcompany.buildingobjects.dto

import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto

@NoArgConstructor
open class BuildingObjectDto(
    var name: String,
    var customerId: Int?,
    var areaId: Int
) : Dto<BuildingObject>()