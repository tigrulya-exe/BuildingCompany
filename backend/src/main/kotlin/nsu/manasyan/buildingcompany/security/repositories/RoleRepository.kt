package nsu.manasyan.buildingcompany.security.repositories

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.security.model.Permission
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.model.UserRole
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.management.relation.Role

interface RoleRepository : JpaFilterRepository<UserRole, Int> {

    @Query("""
        select r
        from UserRole r
        where (:#{#filter.role} is null or lower(r.role) like :#{#filter.role})
    """)
    override fun findAllByFilter(filter: Filter<in UserRole>?, pageable: Pageable): Page<UserRole>

    fun findByRole(role: UserRole.Role): UserRole

    @Query("""
        select r
        from UserRole r join User u
        on u member r.users
        where :userId = u.id
    """)
    fun findByUser(userId: Int, pageable: Pageable): Page<UserRole>
}

@NoArgConstructor
class RoleFilter : Filter<UserRole> {
    var role: String? by FilterStringDelegate()
}

