package nsu.manasyan.buildingcompany.util

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.util.filters.Filter

@NoArgConstructor
data class TechnicalSpecialistFilter(
    var areaId: Int?,
    var managementId: Int?,
    var name: String?) : Filter<TechnicalSpecialist>