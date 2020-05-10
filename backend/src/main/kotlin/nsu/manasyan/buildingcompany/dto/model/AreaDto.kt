package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Area

@NoArgConstructor
data class AreaDto(var managementId: Int?, var managerId: Int?) : Dto<Area>()