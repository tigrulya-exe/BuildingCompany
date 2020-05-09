package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.MachineryMapper
import nsu.manasyan.buildingcompany.dto.model.MachineryDto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.Machinery
import nsu.manasyan.buildingcompany.repositories.MachineryFilter
import nsu.manasyan.buildingcompany.services.MachineryService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/machinery")
class MachineryController(
    machineryService: MachineryService,
    mapper: MachineryMapper
) : AbstractCrudController<Machinery, MachineryDto>(machineryService, mapper, "Machinery") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: MachineryFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}