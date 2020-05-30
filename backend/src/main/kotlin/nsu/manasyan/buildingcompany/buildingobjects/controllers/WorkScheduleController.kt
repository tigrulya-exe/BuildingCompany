package nsu.manasyan.buildingcompany.buildingobjects.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.buildingobjects.mappers.WorkScheduleMapper
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.buildingobjects.dto.DelayDto
import nsu.manasyan.buildingcompany.buildingobjects.dto.WorkScheduleDto
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkSchedule
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BrigadeObjectWorkFilter
import nsu.manasyan.buildingcompany.buildingobjects.repositories.WorkScheduleFilter
import nsu.manasyan.buildingcompany.buildingobjects.services.WorkScheduleService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("\${application.path}/work-schedules")
class WorkScheduleController(
    private val scheduleService: WorkScheduleService,
    mapper: WorkScheduleMapper
) : AbstractCrudController<WorkSchedule, WorkScheduleDto>(scheduleService, mapper, "WorkSchedule") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @RequestParam
        startDateMin: Date?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @RequestParam
        startDateMax: Date?,
        filter: BrigadeObjectWorkFilter?, params: FindRequestParameters?
    ): PageDto<*> {
        val workScheduleFilter =
            WorkScheduleFilter(
                filter,
                startDateMin,
                startDateMax
            )
        return super.findAllByFilter(workScheduleFilter, params)
    }

    @GetMapping("/by-schedule-row/{scheduleRowId}")
    fun findByScheduleRowId(@PathVariable scheduleRowId: Int): DelayDto?{
        val delay = scheduleService.findByScheduleRowId(scheduleRowId)
        return delay?.let { DelayDto(it.delay) }
    }

}