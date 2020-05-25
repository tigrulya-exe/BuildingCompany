package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.workers.Foreman
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.repositories.ForemenRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class ForemenService(repository: ForemenRepository) : AbstractCrudService<Foreman>(repository){
    fun findAllByFilter(
        filter: Filter<TechnicalSpecialist>?,
        params: FindRequestParameters?
    ): Page<Foreman> {
        val pageable = getPageable(params)
        return repository.findAllByFilter(filter, pageable)
    }
}