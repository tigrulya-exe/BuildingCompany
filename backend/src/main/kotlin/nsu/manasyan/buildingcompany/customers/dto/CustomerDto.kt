package nsu.manasyan.buildingcompany.customers.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.customers.model.Customer

@NoArgConstructor
data class CustomerDto(var name: String, var buildingObjectIds: MutableSet<Int>?) : Dto<Customer>()