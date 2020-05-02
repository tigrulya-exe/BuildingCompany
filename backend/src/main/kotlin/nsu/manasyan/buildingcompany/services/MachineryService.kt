package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.ConstructionMachinery
import nsu.manasyan.buildingcompany.repositories.MachineryRepository
import org.springframework.stereotype.Service

@Service
class MachineryService(private val machineryRepository: MachineryRepository) :
    AbstractCrudService<ConstructionMachinery>(machineryRepository)