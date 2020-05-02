package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.BuildingObject
import nsu.manasyan.buildingcompany.repositories.BuildingObjectRepository
import org.springframework.stereotype.Service

@Service
class BuildingObjectService(objectRepository: BuildingObjectRepository) :
    AbstractCrudService<BuildingObject>(objectRepository)