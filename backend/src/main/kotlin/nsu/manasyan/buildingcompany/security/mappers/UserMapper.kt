package nsu.manasyan.buildingcompany.security.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.security.dto.UserDto
import nsu.manasyan.buildingcompany.security.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper : Mapper<User, UserDto>() {
    override fun toDto(entity: User): UserDto {
        val dto = mapper.map(entity, UserDto::class.java)
        return dto
    }

    override fun toEntity(dto: UserDto): User {
        val entity = mapper.map(dto, User::class.java)
        return entity
    }
}