package nsu.manasyan.buildingcompany.buildingobjects.repositories

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.buildingobjects.model.ScheduleDelay
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkSchedule
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ScheduleDelayRepository : JpaFilterRepository<ScheduleDelay, Int> {
    @Query(
        """
        select s   
        from ScheduleDelay s
        where (:#{#filter.scheduleRowId} is null or s.scheduleRow.id = :#{#filter.scheduleRowId})
        and (coalesce(:#{#filter.delayMin},:#{#filter.delayMin}) is null or s.delay >= :#{#filter.delayMin})
        and (coalesce(:#{#filter.delayMax}, :#{#filter.delayMax}) is null or s.delay <= :#{#filter.delayMax})
    """
    )
    override fun findAllByFilter(filter: Filter<in ScheduleDelay>?, pageable: Pageable): Page<ScheduleDelay>

    fun findByScheduleRow(scheduleRow: WorkSchedule) : Optional<ScheduleDelay>
}

@NoArgConstructor
class DelayFilter(
    var delayMin: Date?,
    var delayMax: Date?,
    var scheduleRowId: Int?
) : Filter<ScheduleDelay>