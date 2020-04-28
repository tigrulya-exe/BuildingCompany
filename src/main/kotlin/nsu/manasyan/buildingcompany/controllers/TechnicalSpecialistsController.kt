package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.model.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.services.TechnicalSpecialistsService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application-path}/technical-specialists")
class TechnicalSpecialistsController(service: TechnicalSpecialistsService) :
    AbstractCrudController<TechnicalSpecialist, TechnicalSpecialistDto>(service, "TechnicalSpecialist")