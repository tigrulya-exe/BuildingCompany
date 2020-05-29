package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.security.model.Permission
import nsu.manasyan.buildingcompany.security.repositories.PermissionRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class PermissionsService(
    private val permissionRepository: PermissionRepository
) : AbstractCrudService<Permission>(permissionRepository) {

    fun getByRole(roleId: Int, params: FindRequestParameters?): Page<Permission> {
        val pageable = getPageable(params)
        return permissionRepository.findByRole(roleId, pageable)
    }
}