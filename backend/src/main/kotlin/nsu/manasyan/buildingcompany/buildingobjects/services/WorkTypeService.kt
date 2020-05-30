package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkType
import nsu.manasyan.buildingcompany.buildingobjects.repositories.WorkTypeRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.getNullableList
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WorkTypeService(
    private val workTypeRepository: WorkTypeRepository
) : AbstractCrudService<WorkType>(workTypeRepository) {
    @Transactional
    override fun deleteEntity(id: Int) {
        val entity = getEntity(id)
        entity.buildingObjects.forEach { it.workTypes.remove(entity) }
        super.deleteEntity(id)
    }

    fun getOrCreateByName(name: String): WorkType {
        return workTypeRepository
            .findByNameIgnoreCase(name)
            .orElseGet {
                workTypeRepository.save(WorkType(name))
            }
    }

    fun findByAreaManagementDelay(
        areaIds: List<Int>?,
        managementIds: List<Int>?,
        params: FindRequestParameters?
    ): Page<WorkType> {
        val pageable = getPageable(params)
        return workTypeRepository.findByAreaManagementDelay(
            getNullableList(areaIds), getNullableList(managementIds), pageable
        )
    }

    fun getByBuildingObject(buildingObjectId: Int, params: FindRequestParameters?): Page<WorkType> {
        val pageable = getPageable(params)
        return workTypeRepository.findByBuildingObject(buildingObjectId, pageable)
    }
}