package nsu.manasyan.buildingcompany.workers.controllers

import nsu.manasyan.buildingcompany.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.workers.dto.MasonDto
import nsu.manasyan.buildingcompany.workers.mappers.MasonMapper
import nsu.manasyan.buildingcompany.workers.model.Mason
import nsu.manasyan.buildingcompany.workers.repositories.MasonFilter
import nsu.manasyan.buildingcompany.workers.services.MasonsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/masons")
class MasonsController(service: MasonsService, mapper: MasonMapper) :
    AbstractCrudController<Mason, MasonDto>(service, mapper, "Mason") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: MasonFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}