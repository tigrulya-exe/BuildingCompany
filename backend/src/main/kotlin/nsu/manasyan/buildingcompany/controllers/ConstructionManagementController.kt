package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.ConstructionManagementMapper
import nsu.manasyan.buildingcompany.dto.model.ConstructionManagementDto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.ConstructionManagement
import nsu.manasyan.buildingcompany.repositories.ConstructionManagementFilter
import nsu.manasyan.buildingcompany.services.ConstructionManagementService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/construction-management")
class ConstructionManagementController(
    service: ConstructionManagementService,
    mapper: ConstructionManagementMapper
) : AbstractCrudController<ConstructionManagement,
        ConstructionManagementDto>(service, mapper, "ConstructionManagement") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: ConstructionManagementFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}