package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Outlay
import nsu.manasyan.buildingcompany.model.OutlayExceedance
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OutlayRepository : JpaFilterRepository<Outlay, Int>{
    // TODO: тут наверн надо coalesce
    @Query("""
        select o   
        from Outlay o join WorkSchedule s
        where (:#{#filter.materialId} is null or o.material.id = :#{#filter.materialId})
        and (:#{#filter.brigadeId} is null or s.brigade.id = :#{#filter.brigadeId})
        and (:#{#filter.objectId} is null or s.buildingObject.id = :#{#filter.objectId})
        and (:#{#filter.workTypeId} is null or s.workType.id = :#{#filter.workTypeId})
        and (:#{#filter.materialCountMin} is null or o.materialCount >= :#{#filter.materialCountMin})
        and (:#{#filter.materialCountMax} is null or o.materialCount <= :#{#filter.materialCountMax})
        and (:#{#filter.startDateMin} is null or s.startDate >= :#{#filter.startDateMin})
        and (:#{#filter.startDateMax} is null or s.startDate <= :#{#filter.startDateMax})
    """)
    override fun findAllByFilter(@Param("filter") filter: Filter<Outlay>?,
                                 pageable: Pageable): Page<Outlay>

}

@Repository
interface OutlayExceedanceRepository : JpaRepository<OutlayExceedance, Int>

@NoArgConstructor
data class OutlayFilter(
    var objectId: Int?,
    var workTypeId: Int?,
    var materialCountMin: Int?,
    var materialCountMax: Int?,
    var brigadeId: Int?,
    var materialId: Int?,
    var startDateMin: Date?,
    var startDateMax: Date?
) : Filter<Outlay>