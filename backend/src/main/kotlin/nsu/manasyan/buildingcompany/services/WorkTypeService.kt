package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.WorkType
import nsu.manasyan.buildingcompany.repositories.WorkTypeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class WorkTypeService(private val workTypeRepository: WorkTypeRepository) : AbstractCrudService<WorkType>(workTypeRepository){
    fun getByName(name: String): WorkType {
        return workTypeRepository
            .getByName(name)
            .orElseThrow{ IllegalArgumentException("Wrong work type name!") }
    }
}