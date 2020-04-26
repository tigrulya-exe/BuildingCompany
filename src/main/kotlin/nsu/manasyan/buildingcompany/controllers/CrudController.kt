package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.services.CrudService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application-path}")
class CrudController(val crudService: CrudService) {
    @GetMapping("/customers")
    fun getAllCustomers(params: FindRequestParameters?): MutableList<Customer> {
        return crudService.getAllCustomers(params)
    }
}