package nsu.manasyan.buildingcompany.email

import org.springframework.mail.MailSendException

interface EmailService {
    @Throws(MailSendException::class)
    fun sendMessage(to: Array<String>, subject: String, body: String)

    fun sendMessage(to: String, subject: String, body: String)
}
