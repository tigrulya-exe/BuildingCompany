package nsu.manasyan.buildingcompany.workers.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.workers.model.Brigade
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BrigadeRepository :
    JpaFilterRepository<Brigade, Int> {
    @Query(
        """
        select b   
        from Brigade b
        where (:#{#filter.managerId} is null or b.manager.id = :#{#filter.managerId})
    """
    )
    override fun findAllByFilter(filter: Filter<in Brigade>?, pageable: Pageable): Page<Brigade>
}

@NoArgConstructor
data class BrigadeFilter(var managerId: Int?) : Filter<Brigade>