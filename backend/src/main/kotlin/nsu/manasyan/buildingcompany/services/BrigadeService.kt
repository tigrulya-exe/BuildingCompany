package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.workers.Brigade
import nsu.manasyan.buildingcompany.repositories.BrigadeRepository
import org.springframework.stereotype.Service

@Service
class BrigadeService(private val brigadeRepository: BrigadeRepository) : AbstractCrudService<Brigade>(brigadeRepository)