package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.workers.Worker
import nsu.manasyan.buildingcompany.repositories.WorkersRepository
import org.springframework.stereotype.Service

@Service
class WorkersService(workersRepository: WorkersRepository) : AbstractCrudService<Worker>(workersRepository)