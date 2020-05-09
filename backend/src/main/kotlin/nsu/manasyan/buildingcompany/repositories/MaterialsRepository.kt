package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.Material
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MaterialsRepository : JpaFilterRepository<Material, Int> {
    @Query(
        """
        select m   
        from Material m
        where (:#{#filter.name} is null or lower(m.name) like :#{#filter.name})
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<Material>?,
        pageable: Pageable
    ): Page<Material>
}

class MaterialFilter : Filter<Material> {
    var name: String? by FilterStringDelegate()
}