package nsu.manasyan.buildingcompany.buildingobjects.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.buildingobjects.model.Outlay

@NoArgConstructor
data class OutlayDto(
    var materialCount: Int,
    var materialId: Int,
    var buildingObjectId: Int
) : Dto<Outlay>()