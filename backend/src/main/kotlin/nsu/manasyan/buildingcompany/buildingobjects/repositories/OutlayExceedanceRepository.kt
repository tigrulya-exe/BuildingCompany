package nsu.manasyan.buildingcompany.buildingobjects.repositories

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.buildingobjects.model.Outlay
import nsu.manasyan.buildingcompany.buildingobjects.model.OutlayExceedance
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface OutlayExceedanceRepository : JpaFilterRepository<OutlayExceedance, Int> {
    @Query("""
        select o   
        from OutlayExceedance o
        where (:#{#filter.outlayRowId} is null or o.outlayRow.id = :#{#filter.outlayRowId})
        and (:#{#filter.exceedanceMin} is null or o.exceedanceCount >= :#{#filter.exceedanceMin})
        and (:#{#filter.exceedanceMax} is null or o.exceedanceCount <= :#{#filter.exceedanceMax})
    """)
    override fun findAllByFilter(filter: Filter<in OutlayExceedance>?, pageable: Pageable): Page<OutlayExceedance>

    fun getByOutlayRow(outlayRow: Outlay) : Optional<OutlayExceedance>
}

@NoArgConstructor
data class OutlayExceedanceFilter(
    var outlayRowId: Int?,
    var exceedanceMin: Int?,
    var exceedanceMax: Int?
) : Filter<OutlayExceedance>