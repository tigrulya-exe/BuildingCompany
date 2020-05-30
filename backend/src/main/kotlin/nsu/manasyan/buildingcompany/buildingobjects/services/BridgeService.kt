package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.Bridge
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BridgesRepository
import org.springframework.stereotype.Service

@Service
class BridgeService(repository: BridgesRepository) : AbstractCrudService<Bridge>(repository) {
    override fun updateEntity(entity: Bridge) {
        val building = getEntity(entity.id!!)
        entity.workTypes = building.workTypes
        super.updateEntity(entity)
    }
}