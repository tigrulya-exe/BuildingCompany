package nsu.manasyan.buildingcompany.technicalstuff.repositories

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.technicalstuff.model.TechnicalSpecialist
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TechnicalSpecialistsRepository :
    JpaFilterRepository<TechnicalSpecialist, Int> {

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