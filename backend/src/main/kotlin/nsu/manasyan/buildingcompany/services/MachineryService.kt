package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.Machinery
import nsu.manasyan.buildingcompany.repositories.MachineryRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class MachineryService(private val machineryRepository: MachineryRepository) :
    AbstractCrudService<Machinery>(machineryRepository){
}