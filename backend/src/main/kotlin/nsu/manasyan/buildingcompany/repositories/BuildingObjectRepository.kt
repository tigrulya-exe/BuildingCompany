package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.BuildingObject
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface BuildingObjectRepository : JpaFilterRepository<BuildingObject, Int> {
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
        @Param("filter") filter: Filter<BuildingObject>?,
        pageable: Pageable
    ): Page<BuildingObject>

    @Query("""
        select b
        from BuildingObject b left join Area a on b.area = a
        where a.id in :areaIds or a.management.id in :managementIds
    """)
    fun findByAreasOrManagements(areaIds: List<Int>, managementIds: List<Int>, pageable: Pageable): Page<BuildingObject>
}

@NoArgConstructor
data class BuildingObjectFilter(
    var areaId: Int?,
    var customerId: Int?
) : Filter<BuildingObject> {
    var name: String? by FilterStringDelegate()
}