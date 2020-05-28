package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BuildingObjectRepository
import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BuildingObjectService(
    private val objectRepository: BuildingObjectRepository,
    private val workTypeService: WorkTypeService
) : AbstractCrudService<BuildingObject>(objectRepository) {

    fun findByAreasOrManagements(
        areaIds: List<Int>,
        managementIds: List<Int>,
        params: FindRequestParameters?
    ): Page<BuildingObject> {
        val pageable = getPageable(params)
        return objectRepository.findByAreasOrManagements(areaIds, managementIds, pageable)
    }

    @Transactional
    fun addWorkTypes(workTypeIds: List<Int>, buildingObjectId: Int) {
        val workTypes = workTypeIds.map { workTypeService.getEntity(it) }
        val buildingObject = getEntity(buildingObjectId)
        buildingObject.workTypes.addAll(workTypes)
    }
}