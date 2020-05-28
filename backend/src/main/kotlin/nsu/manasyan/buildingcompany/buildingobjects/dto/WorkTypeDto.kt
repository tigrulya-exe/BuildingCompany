package nsu.manasyan.buildingcompany.buildingobjects.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkType

@NoArgConstructor
class WorkTypeDto(
    var name: String
) : Dto<WorkType>()