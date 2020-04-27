package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.model.Customer

class CustomerDto(
    var name: String? = null,
    var buildingObjects: MutableList<Int>? = null
) : Dto<Customer>()