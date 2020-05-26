package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.WorkType
import nsu.manasyan.buildingcompany.repositories.WorkTypeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class WorkTypeService(private val workTypeRepository: WorkTypeRepository) : AbstractCrudService<WorkType>(workTypeRepository){
    fun getOrCreateByName(name: String): WorkType {
        return workTypeRepository
            .findByNameIgnoreCase(name)
            .orElseGet{ workTypeRepository.save(WorkType(name)) }
    }
}