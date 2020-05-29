package nsu.manasyan.buildingcompany.security.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.security.dto.PermissionDto
import nsu.manasyan.buildingcompany.security.mappers.PermissionMapper
import nsu.manasyan.buildingcompany.security.model.Permission
import nsu.manasyan.buildingcompany.security.repositories.PermissionFilter
import nsu.manasyan.buildingcompany.security.services.PermissionsService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/permissions")
class PermissionsController(
    private val permissionsService: PermissionsService,
    private val permissionMapper: PermissionMapper
) : AbstractCrudController<Permission, PermissionDto>(permissionsService, permissionMapper, "Permission") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: PermissionFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/by-role")
    fun getByRole(@RequestParam roleId: Int, params: FindRequestParameters?): PageDto<*> {
        return mapper.toPageDto(permissionsService.getByRole(roleId, params))
    }
}