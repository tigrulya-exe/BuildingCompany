package nsu.manasyan.buildingcompany.workers.controllers

import nsu.manasyan.buildingcompany.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.workers.dto.LocksmithDto
import nsu.manasyan.buildingcompany.workers.mappers.LocksmithMapper
import nsu.manasyan.buildingcompany.workers.model.Locksmith
import nsu.manasyan.buildingcompany.workers.repositories.LocksmithFilter
import nsu.manasyan.buildingcompany.workers.services.LocksmithsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/locksmiths")
class LocksmithsController(service: LocksmithsService, mapper: LocksmithMapper) :
    AbstractCrudController<Locksmith, LocksmithDto>(service, mapper, "Locksmith") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: LocksmithFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}