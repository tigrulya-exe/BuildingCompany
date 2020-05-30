package nsu.manasyan.buildingcompany.companypartsstuff.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.companypartsstuff.dto.MachineryDto
import nsu.manasyan.buildingcompany.companypartsstuff.mappers.MachineryMapper
import nsu.manasyan.buildingcompany.companypartsstuff.model.Machinery
import nsu.manasyan.buildingcompany.companypartsstuff.repositories.MachineryFilter
import nsu.manasyan.buildingcompany.companypartsstuff.services.MachineryService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
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
    fun getByBuildingObject(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @RequestParam(required = false)
        startDateMin: Date?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @RequestParam(required = false)
        startDateMax: Date?,
        @RequestParam(required = false)
        buildingObjectId: Int?,
        params: FindRequestParameters?
    ): PageDto<*> {

        return mapper.toPageDto(
            machineryService.getByBuildingObject(
                startDateMin,
                startDateMax,
                buildingObjectId,
                params
            )
        )
    }

    @GetMapping("/by-construction-managements")
    fun getByConstructionManagements(
        @RequestParam constructionManagementIds: List<Int>,
        params: FindRequestParameters?
    ): PageDto<*> {
        return mapper.toPageDto(machineryService.getByConstructionManagements(constructionManagementIds, params))
    }
}