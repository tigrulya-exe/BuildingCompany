package nsu.manasyan.buildingcompany.technicalstuff.controllers

import nsu.manasyan.buildingcompany.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.technicalstuff.mappers.MasterMapper
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.technicalstuff.dto.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.technicalstuff.model.Master
import nsu.manasyan.buildingcompany.technicalstuff.repositories.MasterFilter
import nsu.manasyan.buildingcompany.technicalstuff.repositories.TechnicalSpecialistFilter
import nsu.manasyan.buildingcompany.technicalstuff.services.MastersService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/masters")
class MastersController(
    private val mastersService: MastersService,
    mapper: MasterMapper
) :
    AbstractCrudController<Master, TechnicalSpecialistDto>(mastersService, mapper, "Master") {

    @GetMapping("/filter")
    fun findAllByFilter(filter: MasterFilter?, requestParams: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, requestParams)
    }
}