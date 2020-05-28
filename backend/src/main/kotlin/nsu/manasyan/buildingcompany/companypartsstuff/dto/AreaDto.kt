package nsu.manasyan.buildingcompany.companypartsstuff.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.companypartsstuff.model.Area

@NoArgConstructor
data class AreaDto(var managementId: Int?, var managerId: Int?) : Dto<Area>()