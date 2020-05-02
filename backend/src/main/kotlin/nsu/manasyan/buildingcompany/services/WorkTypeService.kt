package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.WorkType
import nsu.manasyan.buildingcompany.repositories.WorkTypeRepository
import org.springframework.stereotype.Service

@Service
class WorkTypeService(workTypeRepository: WorkTypeRepository) : AbstractCrudService<WorkType>(workTypeRepository)