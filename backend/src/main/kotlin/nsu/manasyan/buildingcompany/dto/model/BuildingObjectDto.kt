package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.BuildingObject

@NoArgConstructor
class BuildingObjectDto(
    var name: String,
    var customerId: Int,
    var machineryIds: MutableSet<Int>?
) : Dto<BuildingObject>()