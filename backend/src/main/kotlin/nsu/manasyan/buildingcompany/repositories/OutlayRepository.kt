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
interface OutlayRepository : JpaFilterRepository<Outlay, Int> {
    @Query(
        """
        select o   
        from Outlay o
        where (:#{#filter.materialId} is null or o.material.id = :#{#filter.materialId})
        and (:#{#filter.buildingObjectId} is null or o.buildingObject.id = :#{#filter.buildingObjectId})
        and (:#{#filter.materialCountMin} is null or o.materialCount >= :#{#filter.materialCountMin})
        and (:#{#filter.materialCountMax} is null or o.materialCount <= :#{#filter.materialCountMax})
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<in Outlay>?,
        pageable: Pageable
    ): Page<Outlay>

}

@Repository
interface OutlayExceedanceRepository : JpaRepository<OutlayExceedance, Int>

@NoArgConstructor
data class OutlayFilter(
    var buildingObjectId: Int?,
    var materialCountMin: Int?,
    var materialCountMax: Int?,
    var materialId: Int?
) : Filter<Outlay>