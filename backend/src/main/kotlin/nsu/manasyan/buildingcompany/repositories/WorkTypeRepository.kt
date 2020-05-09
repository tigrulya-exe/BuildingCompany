package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.WorkType
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface WorkTypeRepository : JpaFilterRepository<WorkType, Int> {
    @Query(
        """
        select w   
        from WorkType w
        where (:#{#filter.name} is null or lower(w.name) like :#{#filter.name})
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<WorkType>?,
        pageable: Pageable
    ): Page<WorkType>
}

class WorkTypeFilter : Filter<WorkType> {
    var name: String? by FilterStringDelegate()
}