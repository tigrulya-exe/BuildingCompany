package nsu.manasyan.buildingcompany.companypartsstuff.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.companypartsstuff.mappers.ConstructionManagementMapper
import nsu.manasyan.buildingcompany.companypartsstuff.dto.ConstructionManagementDto
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.companypartsstuff.model.ConstructionManagement
import nsu.manasyan.buildingcompany.companypartsstuff.repositories.ConstructionManagementFilter
import nsu.manasyan.buildingcompany.companypartsstuff.services.ConstructionManagementService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/construction-managements")
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