package nsu.manasyan.buildingcompany.technicalstuff.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.abstracts.model.Post
import nsu.manasyan.buildingcompany.companypartsstuff.services.AreaService
import nsu.manasyan.buildingcompany.technicalstuff.dto.TechnicalSpecialistDto
import nsu.manasyan.buildingcompany.technicalstuff.model.Foreman
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
        entity.apply {
            area = dto.areaId?.let { areaService.getEntity(it) }
            post = Post.FOREMAN
        }
        return entity
    }
}