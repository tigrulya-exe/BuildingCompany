package nsu.manasyan.buildingcompany.security.listeners

import nsu.manasyan.buildingcompany.ApplicationProperties
import nsu.manasyan.buildingcompany.email.EmailService
import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.security.events.PasswordRestoreEvent
import nsu.manasyan.buildingcompany.security.events.RegistrationCompleteEvent
import nsu.manasyan.buildingcompany.security.model.Token
import nsu.manasyan.buildingcompany.security.services.TokenService
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class PasswordRestoreListener(
    private val emailService: EmailService,
    private val tokenService: TokenService,
    private val properties: ApplicationProperties
) : ApplicationListener<PasswordRestoreEvent> {

    override fun onApplicationEvent(event: PasswordRestoreEvent) {
        val user = event.getUser()
        try{
            val token = tokenService.generateToken(user, Token.Type.PASSWORD_RESTORE)
            val confirmUrl = "http://${properties.domainName}:${properties.port}/restore/${token.stringRepresentation}"
            val message =
                """Hello,  ${user.fullName}!
Click on the link below to change your password:
$confirmUrl""".trimIndent()
            emailService.sendMessage(user.email, "Password change", message)
            logger().info("Change password message was sent to ${user.email}")
        } catch (exc: Exception){
            logger().error("Error sending password change message: ${exc.localizedMessage}")
        }
    }
}