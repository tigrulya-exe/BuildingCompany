package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.BrigadeMapper
import nsu.manasyan.buildingcompany.dto.model.BrigadeDto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.workers.Brigade
import nsu.manasyan.buildingcompany.repositories.AreaFilter
import nsu.manasyan.buildingcompany.repositories.BrigadeFilter
import nsu.manasyan.buildingcompany.services.BrigadeService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/brigades")
class BrigadeController(service: BrigadeService, mapper: BrigadeMapper) :
    AbstractCrudController<Brigade, BrigadeDto>(service, mapper, "Brigade") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(@RequestParam filter: BrigadeFilter?, params: FindRequestParameters?) : PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}