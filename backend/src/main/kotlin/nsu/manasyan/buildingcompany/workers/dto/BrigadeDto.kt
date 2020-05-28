package nsu.manasyan.buildingcompany.workers.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.workers.model.Brigade

@NoArgConstructor
data class BrigadeDto(
    var managerId: Int?,
    var constructionManagementId: Int,
    var workerIds: MutableSet<Int>?
) : Dto<Brigade>()
