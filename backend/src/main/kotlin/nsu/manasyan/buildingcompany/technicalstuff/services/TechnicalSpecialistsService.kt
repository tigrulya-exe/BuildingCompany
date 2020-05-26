package nsu.manasyan.buildingcompany.technicalstuff.services

import nsu.manasyan.buildingcompany.services.AbstractCrudService
import nsu.manasyan.buildingcompany.technicalstuff.model.TechnicalSpecialist
import nsu.manasyan.buildingcompany.technicalstuff.repositories.ForemenRepository
import nsu.manasyan.buildingcompany.technicalstuff.repositories.MastersRepository
import nsu.manasyan.buildingcompany.technicalstuff.repositories.TechnicalSpecialistsRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class TechnicalSpecialistsService(
    private val technicksRepository: TechnicalSpecialistsRepository,
    private val foremenRepository: ForemenRepository,
    private val mastersRepository: MastersRepository
) : AbstractCrudService<TechnicalSpecialist>(technicksRepository) {

    fun findByAreasOrManagements(
        areaIds: List<Int>,
        managementIds: List<Int>,
        params: FindRequestParameters?
    ): Page<TechnicalSpecialist> {
        val pageable = getPageable(params)
        return technicksRepository.findByAreasOrManagements(areaIds, managementIds, pageable)
    }
}