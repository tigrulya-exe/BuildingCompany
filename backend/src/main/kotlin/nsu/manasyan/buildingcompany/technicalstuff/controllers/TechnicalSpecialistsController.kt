package nsu.manasyan.buildingcompany.technicalstuff.controllers

import nsu.manasyan.buildingcompany.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.technicalstuff.mappers.TechnicalSpecialistMapper
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.technicalstuff.dto.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.technicalstuff.model.TechnicalSpecialist
import nsu.manasyan.buildingcompany.technicalstuff.repositories.TechnicalSpecialistFilter
import nsu.manasyan.buildingcompany.technicalstuff.services.TechnicalSpecialistsService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/technical-specialists")
class TechnicalSpecialistsController(
    private val specialistsService: TechnicalSpecialistsService,
    mapper: TechnicalSpecialistMapper
) :
    AbstractCrudController<TechnicalSpecialist, TechnicalSpecialistDto>(specialistsService, mapper, "TechnicalSpecialist") {

    @GetMapping("/filter")
    fun findAllByFilter(filter: TechnicalSpecialistFilter?, requestParams: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, requestParams)
    }

    @GetMapping("/by-areas-or-managements")
    fun findByAreasOrManagements(
        @RequestParam areaIds: List<Int> = listOf(),
        @RequestParam managementIds: List<Int> = listOf(),
        requestParams: FindRequestParameters?): PageDto<*> {
        val page = specialistsService.findByAreasOrManagements(areaIds, managementIds, requestParams)
        return mapper.toPageDto(page)
    }
}