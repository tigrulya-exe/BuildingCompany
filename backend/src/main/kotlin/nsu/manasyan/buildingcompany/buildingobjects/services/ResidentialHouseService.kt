package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.buildingobjects.model.ResidentialHouse
import nsu.manasyan.buildingcompany.buildingobjects.repositories.ResidentialHousesRepository
import nsu.manasyan.buildingcompany.services.AbstractCrudService
import org.springframework.stereotype.Service

@Service
class ResidentialHouseService(repository: ResidentialHousesRepository)
    : AbstractCrudService<ResidentialHouse>(repository) {
}