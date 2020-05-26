package nsu.manasyan.buildingcompany.workers.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.workers.model.Locksmith
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

interface LocksmithsRepository : JpaFilterRepository<Locksmith, Int> {
    @Query(
        """
        select w   
        from Locksmith w
        where (:#{#filter.name} is null or lower(w.name) like :#{#filter.name})
        and (:#{#filter.surname} is null or lower(w.surname) like :#{#filter.surname})
        and (:#{#filter.patronymic} is null or lower(w.patronymic) like :#{#filter.patronymic}) 
        and (:#{#filter.experienceYears} is null or w.experienceYears = :#{#filter.experienceYears})   
        and (:#{#filter.brigadeId} is null or w.brigade.id = :#{#filter.brigadeId})
        and (:#{#filter.category} is null or w.category = :#{#filter.category})
        and (:#{#filter.higherEducation} is null or w.higherEducation = :#{#filter.higherEducation}) 
    """
    )
    override fun findAllByFilter(filter: Filter<in Locksmith>?, pageable: Pageable): Page<Locksmith>
}

@NoArgConstructor
class LocksmithFilter(
    brigadeId: Int?,
    experienceYears: Int?,
    var category: Int?,
    var higherEducation: Boolean?
) : WorkerFilter(brigadeId, experienceYears)