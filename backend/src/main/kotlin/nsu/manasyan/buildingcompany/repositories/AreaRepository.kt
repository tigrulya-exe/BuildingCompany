package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Area
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AreaRepository : JpaFilterRepository<Area, Int>{

    @Query("""
        select a   
        from Area a
        where (:#{#filter.managementId} is null or a.management.id = :#{#filter.managementId})
        and (:#{#filter.managerId} is null or a.manager.id = :#{#filter.managerId})
    """)
    override fun findAllByFilter(
        @Param("filter") filter: Filter<Area>?,
        pageable: Pageable): Page<Area>
}

@NoArgConstructor
data class AreaFilter(var managerId: Int?, var managementId: Int?) : Filter<Area>