package nsu.manasyan.buildingcompany.buildingobjects.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.buildingobjects.dto.MaterialDto
import nsu.manasyan.buildingcompany.buildingobjects.model.Material
import org.springframework.stereotype.Component

@Component
class MaterialMapper : Mapper<Material, MaterialDto>() {
    override fun toDto(entity: Material): MaterialDto {
        val dto = MaterialDto(entity.name)
        dto.id = entity.id
        return dto
    }

    override fun toEntity(dto: MaterialDto): Material {
        val entity = Material(dto.name)
        entity.id = dto.id
        return entity
    }
}
