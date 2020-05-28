package nsu.manasyan.buildingcompany.buildingobjects.dto

import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkSchedule
import java.util.*

@NoArgConstructor
data class WorkScheduleDto(
    var startDate: Date,
    var endDate: Date,
    var buildingObjectId: Int,
    var workType: String,
    var brigadeId: Int
) : Dto<WorkSchedule>()