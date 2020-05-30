package nsu.manasyan.buildingcompany.companypartsstuff.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.companypartsstuff.dto.AreaDto
import nsu.manasyan.buildingcompany.companypartsstuff.mappers.AreaMapper
import nsu.manasyan.buildingcompany.companypartsstuff.model.Area
import nsu.manasyan.buildingcompany.companypartsstuff.repositories.AreaFilter
import nsu.manasyan.buildingcompany.companypartsstuff.services.AreaService
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