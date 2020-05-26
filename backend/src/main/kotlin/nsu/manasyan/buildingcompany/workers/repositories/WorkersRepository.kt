package nsu.manasyan.buildingcompany.workers.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.workers.model.Worker
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface WorkersRepository :
    JpaFilterRepository<Worker, Int> {
    @Query(
        """
        select w   
        from Worker w
        where (:#{#filter.name} is null or lower(w.name) like :#{#filter.name})
        and (:#{#filter.surname} is null or lower(w.surname) like :#{#filter.surname})
        and (:#{#filter.patronymic} is null or lower(w.patronymic) like :#{#filter.patronymic}) 
        and (:#{#filter.experienceYears} is null or w.experienceYears = :#{#filter.experienceYears})   
        and (:#{#filter.brigadeId} is null or w.brigade.id = :#{#filter.brigadeId})
    """
    )
    override fun findAllByFilter(filter: Filter<in Worker>?, pageable: Pageable): Page<Worker>
}

@NoArgConstructor
open class WorkerFilter(
    var brigadeId: Int?,
    var experienceYears: Int?
) : Filter<Worker> {
    var name: String? by FilterStringDelegate()
    var surname: String? by FilterStringDelegate()
    var patronymic: String? by FilterStringDelegate()
}