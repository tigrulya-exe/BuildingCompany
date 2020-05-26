package nsu.manasyan.buildingcompany.workers.services

import nsu.manasyan.buildingcompany.services.AbstractCrudService
import nsu.manasyan.buildingcompany.workers.model.Locksmith
import nsu.manasyan.buildingcompany.workers.repositories.LocksmithsRepository
import org.springframework.stereotype.Service

@Service
class LocksmithsService(repository: LocksmithsRepository) : AbstractCrudService<Locksmith>(repository) {
}