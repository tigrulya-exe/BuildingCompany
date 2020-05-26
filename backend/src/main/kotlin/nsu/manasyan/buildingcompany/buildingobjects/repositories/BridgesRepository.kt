package nsu.manasyan.buildingcompany.buildingobjects.repositories

import nsu.manasyan.buildingcompany.buildingobjects.model.Bridge
import nsu.manasyan.buildingcompany.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BridgesRepository : JpaFilterRepository<Bridge, Int> {
    @Query(
        """
        select b   
        from Bridge b
        where (:#{#filter.areaId} is null or b.area.id = :#{#filter.areaId})
        and (:#{#filter.customerId} is null or b.customer.id = :#{#filter.customerId})
        and (:#{#filter.name} is null or lower(b.name) like :#{#filter.name})
        and (:#{#filter.typeOfSpan} is null or lower(b.typeOfSpan) like :#{#filter.typeOfSpan})
        and (:#{#filter.minWidthInMetres} is null or b.widthInMetres >= :#{#filter.minWidthInMetres})
        and (:#{#filter.maxWidthInMetres} is null or b.widthInMetres <= :#{#filter.maxWidthInMetres})
        and (:#{#filter.minNumberOfTrafficLanes} is null or b.numberOfTrafficLanes >= :#{#filter.minNumberOfTrafficLanes})
        and (:#{#filter.maxNumberOfTrafficLanes} is null or b.numberOfTrafficLanes <= :#{#filter.maxNumberOfTrafficLanes})
    """
    )
    override fun findAllByFilter(filter: Filter<in Bridge>?, pageable: Pageable): Page<Bridge>
}

class BridgeFilter(
    areaId: Int?,
    customerId: Int?,
    var minWidthInMetres: Float?,
    var maxWidthInMetres: Float?,
    var minNumberOfTrafficLanes: Int?,
    var maxNumberOfTrafficLanes: Int?
) : BuildingObjectFilter(areaId, customerId){
    var typeOfSpan by FilterStringDelegate()
}