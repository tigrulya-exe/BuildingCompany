package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.BuildingObject
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.repositories.BuildingObjectRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class BuildingObjectService(private val objectRepository: BuildingObjectRepository) :
    AbstractCrudService<BuildingObject>(objectRepository){

    fun findByAreasOrManagements(
        areaIds: List<Int>,
        managementIds: List<Int>,
        params: FindRequestParameters?): Page<BuildingObject> {
        val pageable = getPageable(params)
        return objectRepository.findByAreasOrManagements(areaIds, managementIds, pageable)
    }
}