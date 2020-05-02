package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.ConstructionManagement

@NoArgConstructor
data class ConstructionManagementDto(
    var managerId: Int?,
    var areaIds: MutableSet<Int>
) : Dto<ConstructionManagement>()