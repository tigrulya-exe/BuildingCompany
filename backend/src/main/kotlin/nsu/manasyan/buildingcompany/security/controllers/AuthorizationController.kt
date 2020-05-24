package nsu.manasyan.buildingcompany.security.controllers

import nsu.manasyan.buildingcompany.dto.mappers.UserMapper
import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.security.model.*
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
    fun signIn(@RequestBody credentials: Credentials): AuthorizationTokensDto {
        return usersService.authenticate(credentials)
    }

    @GetMapping("/confirm")
    fun confirmEmail(@RequestParam token: String): ModelAndView {
        usersService.confirmEmail(token)
        val modelAndView = ModelAndView()
        modelAndView.viewName = "/emailConfirmSuccess.html"
        return modelAndView;
    }

    @PostMapping("/refresh")
    fun refreshTokens(@RequestBody tokenDto: TokenDto): AuthorizationTokensDto {
        return usersService.updateTokens(tokenDto.token)
    }

    /**
     * 1 part
     */
    @PostMapping("/restore")
    fun restorePassword(@RequestParam email: String) {
        usersService.restorePassword(email)
    }

    /**
     * 2 part
     */
    @GetMapping("/restore/{token}")
    fun getRestorePage(@PathVariable token: String): ModelAndView? {
        usersService.validateRestoreToken(token)
        val modelAndView = ModelAndView()
        modelAndView.viewName = "/restore.html"
        logger().debug("The restore page has been queried.")
        return modelAndView
    }

    /**
     * 3 part
     */
    @PostMapping("/restore/{token}")
    fun restore(
        @RequestBody newPasswordDto: NewPasswordDto,
        @PathVariable token: String
    ) {
        val newPassword: String = newPasswordDto.newPassword
        usersService.changePassword(token, newPassword)
    }
}