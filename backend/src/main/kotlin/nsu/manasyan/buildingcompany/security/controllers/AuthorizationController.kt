package nsu.manasyan.buildingcompany.security.controllers

import nsu.manasyan.buildingcompany.dto.mappers.UserMapper
import nsu.manasyan.buildingcompany.security.model.AuthorizationTokensDto
import nsu.manasyan.buildingcompany.security.model.Credentials
import nsu.manasyan.buildingcompany.security.model.UserDto
import nsu.manasyan.buildingcompany.security.services.UsersService
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@RestController
class AuthorizationController(
    private val usersService: UsersService,
    private val userMapper: UserMapper
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody dto: UserDto) {
        usersService.signUp(userMapper.toEntity(dto))
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody credentials: Credentials) : AuthorizationTokensDto {
        return usersService.authenticate(credentials)
    }

    @GetMapping("/confirm")
    fun confirmEmail(@RequestParam token: String){
        usersService.confirmEmail(token)
        val modelAndView = ModelAndView()
        modelAndView.viewName = "/emailConfirmSuccess.html"

    }
}