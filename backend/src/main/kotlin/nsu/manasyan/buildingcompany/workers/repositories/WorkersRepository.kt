package nsu.manasyan.buildingcompany.workers.repositories

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import nsu.manasyan.buildingcompany.workers.model.Worker
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


    @Query(
        """
        select distinct w   
        from Worker w 
        join BrigadeObjectWork bow on w.brigade.id = bow.brigade.id
        where bow.buildingObject.id = :buildingObjectId
    """
    )
    fun findDistinctByBuildingObject(buildingObjectId: Int, pageable: Pageable): Page<Worker>
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
