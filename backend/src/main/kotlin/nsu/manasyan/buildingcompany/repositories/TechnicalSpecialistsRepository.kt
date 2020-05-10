package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.workers.Foreman
import nsu.manasyan.buildingcompany.model.workers.Master
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TechnicalSpecialistsRepository : JpaFilterRepository<TechnicalSpecialist, Int> {

    @Query(
        """
        select ts   
        from TechnicalSpecialist ts
        where (:#{#filter.areaId} = ts.area.id or :#{#filter.managementId} = ts.area.management.id)
        and (:#{#filter.name} is null or lower(ts.name) like :#{#filter.name})
        and (:#{#filter.surname} is null or lower(ts.surname) like :#{#filter.surname})
        and (:#{#filter.patronymic} is null or lower(ts.patronymic) like :#{#filter.patronymic}) 
        and (:#{#filter.educationalInstitution} is null or lower(ts.educationalInstitution) like :#{#filter.educationalInstitution})  
        and (:#{#filter.experienceYears} is null or ts.experienceYears = :#{#filter.experienceYears})   
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<TechnicalSpecialist>?,
        pageable: Pageable
    ): Page<TechnicalSpecialist>
}

@Repository
interface ForemenRepository : JpaRepository<Foreman, Int>

@Repository
interface MastersRepository : JpaRepository<Master, Int>

@NoArgConstructor
data class TechnicalSpecialistFilter(
    var areaId: Int?,
    var managementId: Int?,
    var experienceYears: Int?
) : Filter<TechnicalSpecialist> {
    var name: String? by FilterStringDelegate()
    var surname: String? by FilterStringDelegate()
    var patronymic: String? by FilterStringDelegate()
    var educationalInstitution: String? by FilterStringDelegate()
}
