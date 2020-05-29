package nsu.manasyan.buildingcompany.security.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.security.dto.RoleDto
import nsu.manasyan.buildingcompany.security.model.UserRole
import org.springframework.stereotype.Component

@Component
class RoleMapper : Mapper<UserRole, RoleDto>() {
    override fun toDto(entity: UserRole): RoleDto {
        return mapper.map(entity, RoleDto::class.java)
    }

    override fun toEntity(dto: RoleDto): UserRole {
        return mapper.map(dto, UserRole::class.java)
    }
}