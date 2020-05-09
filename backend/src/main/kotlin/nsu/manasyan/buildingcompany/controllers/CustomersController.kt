package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.CustomerMapper
import nsu.manasyan.buildingcompany.dto.model.CustomerDto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.repositories.AreaFilter
import nsu.manasyan.buildingcompany.repositories.CustomerFilter
import nsu.manasyan.buildingcompany.services.CustomersService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("\${application.path}/customers")
class CustomersController(customersService: CustomersService, mapper: CustomerMapper) :
    AbstractCrudController<Customer, CustomerDto>(customersService, mapper, "Customer"){

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(@RequestParam filter: CustomerFilter?, params: FindRequestParameters?) : PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}