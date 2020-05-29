package nsu.manasyan.buildingcompany.security.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.buildingobjects.dto.IdListDto
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BuildingObjectFilter
import nsu.manasyan.buildingcompany.security.mappers.UserMapper
import nsu.manasyan.buildingcompany.security.dto.AuthorizationTokensDto
import nsu.manasyan.buildingcompany.security.model.Credentials
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.dto.UserDto
import nsu.manasyan.buildingcompany.security.repositories.UserFilter
import nsu.manasyan.buildingcompany.security.services.UsersService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${application.path}/users")
class UsersController(
    private val usersService: UsersService,
    private val userMapper: UserMapper
) : AbstractCrudController<User, UserDto>(usersService, userMapper, "user") {

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: UserFilter?, params: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, params)
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    @PostMapping("/{userId}/roles")
    fun addRoles(@RequestBody idListDto: IdListDto, @PathVariable userId: Int){
        usersService.addRoles(idListDto.ids, userId)
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{userId}/roles/{roleId}")
    fun removeWorkType(@PathVariable userId: Int, @PathVariable roleId: Int){
        usersService.removeRole(userId, roleId)
    }

}