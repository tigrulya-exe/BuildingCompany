package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.AreaDto
import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.dto.model.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.model.Area
import nsu.manasyan.buildingcompany.util.identifiablesToIds
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class AreaMapper(private val mapper: ModelMapper) : Mapper<Area, Dto<Area>> {
    override fun toDto(entity: Area): AreaDto {
        val dto = mapper.map(entity, AreaDto::class.java)
        dto.managements = identifiablesToIds(entity.managements)
        dto.manager = mapper.map(entity.manager, TechnicalSpecialistDto::class.java)
        return dto
    }

    override fun toEntity(dto: Dto<Area>): Area {
        return mapper.map(dto, Area::class.java)
    }
}