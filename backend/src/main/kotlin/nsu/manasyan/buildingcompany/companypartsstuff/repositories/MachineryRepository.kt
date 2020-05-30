package nsu.manasyan.buildingcompany.companypartsstuff.repositories

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.companypartsstuff.model.Machinery
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MachineryRepository :
    JpaFilterRepository<Machinery, Int> {
    @Query(
        """
        select m   
        from Machinery m left join Area a on a = m.buildingObject.area
        where (:#{#filter.licencePlateNumber} is null or lower(m.licencePlateNumber) like :#{#filter.licencePlateNumber})
        and (:#{#filter.areaId} is null or a.id = :#{#filter.areaId})
        and (:#{#filter.managementId} is null or a.management.id = :#{#filter.managementId})
        and (:#{#filter.buildingObjectId} is null or m.buildingObject.id = :#{#filter.buildingObjectId})
        and (:#{#filter.type} is null or lower(m.type) like :#{#filter.type})
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<in Machinery>?,
        pageable: Pageable
    ): Page<Machinery>


    @Query(
        """
        select m 
        from Machinery m left join WorkSchedule ws on m member ws.machinery
        where (:buildingObjectId is null or m.buildingObject.id = :buildingObjectId)
        and (coalesce(:startDateMin, :startDateMin) is null or ws.startDate >= :startDateMin)
        and (coalesce(:startDateMax, :startDateMax) is null or ws.startDate <= :startDateMax)
    """
    )
    fun findByBuildingObjectOrWorkSchedule(
        startDateMin: Date?,
        startDateMax: Date?,
        buildingObjectId: Int?,
        pageable: Pageable
    ): Page<Machinery>

    @Query(
        """
        select m 
        from Machinery m join ConstructionManagement cm on m.buildingObject.area.management.id = cm.id
        where cm.id in :constructionManagementIds
    """
    )
    fun findByConstructionManagements(constructionManagementIds: List<Int>, pageable: Pageable): Page<Machinery>
}

@NoArgConstructor
data class MachineryFilter(
    var areaId: Int?,
    var managementId: Int?,
    var buildingObjectId: Int?
) : Filter<Machinery> {
    var type: String? by FilterStringDelegate()

    var licencePlateNumber: String? by FilterStringDelegate()
}