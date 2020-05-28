package nsu.manasyan.buildingcompany.security.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.security.model.User
import nsu.manasyan.buildingcompany.security.dto.UserDto
import org.springframework.stereotype.Component

@Component
class UserMapper : Mapper<User, UserDto>() {
    override fun toDto(entity: User): UserDto {
        val dto = mapper.map(entity, UserDto::class.java)
        // TODO tmp
        dto.password = ""
        return dto
    }

    override fun toEntity(dto: UserDto): User {
        val entity = mapper.map(dto, User::class.java)
        entity.roles = mutableSetOf()
        return entity
    }
}