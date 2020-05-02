package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Machinery

@NoArgConstructor
data class MachineryDto(
    var type: String,
    var licencePlateNumber: String,
    var buildingObjectId: Int?
) : Dto<Machinery>()