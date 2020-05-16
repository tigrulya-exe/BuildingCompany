package nsu.manasyan.buildingcompany.security.services

import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.repositories.UserRepository
import nsu.manasyan.buildingcompany.services.AbstractCrudService
import org.springframework.stereotype.Service

@Service
class UsersService(private val userRepository: UserRepository) : AbstractCrudService<User>(userRepository){

}