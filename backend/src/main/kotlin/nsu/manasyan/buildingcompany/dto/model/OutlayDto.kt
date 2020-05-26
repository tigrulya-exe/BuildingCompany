package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Outlay

@NoArgConstructor
data class OutlayDto(
    var materialCount: Int,
    var materialId: Int,
    var buildingObjectId: Int
) : Dto<Outlay>()