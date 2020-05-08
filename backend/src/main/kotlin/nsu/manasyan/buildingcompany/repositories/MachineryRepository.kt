package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Machinery
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MachineryRepository : JpaFilterRepository<Machinery, Int> {
    @Query("""
        select m   
        from Machinery m
        where (:#{#filter.licencePlateNumber} is null or lower(m.licencePlateNumber) like :#{#filter.licencePlateNumber})
        and (:#{#filter.buildingObjectId} is null or m.buildingObject.id = :#{#filter.buildingObjectId})
        and (:#{#filter.areaId} is null or m.buildingObject.area.id = :#{#filter.areaId})
        and (:#{#filter.managementId} is null or m.buildingObject.area.management.id = :#{#filter.managementId})
        and (:#{#filter.type} is null or lower(m.type) like :#{#filter.type})
    """)
    override fun findAllByFilter(@Param("filter") filter: Filter<Machinery>?,
                                 pageable: Pageable): Page<Machinery>
}

@NoArgConstructor
data class MachineryFilter(
    var areaId: Int?,
    var managementId: Int?,
    var buildingObjectId: Int?
) : Filter<Machinery>{
    var type: String? by FilterStringDelegate()

    var licencePlateNumber: String? by FilterStringDelegate()
}