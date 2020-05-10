package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.ScheduleDelay
import nsu.manasyan.buildingcompany.model.WorkSchedule
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WorkScheduleRepository : JpaFilterRepository<WorkSchedule, Int> {
    @Query(
        """
        select s   
        from WorkSchedule s
        where (:#{#filter.brigadeId} is null or s.brigade.id = :#{#filter.brigadeId})
        and (:#{#filter.objectId} is null or s.buildingObject.id = :#{#filter.objectId})
        and (:#{#filter.workTypeId} is null or s.workType.id = :#{#filter.workTypeId})
        and (:#{#filter.startDateMin} is null or s.startDate >= :#{#filter.startDateMin})
        and (:#{#filter.startDateMax} is null or s.startDate <= :#{#filter.startDateMax})
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
data class WorkScheduleFilter(
    var objectId: Int?,
    var workTypeId: Int?,
    var brigadeId: Int?,
    var startDateMin: Date?,
    var startDateMax: Date?
) : Filter<WorkSchedule>