package nsu.manasyan.buildingcompany.buildingobjects.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.buildingobjects.dto.WorkTypeDto
import nsu.manasyan.buildingcompany.buildingobjects.mappers.WorkTypeMapper
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkType
import nsu.manasyan.buildingcompany.buildingobjects.repositories.MaterialFilter
import nsu.manasyan.buildingcompany.buildingobjects.repositories.WorkTypeFilter
import nsu.manasyan.buildingcompany.buildingobjects.services.WorkTypeService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/work-types")
class WorkTypeController(
    private val workTypeService: WorkTypeService,
    mapper: WorkTypeMapper
) : AbstractCrudController<WorkType, WorkTypeDto>(workTypeService, mapper, "Work type") {

    @GetMapping("/by-building-object")
    fun getByBuildingObject(@RequestParam buildingObjectId: Int, params: FindRequestParameters?): PageDto<*> {
        return mapper.toPageDto(workTypeService.getByBuildingObject(buildingObjectId, params))
    }

    @GetMapping("/by-areas-managements-delay")
    fun findByAreaManagementDelay(@RequestParam areaIds: List<Int>?,
                                  @RequestParam managementIds: List<Int>?,
                                  params: FindRequestParameters?): PageDto<*> {
        return mapper.toPageDto(workTypeService.findByAreaManagementDelay(areaIds, managementIds, params))
    }

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: WorkTypeFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}