package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.repositories.CustomersRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.findAllEntities
import org.springframework.stereotype.Service

@Service
class CrudService(val customersRepository: CustomersRepository) {
    fun getAllCustomers(parameters: FindRequestParameters?): MutableList<Customer> {
        logger().info("All customers were fetched")
        return findAllEntities(customersRepository, parameters)
    }

    fun addCustomer(customer: Customer) {
        customersRepository.save(customer)
        logger().info("Customer '${customer.name}' was added")
    }

    fun deleteCustomer(id: Int) {
        customersRepository.deleteById(id)
        logger().info("Customer #'$id' was deleted")
    }
}