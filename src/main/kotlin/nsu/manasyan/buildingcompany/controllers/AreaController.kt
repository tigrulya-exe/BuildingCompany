package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.AreaMapper
import nsu.manasyan.buildingcompany.dto.model.AreaDto
import nsu.manasyan.buildingcompany.model.Area
import nsu.manasyan.buildingcompany.services.AreaService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application-path}/areas")
class AreaController(service: AreaService, mapper: AreaMapper)
    : AbstractCrudController<Area, AreaDto>(service, mapper, "Area")