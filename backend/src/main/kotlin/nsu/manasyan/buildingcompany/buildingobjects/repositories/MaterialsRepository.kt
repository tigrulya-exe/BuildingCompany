package nsu.manasyan.buildingcompany.buildingobjects.repositories

import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.buildingobjects.model.Material
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkType
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MaterialsRepository :
    JpaFilterRepository<Material, Int> {
    @Query(
        """
        select m   
        from Material m
        where (:#{#filter.name} is null or lower(m.name) like :#{#filter.name})
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<in Material>?,
        pageable: Pageable
    ): Page<Material>

    @Query("""
        select m
        from Material m join Outlay o 
            on m = o.material
        join OutlayExceedance oe
            on o = oe.outlayRow
        left join Area a 
            on a = o.buildingObject.area
        where (:areaId is null or a.id = :areaId)
        and (:managementId is null or a.management.id = :managementId)
    """)
    fun findByAreaManagementDelay(areaId: Int?, managementId: Int?, pageable: Pageable) : Page<Material>
}

class MaterialFilter : Filter<Material> {
    var name: String? by FilterStringDelegate()
}