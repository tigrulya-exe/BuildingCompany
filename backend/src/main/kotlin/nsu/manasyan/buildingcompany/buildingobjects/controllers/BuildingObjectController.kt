package nsu.manasyan.buildingcompany.buildingobjects.controllers

import nsu.manasyan.buildingcompany.buildingobjects.dto.BuildingObjectDto
import nsu.manasyan.buildingcompany.buildingobjects.mappers.BuildingObjectMapper
import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BuildingObjectFilter
import nsu.manasyan.buildingcompany.buildingobjects.services.BuildingObjectService
import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.buildingobjects.dto.IdListDto
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${application.path}/building-objects")
@PreAuthorize("hasAuthority('READ')")
class BuildingObjectController(
    private val objectService: BuildingObjectService,
    mapper: BuildingObjectMapper
) : AbstractCrudController<BuildingObject, BuildingObjectDto>(objectService, mapper, "BuildingObject") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: BuildingObjectFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    @PostMapping("/workTypes/{buildingObjectId}")
    fun addWorkTypes(@RequestBody idListDto: IdListDto, @PathVariable buildingObjectId: Int){
        objectService.addWorkTypes(idListDto.ids, buildingObjectId)
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{buildingObjectId}/workTypes/{workTypeId}")
    fun removeWorkType(@PathVariable buildingObjectId: Int, @PathVariable workTypeId: Int){
        objectService.removeWorkType(buildingObjectId, workTypeId)
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