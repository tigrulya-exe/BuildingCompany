package nsu.manasyan.buildingcompany.technicalstuff.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.technicalstuff.dto.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.technicalstuff.mappers.ForemanMapper
import nsu.manasyan.buildingcompany.technicalstuff.model.Foreman
import nsu.manasyan.buildingcompany.technicalstuff.repositories.ForemanFilter
import nsu.manasyan.buildingcompany.technicalstuff.services.ForemenService
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
    fun findAllByFilter(filter: ForemanFilter?, requestParams: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, requestParams)
    }
}