package nsu.manasyan.buildingcompany.companypartsstuff.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.companypartsstuff.model.ConstructionManagement

@NoArgConstructor
data class ConstructionManagementDto(
    var managerId: Int?,
    var areaIds: MutableSet<Int>?
) : Dto<ConstructionManagement>()