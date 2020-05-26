package nsu.manasyan.buildingcompany.workers.controllers

import nsu.manasyan.buildingcompany.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.workers.dto.WorkerDto
import nsu.manasyan.buildingcompany.workers.mappers.WorkerMapper
import nsu.manasyan.buildingcompany.workers.model.Worker
import nsu.manasyan.buildingcompany.workers.repositories.WorkerFilter
import nsu.manasyan.buildingcompany.workers.services.WorkersService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${application.path}/workers")
class WorkersController(
    private val workersService: WorkersService,
    mapper: WorkerMapper
) : AbstractCrudController<Worker, WorkerDto>(workersService, mapper, "Worker") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: WorkerFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

    @GetMapping("/by-building-object")
    fun getByBuildingObject(@RequestParam buildingObjectId: Int, params: FindRequestParameters?): PageDto<*> {
        return mapper.toPageDto(workersService.findAllByFilter(buildingObjectId, params))
    }
}