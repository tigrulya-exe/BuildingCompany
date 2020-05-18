package nsu.manasyan.buildingcompany.security.events

import nsu.manasyan.buildingcompany.security.model.User
import org.springframework.context.ApplicationEvent

class PasswordRestoreEvent(user: User) : ApplicationEvent(user) {
    fun getUser() = source as User
}