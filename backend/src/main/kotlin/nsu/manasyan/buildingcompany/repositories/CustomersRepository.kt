package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomersRepository : JpaRepository<Customer, Int>