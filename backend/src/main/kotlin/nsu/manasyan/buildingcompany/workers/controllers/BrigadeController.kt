package nsu.manasyan.buildingcompany.workers.controllers

import nsu.manasyan.buildingcompany.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.workers.dto.BrigadeDto
import nsu.manasyan.buildingcompany.workers.mappers.BrigadeMapper
import nsu.manasyan.buildingcompany.workers.model.Brigade
import nsu.manasyan.buildingcompany.workers.repositories.BrigadeFilter
import nsu.manasyan.buildingcompany.workers.services.BrigadeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/brigades")
class BrigadeController(service: BrigadeService, mapper: BrigadeMapper) :
    AbstractCrudController<Brigade, BrigadeDto>(service, mapper, "Brigade") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: BrigadeFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}