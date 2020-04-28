package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.MaterialDto
import nsu.manasyan.buildingcompany.model.Material
import org.springframework.stereotype.Component

@Component
class MaterialMapper : Mapper<Material, MaterialDto> {
    override fun toDto(entity: Material): MaterialDto = MaterialDto(entity.name)

    override fun toEntity(dto: MaterialDto): Material = Material(dto.name)
}
