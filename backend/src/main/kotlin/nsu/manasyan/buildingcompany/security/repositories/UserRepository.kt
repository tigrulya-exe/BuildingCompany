package nsu.manasyan.buildingcompany.security.repositories

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : JpaFilterRepository<User, Int> {

    @Query(
        """
        select u
        from User u
        where (:#{#filter.fullName} is null or lower(u.fullName) like :#{#filter.fullName})
        and (:#{#filter.nickname} is null or lower(u.nickname) like :#{#filter.nickname})
        and (:#{#filter.email} is null or lower(u.email) like :#{#filter.email})
    """
    )
    override fun findAllByFilter(filter: Filter<in User>?, pageable: Pageable): Page<User>

    fun findByNicknameIgnoreCase(nickname: String): Optional<User>

    fun findByEmailIgnoreCase(email: String): Optional<User>
}

@NoArgConstructor
class UserFilter : Filter<User> {
    var fullName: String? by FilterStringDelegate()
    var nickname: String? by FilterStringDelegate()
    var email: String? by FilterStringDelegate()
}
