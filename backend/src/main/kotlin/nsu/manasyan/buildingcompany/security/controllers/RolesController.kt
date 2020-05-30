package nsu.manasyan.buildingcompany.security.controllers

import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.buildingobjects.dto.IdListDto
import nsu.manasyan.buildingcompany.security.dto.RoleDto
import nsu.manasyan.buildingcompany.security.mappers.RoleMapper
import nsu.manasyan.buildingcompany.security.model.UserRole
import nsu.manasyan.buildingcompany.security.repositories.RoleFilter
import nsu.manasyan.buildingcompany.security.services.RolesService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${application.path}/roles")
class RolesController(
    private val rolesService: RolesService,
    private val roleMapper: RoleMapper
) : AbstractSecuredCrudController<UserRole, RoleDto>(rolesService, roleMapper, "Role") {
    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: RoleFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

    @PreAuthorize("hasAuthority('EDIT_USERS')")
    @PostMapping("/{roleId}/permissions")
    fun addPermissions(@RequestBody idListDto: IdListDto, @PathVariable roleId: Int) {
        rolesService.addPermissions(idListDto.ids, roleId)
    }

    @PreAuthorize("hasAuthority('EDIT_USERS')")
    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    fun removePermission(@PathVariable roleId: Int, @PathVariable permissionId: Int) {
        rolesService.removePermission(roleId, permissionId)
    }

    @PreAuthorize("hasAuthority('READ_USERS')")
    @GetMapping("/by-user")
    fun getByUser(@RequestParam userId: Int, params: FindRequestParameters?): PageDto<*> {
        return mapper.toPageDto(rolesService.getByUser(userId, params))
    }
}