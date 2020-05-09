package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.OutlayMapper
import nsu.manasyan.buildingcompany.dto.model.OutlayDto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.Outlay
import nsu.manasyan.buildingcompany.repositories.AreaFilter
import nsu.manasyan.buildingcompany.repositories.OutlayFilter
import nsu.manasyan.buildingcompany.services.OutlayService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/outlays")
class OutlayController(
    service: OutlayService,
    mapper: OutlayMapper
) : AbstractCrudController<Outlay, OutlayDto>(service, mapper, "Outlay"){

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: OutlayFilter?, params: FindRequestParameters?) : PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}