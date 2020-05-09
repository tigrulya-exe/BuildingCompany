package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.AreaMapper
import nsu.manasyan.buildingcompany.dto.model.AreaDto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.Area
import nsu.manasyan.buildingcompany.repositories.AreaFilter
import nsu.manasyan.buildingcompany.services.AreaService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/areas")
class AreaController(service: AreaService, mapper: AreaMapper) :
    AbstractCrudController<Area, AreaDto>(service, mapper, "Area") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: AreaFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}