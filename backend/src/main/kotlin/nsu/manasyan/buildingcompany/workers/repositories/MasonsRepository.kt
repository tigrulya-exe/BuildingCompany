package nsu.manasyan.buildingcompany.workers.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.workers.model.Mason
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MasonsRepository : JpaFilterRepository<Mason, Int> {

    @Query(
        """
        select w   
        from Mason w
        where (:#{#filter.name} is null or lower(w.name) like :#{#filter.name})
        and (:#{#filter.surname} is null or lower(w.surname) like :#{#filter.surname})
        and (:#{#filter.patronymic} is null or lower(w.patronymic) like :#{#filter.patronymic}) 
        and (:#{#filter.experienceYears} is null or w.experienceYears = :#{#filter.experienceYears})   
        and (:#{#filter.brigadeId} is null or w.brigade.id = :#{#filter.brigadeId})
        and (:#{#filter.minBricksPerHour} is null or w.bricksPerHour >= :#{#filter.minBricksPerHour})
    """
    )
    override fun findAllByFilter(filter: Filter<in Mason>?, pageable: Pageable): Page<Mason>
}

@NoArgConstructor
class MasonFilter(
    brigadeId: Int?,
    experienceYears: Int?,
    var minBricksPerHour: Int?
) : WorkerFilter(brigadeId, experienceYears)