package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.workers.Brigade

@NoArgConstructor
data class BrigadeDto(
    var managerId: Int?,
    var workerIds: MutableSet<Int>?
) : Dto<Brigade>()
