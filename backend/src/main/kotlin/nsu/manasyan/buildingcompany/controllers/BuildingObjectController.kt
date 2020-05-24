package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.BuildingObjectMapper
import nsu.manasyan.buildingcompany.dto.model.BuildingObjectDto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.BuildingObject
import nsu.manasyan.buildingcompany.repositories.BuildingObjectFilter
import nsu.manasyan.buildingcompany.services.BuildingObjectService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/building-objects")
class BuildingObjectController(
    private val objectService: BuildingObjectService,
    mapper: BuildingObjectMapper
) : AbstractCrudController<BuildingObject, BuildingObjectDto>(objectService, mapper, "BuildingObject") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: BuildingObjectFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

    @GetMapping("/by-areas-or-managements")
    fun findByAreasOrManagements(
        @RequestParam areaIds: List<Int> = listOf(),
        @RequestParam managementIds: List<Int> = listOf(),
        requestParams: FindRequestParameters?): PageDto<*> {
        val page = objectService.findByAreasOrManagements(areaIds, managementIds, requestParams)
        return mapper.toPageDto(page)
    }
}