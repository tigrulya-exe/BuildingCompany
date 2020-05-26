package nsu.manasyan.buildingcompany.technicalstuff.mappers

import nsu.manasyan.buildingcompany.dto.mappers.Mapper
import nsu.manasyan.buildingcompany.model.Post
import nsu.manasyan.buildingcompany.services.AreaService
import nsu.manasyan.buildingcompany.technicalstuff.dto.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.technicalstuff.model.Master
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
        entity.apply {
            area = dto.areaId?.let { areaService.getEntity(it) }
            post = Post.MASTER
        }
        return entity
    }
}