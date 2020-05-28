package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkType
import nsu.manasyan.buildingcompany.buildingobjects.repositories.WorkTypeRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class WorkTypeService(
    private val workTypeRepository: WorkTypeRepository
//    ,private val buildingObjectService: BuildingObjectService
) : AbstractCrudService<WorkType>(workTypeRepository) {
    fun getOrCreateByName(name: String): WorkType {
        return workTypeRepository
            .findByNameIgnoreCase(name)
            .orElseGet { workTypeRepository.save(
                WorkType(
                    name
                )
            ) }
    }

    fun getByBuildingObject(buildingObjectId: Int, params: FindRequestParameters?): Page<WorkType> {
        val pageable = getPageable(params)
//        val buildingObject = buildingObjectService.getEntity(buildingObjectId)
        return workTypeRepository.findByBuildingObject(buildingObjectId, pageable)
//        return workTypeRepository.findByBuildingObjectsContains(buildingObject, pageable)
    }
}