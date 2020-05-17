package nsu.manasyan.buildingcompany.email

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class LocalEmailService(
    private val emailSender: JavaMailSender
) : EmailService {

    override fun sendMessage(to: Array<String>, subject: String, text: String) {
        val message = SimpleMailMessage()
        message.setTo(*to)
        message.setSubject(subject)
        message.setText(text)
        emailSender.send(message)
    }

    override fun sendMessage(to: String, subject: String, body: String) {
        sendMessage(arrayOf(to), subject, body)
    }
}