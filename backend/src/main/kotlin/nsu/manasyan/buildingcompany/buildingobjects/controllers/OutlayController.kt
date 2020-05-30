package nsu.manasyan.buildingcompany.buildingobjects.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.buildingobjects.mappers.OutlayMapper
import nsu.manasyan.buildingcompany.buildingobjects.dto.OutlayDto
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.buildingobjects.dto.ExceedanceDto
import nsu.manasyan.buildingcompany.buildingobjects.model.Outlay
import nsu.manasyan.buildingcompany.buildingobjects.model.OutlayExceedance
import nsu.manasyan.buildingcompany.buildingobjects.repositories.OutlayFilter
import nsu.manasyan.buildingcompany.buildingobjects.services.OutlayService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/outlays")
class OutlayController(
    private val outlayService: OutlayService,
    mapper: OutlayMapper
) : AbstractCrudController<Outlay, OutlayDto>(outlayService, mapper, "Outlay") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: OutlayFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

    @GetMapping("/by-outlay/{outlayId}")
    fun getExceedanceByOutlayId(@PathVariable outlayId: Int): ExceedanceDto? {
        val exceedance =  outlayService.getExceedanceByOutlayId(outlayId)
        return exceedance?.let { ExceedanceDto(it.exceedanceCount) }
    }
}