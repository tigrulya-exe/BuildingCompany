package nsu.manasyan.buildingcompany.security.controllers

import nsu.manasyan.buildingcompany.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.dto.mappers.UserMapper
import nsu.manasyan.buildingcompany.security.model.AuthorizationTokensDto
import nsu.manasyan.buildingcompany.security.model.Credentials
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.model.UserDto
import nsu.manasyan.buildingcompany.security.services.UsersService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/users")
class UsersController(
    private val usersService: UsersService,
    private val userMapper: UserMapper
) : AbstractCrudController<User, UserDto>(usersService, userMapper, "user"){

    @PostMapping("/sign-up")
    override fun addEntity(@RequestBody dto: UserDto) {
        super.addEntity(dto)
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody credentials: Credentials) : AuthorizationTokensDto{
        return usersService.authenticate(credentials)
    }

}