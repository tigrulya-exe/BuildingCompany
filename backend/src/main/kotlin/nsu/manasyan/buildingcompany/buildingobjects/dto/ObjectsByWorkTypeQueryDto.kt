package nsu.manasyan.buildingcompany.buildingobjects.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import java.util.*

@NoArgConstructor
class ObjectsByWorkTypeQueryDto(
    var workTypeIds: List<Int>,
    var managementIds: List<Int>,
    var startDateMin: Date?,
    var startDateMax: Date?
)