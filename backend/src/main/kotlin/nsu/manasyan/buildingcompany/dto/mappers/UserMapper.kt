package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.model.UserDto
import org.springframework.stereotype.Component

@Component
class UserMapper : Mapper<User, UserDto>() {
    override fun toDto(entity: User): UserDto {
        return mapper.map(entity, UserDto::class.java)
    }

    override fun toEntity(dto: UserDto): User {
        val entity = mapper.map(dto, User::class.java)
        entity.roles = mutableSetOf()
        return entity
    }
}