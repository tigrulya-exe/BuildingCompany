package nsu.manasyan.buildingcompany.abstracts.dto

import nsu.manasyan.buildingcompany.NoArgConstructor

@NoArgConstructor
class PageDto<D>(
    var content: List<D>,
    var totalElements: Int,
    var pageNumber: Int
)