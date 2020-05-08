package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.ConstructionManagement
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ConstructionManagementsRepository : JpaFilterRepository<ConstructionManagement, Int>{
    @Query("""
        select c   
        from ConstructionManagement c
        where (:#{#filter.managerId} is null or c.manager.id = :#{#filter.managerId})
    """)
    override fun findAllByFilter(@Param("filter") filter: Filter<ConstructionManagement>?,
                                 pageable: Pageable): Page<ConstructionManagement>
}

@NoArgConstructor
data class ConstructionManagementFilter(var managerId: Int?) : Filter<ConstructionManagement>