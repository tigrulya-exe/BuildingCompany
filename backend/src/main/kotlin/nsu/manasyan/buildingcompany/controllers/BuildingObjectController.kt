package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.BuildingObjectMapper
import nsu.manasyan.buildingcompany.dto.model.BuildingObjectDto
import nsu.manasyan.buildingcompany.model.BuildingObject
import nsu.manasyan.buildingcompany.services.BuildingObjectService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application-path}/building-objects")
class BuildingObjectController(
    service: BuildingObjectService,
    mapper: BuildingObjectMapper
) : AbstractCrudController<BuildingObject, BuildingObjectDto>(service, mapper, "BuildingObject")