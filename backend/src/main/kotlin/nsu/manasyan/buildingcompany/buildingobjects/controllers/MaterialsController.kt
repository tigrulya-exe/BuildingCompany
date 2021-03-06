package nsu.manasyan.buildingcompany.buildingobjects.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.buildingobjects.dto.MaterialDto
import nsu.manasyan.buildingcompany.buildingobjects.mappers.MaterialMapper
import nsu.manasyan.buildingcompany.buildingobjects.model.Material
import nsu.manasyan.buildingcompany.buildingobjects.repositories.MaterialFilter
import nsu.manasyan.buildingcompany.buildingobjects.services.MaterialsService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/materials")
class MaterialsController(
    private val materialsService: MaterialsService,
    mapper: MaterialMapper
) : AbstractCrudController<Material, MaterialDto>(materialsService, mapper, "Material") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: MaterialFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

    @GetMapping("/by-area-management-exceedance")
    fun findByAreaManagementExceedance(
        @RequestParam areaIds: List<Int>?,
        @RequestParam managementIds: List<Int>?,
        params: FindRequestParameters?
    ): PageDto<*> {
        return mapper.toPageDto(materialsService.findByAreaManagementExceedance(areaIds, managementIds, params))
    }
}