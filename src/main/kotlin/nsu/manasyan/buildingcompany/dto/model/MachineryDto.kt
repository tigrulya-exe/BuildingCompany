package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.ConstructionMachinery

@NoArgConstructor
data class MachineryDto(
    var type: String,
    var licencePlateNumber: String,
    var buildingObjectId: Int?
) : Dto<ConstructionMachinery>()