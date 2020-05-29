package nsu.manasyan.buildingcompany.security.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.security.dto.PermissionDto
import nsu.manasyan.buildingcompany.security.model.Permission
import org.springframework.stereotype.Component

@Component
class PermissionMapper : Mapper<Permission, PermissionDto>() {
    override fun toDto(entity: Permission): PermissionDto {
        val dto = PermissionDto(entity.stringRepresentation)
        dto.id = entity.id
        return dto
    }

    override fun toEntity(dto: PermissionDto): Permission {
        val entity = Permission(dto.name)
        entity.id = dto.id
        return entity
    }
}