package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.repositories.ForemenRepository
import nsu.manasyan.buildingcompany.repositories.MastersRepository
import nsu.manasyan.buildingcompany.repositories.TechnicalSpecialistsRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.getPageable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class TechnicalSpecialistsService(
    private val technicksRepository: TechnicalSpecialistsRepository,
    private val foremenRepository: ForemenRepository,
    private val mastersRepository: MastersRepository
) : AbstractCrudService<TechnicalSpecialist>(technicksRepository){

    fun findAllByFilter(filter: Filter<TechnicalSpecialist>?, params: FindRequestParameters?): Page<TechnicalSpecialist>{
        val pageable = getPageable(params, Sort.unsorted())
        return technicksRepository.findAllByFilter(filter, pageable)
    }
}