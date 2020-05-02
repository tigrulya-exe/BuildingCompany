package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Area

@NoArgConstructor
data class AreaDto(var managements: MutableSet<Int>?, var manager: TechnicalSpecialistDto?) : Dto<Area>()