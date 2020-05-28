package nsu.manasyan.buildingcompany.workers.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.workers.model.Mason
import nsu.manasyan.buildingcompany.workers.repositories.MasonsRepository
import org.springframework.stereotype.Service

@Service
class MasonsService(repository: MasonsRepository) : AbstractCrudService<Mason>(repository) {
}