package nsu.manasyan.buildingcompany.security.listeners

import nsu.manasyan.buildingcompany.ApplicationProperties
import nsu.manasyan.buildingcompany.email.EmailService
import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.security.events.RegistrationCompleteEvent
import nsu.manasyan.buildingcompany.security.model.Token
import nsu.manasyan.buildingcompany.security.services.TokenService
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class RegistrationCompleteListener(
    private val emailService: EmailService,
    private val tokenService: TokenService,
    private val properties: ApplicationProperties
) : ApplicationListener<RegistrationCompleteEvent> {

    override fun onApplicationEvent(event: RegistrationCompleteEvent) {
        val user = event.getUser()
        try {
            val token = tokenService.generateToken(user, Token.Type.EMAIL_CONFIRM)
            val confirmUrl =
                "http://${properties.domainName}:${properties.port}/confirm?token=${token.stringRepresentation}"
            val message =
                """Hello,  ${user.fullName}!
Click on the link below to confirm your email address:
$confirmUrl""".trimIndent()
            emailService.sendMessage(user.email, "Email confirmation", message)
            logger().info("Confirm email was sent to ${user.email}")
        } catch (exc: Exception) {
            logger().error("Error sending email confirm message: ${exc.localizedMessage}")
        }
    }
}