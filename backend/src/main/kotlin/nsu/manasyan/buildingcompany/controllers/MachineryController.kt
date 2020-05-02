package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.MachineryMapper
import nsu.manasyan.buildingcompany.dto.model.MachineryDto
import nsu.manasyan.buildingcompany.model.Machinery
import nsu.manasyan.buildingcompany.services.MachineryService
import org.springframework.web.bind.annotation.RestController

@RestController
class MachineryController(
    machineryService: MachineryService,
    mapper: MachineryMapper
) : AbstractCrudController<Machinery, MachineryDto>(machineryService, mapper, "Machinery")