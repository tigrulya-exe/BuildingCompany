package nsu.manasyan.buildingcompany.buildingobjects.controllers

import nsu.manasyan.buildingcompany.buildingobjects.dto.BridgeDto
import nsu.manasyan.buildingcompany.buildingobjects.mappers.BridgeMapper
import nsu.manasyan.buildingcompany.buildingobjects.model.Bridge
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BridgeFilter
import nsu.manasyan.buildingcompany.buildingobjects.services.BridgeService
import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/bridges")
class BridgesController(
    service: BridgeService,
    mapper: BridgeMapper
) : AbstractCrudController<Bridge, BridgeDto>(service, mapper, "Bridge") {

    @GetMapping("/filter")
    @PreAuthorize("hasAuthority('READ')")
    fun getAllEntitiesByFilter(filter: BridgeFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

}