package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.model.Area

data class AreaDto(
    var managements: MutableSet<Int>? = null,
    var manager: TechnicalSpecialistDto? = null
) : Dto<Area>()