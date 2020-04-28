package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.model.Customer


class CustomerDto(
    var name: String? = null,
    var buildingObjects: MutableSet<Int>? = null
) : Dto<Customer>()