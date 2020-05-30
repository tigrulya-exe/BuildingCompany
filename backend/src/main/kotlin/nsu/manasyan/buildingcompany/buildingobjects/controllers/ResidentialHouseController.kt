package nsu.manasyan.buildingcompany.buildingobjects.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.buildingobjects.dto.ResidentialHouseDto
import nsu.manasyan.buildingcompany.buildingobjects.mappers.ResidentialHouseMapper
import nsu.manasyan.buildingcompany.buildingobjects.model.ResidentialHouse
import nsu.manasyan.buildingcompany.buildingobjects.repositories.ResidentialHouseFilter
import nsu.manasyan.buildingcompany.buildingobjects.services.ResidentialHouseService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/residential-houses")
class ResidentialHouseController(
    service: ResidentialHouseService,
    mapper: ResidentialHouseMapper
) : AbstractCrudController<ResidentialHouse, ResidentialHouseDto>(service, mapper, "ResidentialHouse") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: ResidentialHouseFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

}