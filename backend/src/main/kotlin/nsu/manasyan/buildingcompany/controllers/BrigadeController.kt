package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.BrigadeMapper
import nsu.manasyan.buildingcompany.dto.model.BrigadeDto
import nsu.manasyan.buildingcompany.model.workers.Brigade
import nsu.manasyan.buildingcompany.services.BrigadeService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application-path}/brigades")
class BrigadeController(service: BrigadeService, mapper: BrigadeMapper) :
    AbstractCrudController<Brigade, BrigadeDto>(service, mapper, "Brigade") {
}