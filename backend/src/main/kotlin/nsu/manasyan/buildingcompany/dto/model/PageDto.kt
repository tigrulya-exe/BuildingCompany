package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor

@NoArgConstructor
class PageDto<D>(
    var content: List<D>,
    var totalElements: Int,
    var totalPages: Int
)