package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.ForemanMapper
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.dto.model.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.model.workers.Foreman
import nsu.manasyan.buildingcompany.repositories.TechnicalSpecialistFilter
import nsu.manasyan.buildingcompany.services.ForemenService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/foremen")
class ForemenController(
    private val foremenService: ForemenService,
    mapper: ForemanMapper
) :
    AbstractCrudController<Foreman, TechnicalSpecialistDto>(foremenService, mapper, "Foreman") {

    @GetMapping("/filter")
    fun findAllByFilter(filter: TechnicalSpecialistFilter?, requestParams: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, requestParams)
    }
}