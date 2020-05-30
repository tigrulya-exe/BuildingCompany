package nsu.manasyan.buildingcompany.security.repositories

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.security.model.Permission
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

interface PermissionRepository : JpaFilterRepository<Permission, Int> {
    @Query(
        """
        select r
        from Permission r
        where (:#{#filter.name} is null or lower(r.stringRepresentation) like :#{#filter.name})
    """
    )
    override fun findAllByFilter(filter: Filter<in Permission>?, pageable: Pageable): Page<Permission>

    @Query(
        """
        select p
        from Permission p join UserRole r
        on r member p.roles
        where :roleId = r.id
    """
    )
    fun findByRole(roleId: Int, pageable: Pageable): Page<Permission>
}

@NoArgConstructor
class PermissionFilter : Filter<Permission> {
    var name: String? by FilterStringDelegate()
}