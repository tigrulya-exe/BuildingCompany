package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.model.workers.Foreman
import nsu.manasyan.buildingcompany.services.AreaService
import org.springframework.stereotype.Component

@Component
class ForemanMapper(private val areaService: AreaService) :
    Mapper<Foreman, TechnicalSpecialistDto>() {
    override fun toDto(entity: Foreman): TechnicalSpecialistDto {
        val dto = mapper.map(entity, TechnicalSpecialistDto::class.java)
        dto.areaId = entity.area?.id
        return dto
    }

    override fun toEntity(dto: TechnicalSpecialistDto): Foreman {
        val entity = mapper.map(dto, Foreman::class.java)
        entity.area = dto.areaId?.let { areaService.getEntity(it) }
        return entity
    }
}