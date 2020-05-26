package nsu.manasyan.buildingcompany.workers.services

import nsu.manasyan.buildingcompany.services.AbstractCrudService
import nsu.manasyan.buildingcompany.workers.model.Worker
import nsu.manasyan.buildingcompany.workers.repositories.WorkersRepository
import org.springframework.stereotype.Service

@Service
class WorkersService(workersRepository: WorkersRepository) : AbstractCrudService<Worker>(workersRepository)