package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.MachineryMapper
import nsu.manasyan.buildingcompany.dto.model.MachineryDto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.Machinery
import nsu.manasyan.buildingcompany.repositories.BrigadeObjectWorkFilter
import nsu.manasyan.buildingcompany.repositories.MachineryFilter
import nsu.manasyan.buildingcompany.repositories.MachineryRepository
import nsu.manasyan.buildingcompany.repositories.WorkScheduleFilter
import nsu.manasyan.buildingcompany.services.MachineryService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.PageRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("\${application.path}/machinery")
class MachineryController(
    private val machineryService: MachineryService,
    mapper: MachineryMapper
) : AbstractCrudController<Machinery, MachineryDto>(machineryService, mapper, "Machinery") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: MachineryFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

    @GetMapping("/by-building-object")
    fun getByBuildingObject(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                               @RequestParam(required = false)
                               startDateMin: Date?,
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                               @RequestParam(required = false)
                               startDateMax: Date?,
                               @RequestParam(required = false)
                               buildingObjectId: Int?,
                               params: FindRequestParameters?): PageDto<*> {

        return mapper.toPageDto(machineryService.getByBuildingObject(startDateMin, startDateMax, buildingObjectId, params))
    }
}