package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.workers.Foreman
import nsu.manasyan.buildingcompany.model.workers.Master
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.util.TechnicalSpecialistFilter
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TechnicalSpecialistsRepository : JpaFilterRepository<TechnicalSpecialist, Int>{

    @Query("""
        select ts   
        from TechnicalSpecialist ts
        where (:#{#filter.areaId} = ts.area.id or :#{#filter.managementId} = ts.area.management.id)
        and (:#{#filter.name} is null or ts.name like :#{#filter.name})
    """)
    override fun findAllByFilter(
        @Param("filter") filter: Filter<TechnicalSpecialist>?,
        pageable: Pageable
    ) : Page<TechnicalSpecialist>
}

@Repository
interface ForemenRepository : JpaRepository<Foreman, Int>

@Repository
interface MastersRepository : JpaRepository<Master, Int>