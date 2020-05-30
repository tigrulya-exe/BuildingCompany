package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.security.model.UserRole
import nsu.manasyan.buildingcompany.security.repositories.RoleRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RolesService(
    private val roleRepository: RoleRepository,
    private val permissionsService: PermissionsService
) : AbstractCrudService<UserRole>(roleRepository) {

    @Transactional
    override fun deleteEntity(id: Int) {
        val entity = getEntity(id)
        entity.users.forEach { it.roles.remove(entity) }
        super.deleteEntity(id)
    }

    @Transactional
    fun addPermissions(ids: List<Int>, roleId: Int) {
        val permissions = ids.map { permissionsService.getEntity(it) }
        val role = getEntity(roleId)
        role.permissions.addAll(permissions)
    }

    @Transactional
    fun removePermission(roleId: Int, permissionId: Int) {
        val role = getEntity(roleId)
        role.permissions.removeIf { it.id == permissionId }
    }

    @Transactional
    fun getByUser(userId: Int, params: FindRequestParameters?): Page<UserRole> {
        val pageable = getPageable(params)
        return roleRepository.findByUser(userId, pageable)
    }
}