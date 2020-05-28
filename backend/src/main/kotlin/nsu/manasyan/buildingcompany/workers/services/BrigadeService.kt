package nsu.manasyan.buildingcompany.workers.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.workers.model.Brigade
import nsu.manasyan.buildingcompany.workers.repositories.BrigadeRepository
import org.springframework.stereotype.Service

@Service
class BrigadeService(private val brigadeRepository: BrigadeRepository) : AbstractCrudService<Brigade>(brigadeRepository)