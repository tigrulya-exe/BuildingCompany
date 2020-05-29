package nsu.manasyan.buildingcompany.abstracts.dto

import nsu.manasyan.buildingcompany.NoArgConstructor

@NoArgConstructor
class NativeQueryResultsDto(
    var result: MutableList<*>,
    var totalCount: Int
)