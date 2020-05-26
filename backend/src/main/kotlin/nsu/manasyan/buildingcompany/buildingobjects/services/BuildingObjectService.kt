package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BuildingObjectRepository
import nsu.manasyan.buildingcompany.services.AbstractCrudService
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