package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.repositories.CustomersRepository
import org.springframework.stereotype.Service

@Service
class CrudService(val customersRepository: CustomersRepository) {
    fun getAllCustomers(): MutableList<Customer> = customersRepository.findAll()
}