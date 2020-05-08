package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.CustomerMapper
import nsu.manasyan.buildingcompany.dto.model.CustomerDto
import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.services.CustomersService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("\${application.path}/customers")
class CustomersController(customersService: CustomersService, mapper: CustomerMapper) :
    AbstractCrudController<Customer, CustomerDto>(customersService, mapper, "Customer")