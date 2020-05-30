package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.ScheduleDelay
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkSchedule
import nsu.manasyan.buildingcompany.buildingobjects.repositories.ScheduleDelayRepository
import nsu.manasyan.buildingcompany.buildingobjects.repositories.WorkScheduleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class WorkScheduleService(
    scheduleRepository: WorkScheduleRepository,
    private val scheduleDelayRepository: ScheduleDelayRepository
) : AbstractCrudService<WorkSchedule>(scheduleRepository){

    fun findByScheduleRowId(scheduleRowId: Int): ScheduleDelay? {
        val scheduleRow = getEntity(scheduleRowId)
        return scheduleDelayRepository
            .findByScheduleRow(scheduleRow)
            .orElse(null)
    }
}