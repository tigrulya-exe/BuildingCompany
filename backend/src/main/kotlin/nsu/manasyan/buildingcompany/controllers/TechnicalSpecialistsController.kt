package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.TechnicalSpecialistMapper
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.dto.model.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.services.TechnicalSpecialistsService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.TechnicalSpecialistFilter
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/technical-specialists")
class TechnicalSpecialistsController(override val service: TechnicalSpecialistsService, mapper: TechnicalSpecialistMapper) :
    AbstractCrudController<TechnicalSpecialist, TechnicalSpecialistDto>(service, mapper, "TechnicalSpecialist"){

    @GetMapping("/filter")
    fun findAllByFilter(filter: Filter<TechnicalSpecialist>?, requestParams: FindRequestParameters?) : PageDto<*>{
        val page = service.findAllByFilter(filter, requestParams)
        return mapper.toPageDto(page)
    }
}