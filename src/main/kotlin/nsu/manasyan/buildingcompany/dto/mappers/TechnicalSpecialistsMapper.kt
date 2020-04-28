package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.dto.model.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class TechnicalSpecialistMapper(private val mapper: ModelMapper) :
    Mapper<TechnicalSpecialist, Dto<TechnicalSpecialist>> {
    override fun toDto(entity: TechnicalSpecialist): TechnicalSpecialistDto {
        return mapper.map(entity, TechnicalSpecialistDto::class.java)
    }

    override fun toEntity(dto: Dto<TechnicalSpecialist>): TechnicalSpecialist {
        return mapper.map(dto, TechnicalSpecialist::class.java)
    }
}