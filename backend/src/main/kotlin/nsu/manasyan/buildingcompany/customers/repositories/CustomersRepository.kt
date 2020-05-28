package nsu.manasyan.buildingcompany.customers.repositories

import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.customers.model.Customer
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CustomersRepository :
    JpaFilterRepository<Customer, Int> {
    @Query(
        """
        select c   
        from Customer c
        where (:#{#filter.name} is null or lower(c.name) like :#{#filter.name})
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<in Customer>?,
        pageable: Pageable
    ): Page<Customer>
}

class CustomerFilter : Filter<Customer> {
    var name: String? by FilterStringDelegate()
}