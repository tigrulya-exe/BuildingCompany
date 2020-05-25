package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.workers.Master
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.repositories.MastersRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class MastersService(repository: MastersRepository) : AbstractCrudService<Master>(repository) {
    fun findAllByFilter(
        filter: Filter<TechnicalSpecialist>?,
        params: FindRequestParameters?
    ): Page<Master> {
        val pageable = getPageable(params)
        return repository.findAllByFilter(filter, pageable)
    }
}