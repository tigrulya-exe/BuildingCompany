package nsu.manasyan.buildingcompany.customers.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.customers.model.Customer
import nsu.manasyan.buildingcompany.customers.repositories.CustomersRepository
import org.springframework.stereotype.Service

@Service
class CustomersService(repository: CustomersRepository) : AbstractCrudService<Customer>(repository)