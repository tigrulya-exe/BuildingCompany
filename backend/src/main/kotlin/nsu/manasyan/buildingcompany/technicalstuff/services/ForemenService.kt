package nsu.manasyan.buildingcompany.technicalstuff.services

import nsu.manasyan.buildingcompany.services.AbstractCrudService
import nsu.manasyan.buildingcompany.technicalstuff.model.Foreman
import nsu.manasyan.buildingcompany.technicalstuff.repositories.ForemenRepository
import org.springframework.stereotype.Service

@Service
class ForemenService(repository: ForemenRepository) : AbstractCrudService<Foreman>(repository)