package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.services.CrudService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${application-path}")
class CrudController(val crudService: CrudService) {
    @GetMapping("/customers")
    fun getAllCustomers(params: FindRequestParameters?): MutableList<Customer> {
        return crudService.getAllCustomers(params)
    }

    @PostMapping("/customers")
    fun addCustomer(@RequestBody customer: Customer) {
        crudService.addCustomer(customer)
    }

    @DeleteMapping("/customers")
    fun deleteCustomer(@RequestParam id: Int) {
        crudService.deleteCustomer(id)
    }
}