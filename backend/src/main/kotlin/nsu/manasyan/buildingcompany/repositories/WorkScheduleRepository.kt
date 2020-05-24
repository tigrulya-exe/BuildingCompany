package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.ScheduleDelay
import nsu.manasyan.buildingcompany.model.WorkSchedule
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Repository
interface WorkScheduleRepository : JpaFilterRepository<WorkSchedule, Int> {
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
        @Param("filter") filter: Filter<WorkSchedule>?,
        pageable: Pageable
    ): Page<WorkSchedule>
}

@Repository
interface ScheduleDelayRepository : JpaRepository<ScheduleDelay, Int>

@NoArgConstructor
class BrigadeObjectWorkFilter(
    var buildingObjectId: Int?,
    var brigadeId: Int?,
    workType: String?
) : Filter<WorkSchedule>{
    var workType: String? by FilterStringDelegate(workType)
}

@NoArgConstructor
class WorkScheduleFilter(
    var buildingObjectId: Int?,
    var brigadeId: Int?,
    var startDateMin: Date?,
    var startDateMax: Date?,
    workType: String?
) : Filter<WorkSchedule>{
    var workType: String? by FilterStringDelegate(workType)

    constructor(
        outerFilter : BrigadeObjectWorkFilter?,
        startDateMin: Date?,
        startDateMax: Date?)
            : this(
        outerFilter?.buildingObjectId,
        outerFilter?.brigadeId,
        startDateMin,
        startDateMax,
        outerFilter?.workType)
}