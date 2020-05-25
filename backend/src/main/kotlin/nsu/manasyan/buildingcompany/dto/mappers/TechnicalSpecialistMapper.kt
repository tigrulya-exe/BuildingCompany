package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.services.AreaService
import org.springframework.stereotype.Component

@Component
class TechnicalSpecialistMapper(private val areaService: AreaService) :
    Mapper<TechnicalSpecialist, TechnicalSpecialistDto>() {
    override fun toDto(entity: TechnicalSpecialist): TechnicalSpecialistDto {
        val dto = mapper.map(entity, TechnicalSpecialistDto::class.java)
        dto.areaId = entity.area?.id
        return dto
    }

    override fun toEntity(dto: TechnicalSpecialistDto): TechnicalSpecialist {
        val entity = mapper.map(dto, TechnicalSpecialist::class.java)
        entity.area = dto.areaId?.let { areaService.getEntity(it) }
        return entity
    }
}