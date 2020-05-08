package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.workers.Brigade
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BrigadeRepository : JpaFilterRepository<Brigade, Int>{
    @Query("""
        select b   
        from Brigade b
        where (:#{#filter.managerId} is null or b.manager.id = :#{#filter.managerId})
    """)
    override fun findAllByFilter(filter: Filter<Brigade>?, pageable: Pageable): Page<Brigade>
}

@NoArgConstructor
data class BrigadeFilter(var managerId: Int?) : Filter<Brigade>