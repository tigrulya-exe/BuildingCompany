package nsu.manasyan.buildingcompany.buildingobjects.repositories

import nsu.manasyan.buildingcompany.buildingobjects.model.ResidentialHouse
import nsu.manasyan.buildingcompany.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ResidentialHousesRepository : JpaFilterRepository<ResidentialHouse, Int> {
    @Query(
        """
        select r   
        from ResidentialHouse r
        where (:#{#filter.areaId} is null or r.area.id = :#{#filter.areaId})
        and (:#{#filter.customerId} is null or r.customer.id = :#{#filter.customerId})
        and (:#{#filter.name} is null or lower(r.name) like :#{#filter.name})
        and (:#{#filter.minFlatCount} is null or r.flatCount >= :#{#filter.minFlatCount})
        and (:#{#filter.maxFlatCount} is null or r.flatCount <= :#{#filter.maxFlatCount})
        and (:#{#filter.minFloorCount} is null or r.floorCount >= :#{#filter.minFloorCount})
        and (:#{#filter.maxFloorCount} is null or r.floorCount <= :#{#filter.maxFloorCount})
    """
    )
    override fun findAllByFilter(filter: Filter<in ResidentialHouse>?, pageable: Pageable): Page<ResidentialHouse>
}

class ResidentialHouseFilter(
    areaId: Int?,
    customerId: Int?,
    var minFlatCount: Int?,
    var maxFlatCount: Int?,
    var minFloorCount: Int?,
    var maxFloorCount: Int?
) : BuildingObjectFilter(areaId, customerId)
