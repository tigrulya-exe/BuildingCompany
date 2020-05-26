package nsu.manasyan.buildingcompany.technicalstuff.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.technicalstuff.model.Foreman
import nsu.manasyan.buildingcompany.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ForemenRepository :
    JpaFilterRepository<Foreman, Int> {
    @Query(
        """
        select ts
        from Foreman ts left join Area a on ts.area = a
        where (:#{#filter.areaId} is null or :#{#filter.areaId} = a.id)
        and (:#{#filter.managementId} is null or :#{#filter.areaId} = a.management.id)
        and (:#{#filter.name} is null or lower(ts.name) like :#{#filter.name})
        and (:#{#filter.surname} is null or lower(ts.surname) like :#{#filter.surname})
        and (:#{#filter.patronymic} is null or lower(ts.patronymic) like :#{#filter.patronymic}) 
        and (:#{#filter.educationalInstitution} is null or lower(ts.educationalInstitution) like :#{#filter.educationalInstitution})  
        and (:#{#filter.experienceYears} is null or ts.experienceYears = :#{#filter.experienceYears})
        and (:#{#filter.knowledgeOfEnglish} is null or ts.knowledgeOfEnglish = :#{#filter.knowledgeOfEnglish})
    """
    )
    override fun findAllByFilter(filter: Filter<in Foreman>?, pageable: Pageable): Page<Foreman>
}

@NoArgConstructor
class ForemanFilter(
    areaId: Int?,
    managementId: Int?,
    experienceYears: Int?,
    var knowledgeOfEnglish: Boolean?
) : TechnicalSpecialistFilter(areaId, managementId, experienceYears)
