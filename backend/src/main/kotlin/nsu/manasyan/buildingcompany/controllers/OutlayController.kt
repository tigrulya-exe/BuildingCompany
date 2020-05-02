package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.OutlayMapper
import nsu.manasyan.buildingcompany.dto.model.OutlayDto
import nsu.manasyan.buildingcompany.model.Outlay
import nsu.manasyan.buildingcompany.services.OutlayService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application-path}/outlays")
class OutlayController(
    service: OutlayService,
    mapper: OutlayMapper
) : AbstractCrudController<Outlay, OutlayDto>(service, mapper, "Outlay")