package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.dto.mappers.CustomerMapper
import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.repositories.CustomersRepository
import org.springframework.stereotype.Service

@Service
class CustomersService(repository: CustomersRepository, mapper: CustomerMapper) :
    AbstractService<Customer>(repository, mapper) {
}