package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.model.workers.Master
import nsu.manasyan.buildingcompany.services.AreaService
import org.springframework.stereotype.Component

@Component
class MasterMapper(private val areaService: AreaService) :
    Mapper<Master, TechnicalSpecialistDto>() {
    override fun toDto(entity: Master): TechnicalSpecialistDto {
        val dto = mapper.map(entity, TechnicalSpecialistDto::class.java)
        dto.areaId = entity.area?.id
        return dto
    }

    override fun toEntity(dto: TechnicalSpecialistDto): Master {
        val entity = mapper.map(dto, Master::class.java)
        entity.area = dto.areaId?.let { areaService.getEntity(it) }
        return entity
    }
}