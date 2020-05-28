package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkType
import nsu.manasyan.buildingcompany.buildingobjects.repositories.WorkTypeRepository
import org.springframework.stereotype.Service

@Service
class WorkTypeService(private val workTypeRepository: WorkTypeRepository) :
    AbstractCrudService<WorkType>(workTypeRepository) {
    fun getOrCreateByName(name: String): WorkType {
        return workTypeRepository
            .findByNameIgnoreCase(name)
            .orElseGet { workTypeRepository.save(
                WorkType(
                    name
                )
            ) }
    }
}