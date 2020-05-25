package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.workers.Foreman
import nsu.manasyan.buildingcompany.model.workers.Master
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TechnicalSpecialistsRepository : JpaFilterRepository<TechnicalSpecialist, Int> {

    @Query(
        """
        select ts
        from TechnicalSpecialist ts left join Area a on ts.area = a
        where (:#{#filter.areaId} is null or :#{#filter.areaId} = a.id)
        and (:#{#filter.managementId} is null or :#{#filter.areaId} = a.management.id)
        and (:#{#filter.name} is null or lower(ts.name) like :#{#filter.name})
        and (:#{#filter.surname} is null or lower(ts.surname) like :#{#filter.surname})
        and (:#{#filter.patronymic} is null or lower(ts.patronymic) like :#{#filter.patronymic}) 
        and (:#{#filter.educationalInstitution} is null or lower(ts.educationalInstitution) like :#{#filter.educationalInstitution})  
        and (:#{#filter.experienceYears} is null or ts.experienceYears = :#{#filter.experienceYears})   
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<in TechnicalSpecialist>?,
        pageable: Pageable
    ): Page<TechnicalSpecialist>

    @Query(
        """
        select ts
        from TechnicalSpecialist ts left join Area a on ts.area = a
        where a.id in :areaIds or a.management.id in :managementIds
    """
    )
    fun findByAreasOrManagements(
        areaIds: List<Int>,
        managementIds: List<Int>,
        pageable: Pageable
    ): Page<TechnicalSpecialist>

}

@Repository
interface ForemenRepository : JpaFilterRepository<Foreman, Int> {
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

@Repository
interface MastersRepository : JpaFilterRepository<Master, Int> {
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
open class TechnicalSpecialistFilter(
    var areaId: Int?,
    var managementId: Int?,
    var experienceYears: Int?
) : Filter<TechnicalSpecialist> {
    var name: String? by FilterStringDelegate()
    var surname: String? by FilterStringDelegate()
    var patronymic: String? by FilterStringDelegate()
    var educationalInstitution: String? by FilterStringDelegate()
}

class ForemanFilter(
    areaId: Int?,
    managementId: Int?,
    experienceYears: Int?,
    var knowledgeOfEnglish: Boolean?
) : TechnicalSpecialistFilter(areaId, managementId, experienceYears)

class MasterFilter(
    areaId: Int?,
    managementId: Int?,
    experienceYears: Int?
) : TechnicalSpecialistFilter(areaId, managementId, experienceYears){
    var category: String? by FilterStringDelegate()
}
