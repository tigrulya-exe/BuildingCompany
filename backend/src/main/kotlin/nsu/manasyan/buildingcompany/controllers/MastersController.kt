package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.MasterMapper
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.dto.model.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.model.workers.Master
import nsu.manasyan.buildingcompany.repositories.TechnicalSpecialistFilter
import nsu.manasyan.buildingcompany.services.MastersService
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
    fun findAllByFilter(filter: TechnicalSpecialistFilter?, requestParams: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, requestParams)
    }
}