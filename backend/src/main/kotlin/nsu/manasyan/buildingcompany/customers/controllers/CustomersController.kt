package nsu.manasyan.buildingcompany.customers.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.customers.mappers.CustomerMapper
import nsu.manasyan.buildingcompany.customers.dto.CustomerDto
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.customers.model.Customer
import nsu.manasyan.buildingcompany.customers.repositories.CustomerFilter
import nsu.manasyan.buildingcompany.customers.services.CustomersService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("\${application.path}/customers")
class CustomersController(customersService: CustomersService, mapper: CustomerMapper) :
    AbstractCrudController<Customer, CustomerDto>(customersService, mapper, "Customer") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: CustomerFilter, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}