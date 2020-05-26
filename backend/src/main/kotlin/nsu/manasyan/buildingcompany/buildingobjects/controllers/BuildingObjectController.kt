package nsu.manasyan.buildingcompany.buildingobjects.controllers

import nsu.manasyan.buildingcompany.buildingobjects.dto.BuildingObjectDto
import nsu.manasyan.buildingcompany.buildingobjects.mappers.BuildingObjectMapper
import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BuildingObjectFilter
import nsu.manasyan.buildingcompany.buildingobjects.services.BuildingObjectService
import nsu.manasyan.buildingcompany.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.dto.model.PageDto
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
        requestParams: FindRequestParameters?
    ): PageDto<*> {
        val page = objectService.findByAreasOrManagements(areaIds, managementIds, requestParams)
        return mapper.toPageDto(page)
    }
}