package nsu.manasyan.buildingcompany.workers.services

import nsu.manasyan.buildingcompany.services.AbstractCrudService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.workers.model.Worker
import nsu.manasyan.buildingcompany.workers.repositories.WorkersRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class WorkersService(private val workersRepository: WorkersRepository) : AbstractCrudService<Worker>(workersRepository){
    fun findAllByFilter(buildingObjectId: Int, params: FindRequestParameters?): Page<Worker> {
        val pageable = getPageable(params)
        return workersRepository.findDistinctByBuildingObject(buildingObjectId, pageable)
    }
}