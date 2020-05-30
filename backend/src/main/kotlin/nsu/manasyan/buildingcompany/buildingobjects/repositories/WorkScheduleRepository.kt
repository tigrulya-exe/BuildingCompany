package nsu.manasyan.buildingcompany.buildingobjects.repositories

import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.buildingobjects.model.ScheduleDelay
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkSchedule
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WorkScheduleRepository :
    JpaFilterRepository<WorkSchedule, Int> {
    @Query(
        """
        select s   
        from WorkSchedule s join BrigadeObjectWork b on s.brigadeWork = b
        where (:#{#filter.brigadeId} is null or b.brigade.id = :#{#filter.brigadeId})
        and (:#{#filter.buildingObjectId} is null or b.buildingObject.id = :#{#filter.buildingObjectId})
        and (:#{#filter.workType} is null or lower(b.workType.name) like :#{#filter.workType})
        and (coalesce(:#{#filter.startDateMin},:#{#filter.startDateMin}) is null or s.startDate >= :#{#filter.startDateMin})
        and (coalesce(:#{#filter.startDateMax}, :#{#filter.startDateMax}) is null or s.startDate <= :#{#filter.startDateMax})
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<in WorkSchedule>?,
        pageable: Pageable
    ): Page<WorkSchedule>

}

@NoArgConstructor
class BrigadeObjectWorkFilter(
    var buildingObjectId: Int?,
    var brigadeId: Int?,
    workType: String?
) : Filter<WorkSchedule> {
    var workType: String? by FilterStringDelegate(workType)
}

@NoArgConstructor
class WorkScheduleFilter(
    var buildingObjectId: Int?,
    var startDateMin: Date?,
    var startDateMax: Date?,
    workType: String? = null,
    var brigadeId: Int? = null
) : Filter<WorkSchedule> {
    var workType: String? by FilterStringDelegate(workType)

    constructor(
        outerFilter: BrigadeObjectWorkFilter?,
        startDateMin: Date?,
        startDateMax: Date?
    ) : this(
        outerFilter?.buildingObjectId,
        startDateMin,
        startDateMax,
        outerFilter?.workType,
        outerFilter?.brigadeId
    )
}