package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.WorkScheduleMapper
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.dto.model.WorkScheduleDto
import nsu.manasyan.buildingcompany.model.WorkSchedule
import nsu.manasyan.buildingcompany.repositories.BrigadeObjectWorkFilter
import nsu.manasyan.buildingcompany.repositories.WorkScheduleFilter
import nsu.manasyan.buildingcompany.services.WorkScheduleService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("\${application.path}/work-schedules")
class WorkScheduleController(
    service: WorkScheduleService,
    mapper: WorkScheduleMapper
) : AbstractCrudController<WorkSchedule, WorkScheduleDto>(service, mapper, "WorkSchedule") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                               @RequestParam
                               startDateMin: Date?,
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                               @RequestParam
                               startDateMax: Date?,
                               filter: BrigadeObjectWorkFilter?, params: FindRequestParameters?): PageDto<*> {
        val workScheduleFilter = WorkScheduleFilter(filter, startDateMin, startDateMax)
        return super.findAllByFilter(workScheduleFilter, params)
    }

}