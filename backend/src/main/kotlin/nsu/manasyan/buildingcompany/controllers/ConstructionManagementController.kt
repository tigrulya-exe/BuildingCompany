package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.ConstructionManagementMapper
import nsu.manasyan.buildingcompany.dto.model.ConstructionManagementDto
import nsu.manasyan.buildingcompany.model.ConstructionManagement
import nsu.manasyan.buildingcompany.services.ConstructionManagementService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application-path}/construction-management")
class ConstructionManagementController(
    service: ConstructionManagementService,
    mapper: ConstructionManagementMapper
) : AbstractCrudController<ConstructionManagement, ConstructionManagementDto>(service, mapper, "ConstructionManagement")