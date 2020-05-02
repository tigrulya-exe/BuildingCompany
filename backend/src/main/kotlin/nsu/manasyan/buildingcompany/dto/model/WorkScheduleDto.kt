package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.WorkSchedule
import java.util.*

@NoArgConstructor
data class WorkScheduleDto(
    var startDate: Date,
    var endDate: Date,
    var buildingObjectId: Int,
    var workTypeId: Int,
    var brigadeId: Int
) : Dto<WorkSchedule>()