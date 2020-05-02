package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.ConstructionManagement
import nsu.manasyan.buildingcompany.repositories.ConstructionManagementsRepository
import org.springframework.stereotype.Service

@Service
class ConstructionManagementService(managementsRepository: ConstructionManagementsRepository)
    : AbstractCrudService<ConstructionManagement>(managementsRepository)