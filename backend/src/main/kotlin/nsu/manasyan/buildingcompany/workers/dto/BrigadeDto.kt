package nsu.manasyan.buildingcompany.workers.dto

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.workers.model.Brigade

@NoArgConstructor
data class BrigadeDto(
    var managerId: Int?,
    var workerIds: MutableSet<Int>?
) : Dto<Brigade>()
