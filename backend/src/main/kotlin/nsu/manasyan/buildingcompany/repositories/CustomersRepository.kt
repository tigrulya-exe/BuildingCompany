package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@Repository
@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
interface CustomersRepository : JpaRepository<Customer, Int>