package nsu.manasyan.buildingcompany.technicalstuff.services

import nsu.manasyan.buildingcompany.technicalstuff.model.Master
import nsu.manasyan.buildingcompany.technicalstuff.model.TechnicalSpecialist
import nsu.manasyan.buildingcompany.technicalstuff.repositories.MastersRepository
import nsu.manasyan.buildingcompany.services.AbstractCrudService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class MastersService(repository: MastersRepository) : AbstractCrudService<Master>(repository)