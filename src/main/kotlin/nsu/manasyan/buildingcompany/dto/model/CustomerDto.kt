package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Customer

@NoArgConstructor
data class CustomerDto(var name: String, var buildingObjectIds: MutableSet<Int>?) : Dto<Customer>()