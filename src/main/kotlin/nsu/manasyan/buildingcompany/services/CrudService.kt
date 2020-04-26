package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.repositories.CustomersRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.findAllEntities
import org.springframework.stereotype.Service

@Service
class CrudService(val customersRepository: CustomersRepository) {
    fun getAllCustomers(parameters: FindRequestParameters?) = findAllEntities(customersRepository, parameters)
}