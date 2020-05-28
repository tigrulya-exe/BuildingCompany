package nsu.manasyan.buildingcompany.technicalstuff.repositories

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.technicalstuff.model.Master
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MastersRepository :
    JpaFilterRepository<Master, Int> {
    @Query(
        """
        select ts
        from Master ts left join Area a on ts.area = a
        where (:#{#filter.areaId} is null or :#{#filter.areaId} = a.id)
        and (:#{#filter.managementId} is null or :#{#filter.areaId} = a.management.id)
        and (:#{#filter.name} is null or lower(ts.name) like :#{#filter.name})
        and (:#{#filter.surname} is null or lower(ts.surname) like :#{#filter.surname})
        and (:#{#filter.patronymic} is null or lower(ts.patronymic) like :#{#filter.patronymic}) 
        and (:#{#filter.educationalInstitution} is null or lower(ts.educationalInstitution) like :#{#filter.educationalInstitution})  
        and (:#{#filter.experienceYears} is null or ts.experienceYears = :#{#filter.experienceYears})
        and (:#{#filter.category} is null or ts.category = :#{#filter.category})
    """
    )
    override fun findAllByFilter(filter: Filter<in Master>?, pageable: Pageable): Page<Master>
}

@NoArgConstructor
class MasterFilter(
    areaId: Int?,
    managementId: Int?,
    experienceYears: Int?
) : TechnicalSpecialistFilter(areaId, managementId, experienceYears) {
    var category: String? by FilterStringDelegate()
}