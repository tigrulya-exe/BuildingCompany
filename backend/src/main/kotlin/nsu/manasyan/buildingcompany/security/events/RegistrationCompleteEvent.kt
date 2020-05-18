package nsu.manasyan.buildingcompany.security.events

import nsu.manasyan.buildingcompany.security.model.User
import org.springframework.context.ApplicationEvent

class RegistrationCompleteEvent(user: User) : ApplicationEvent(user) {
    fun getUser() = source as User
}