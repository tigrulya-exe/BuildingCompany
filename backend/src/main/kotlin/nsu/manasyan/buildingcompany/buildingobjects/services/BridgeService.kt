package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.buildingobjects.model.Bridge
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BridgesRepository
import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import org.springframework.stereotype.Service

@Service
class BridgeService(repository: BridgesRepository) : AbstractCrudService<Bridge>(repository)