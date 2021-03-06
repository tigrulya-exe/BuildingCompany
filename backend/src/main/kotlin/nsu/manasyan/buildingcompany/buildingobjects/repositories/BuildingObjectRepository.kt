package nsu.manasyan.buildingcompany.buildingobjects.repositories

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface BuildingObjectRepository :
    JpaFilterRepository<BuildingObject, Int> {
    @Query(
        """
        select b   
        from BuildingObject b
        where (:#{#filter.areaId} is null or b.area.id = :#{#filter.areaId})
        and (:#{#filter.customerId} is null or b.customer.id = :#{#filter.customerId})
        and (:#{#filter.name} is null or lower(b.name) like :#{#filter.name})
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<in BuildingObject>?,
        pageable: Pageable
    ): Page<BuildingObject>

    @Query(
        """
        select b
        from BuildingObject b left join Area a on b.area = a
        where a.id in :areaIds or a.management.id in :managementIds
    """
    )
    fun findByAreasOrManagements(areaIds: List<Int>, managementIds: List<Int>, pageable: Pageable): Page<BuildingObject>

    @Query(
        """
        select b
        from BuildingObject b
        join Area a on a = b.area
        join BrigadeObjectWork bow on bow.buildingObject = b
        join WorkSchedule ws on ws.brigadeWork = bow
        where (coalesce(:managementIds, :managementIds) is null or a.management.id in :managementIds)
        and (coalesce(:workTypeIds, :workTypeIds) is null or bow.workType.id in :workTypeIds)
        and (coalesce(:startDateMin, :startDateMin) is null or ws.startDate >= :startDateMin)
        and (coalesce(:startDateMax, :startDateMax) is null or ws.startDate <= :startDateMax)
    """
    )
    fun findByManagementsOrWorkTypes(
        managementIds: List<Int>?,
        startDateMin: Date?,
        startDateMax: Date?,
        workTypeIds: List<Int>?,
        pageable: Pageable
    ): Page<BuildingObject>

}

@NoArgConstructor
open class BuildingObjectFilter(
    var areaId: Int?,
    var customerId: Int?
) : Filter<BuildingObject> {
    var name: String? by FilterStringDelegate()
}