package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.model.CustomerDto
import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.services.CustomersService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${application-path}")
class CustomersController(val customersService: CustomersService) {
    @GetMapping("/customers")
    fun getAllCustomers(params: FindRequestParameters?): MutableList<Dto<Customer>> {
        logger().info("All customers were fetched")
        return customersService.getAllEntities(params)
    }

    @PostMapping("/customers")
    fun addCustomer(@RequestBody customer: CustomerDto) {
        customersService.addEntity(customer)
        logger().info("Customer '${customer.name}' was added")
    }

    @DeleteMapping("/customers")
    fun deleteCustomer(@RequestParam id: Int) {
        customersService.deleteEntity(id)
        logger().info("Customer #'$id' was deleted")
    }

    @GetMapping("/customers/{id}")
    fun getCustomer(@PathVariable id: Int): Dto<Customer> {
        logger().info("Customer #'$id' was queried")
        return customersService.getEntity(id)
    }

    @PutMapping("/customers")
    fun updateCustomer(@RequestBody customer: CustomerDto) {
        customersService.updateEntity(customer)
        logger().info("Customer '${customer.name}' was updated")
    }
}