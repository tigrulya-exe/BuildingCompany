package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.repositories.ForemenRepository
import nsu.manasyan.buildingcompany.repositories.MastersRepository
import nsu.manasyan.buildingcompany.repositories.TechnicalSpecialistsRepository
import org.springframework.stereotype.Service

@Service
class TechnicalSpecialistsService(
    technicksRepository: TechnicalSpecialistsRepository,
    private val foremenRepository: ForemenRepository,
    private val mastersRepository: MastersRepository
) : AbstractCrudService<TechnicalSpecialist>(technicksRepository)