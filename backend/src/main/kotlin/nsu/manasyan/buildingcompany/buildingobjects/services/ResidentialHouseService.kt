package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.ResidentialHouse
import nsu.manasyan.buildingcompany.buildingobjects.repositories.ResidentialHousesRepository
import org.springframework.stereotype.Service

@Service
class ResidentialHouseService(repository: ResidentialHousesRepository) :
    AbstractCrudService<ResidentialHouse>(repository) {

    override fun updateEntity(entity: ResidentialHouse) {
        val building = getEntity(entity.id!!)
        entity.workTypes = building.workTypes
        super.updateEntity(entity)
    }
}