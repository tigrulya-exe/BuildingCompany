package nsu.manasyan.buildingcompany.technicalstuff.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.technicalstuff.model.Master
import nsu.manasyan.buildingcompany.technicalstuff.repositories.MastersRepository
import org.springframework.stereotype.Service

@Service
class MastersService(repository: MastersRepository) : AbstractCrudService<Master>(repository)