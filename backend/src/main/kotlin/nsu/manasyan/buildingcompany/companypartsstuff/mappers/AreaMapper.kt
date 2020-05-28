package nsu.manasyan.buildingcompany.companypartsstuff.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.companypartsstuff.dto.AreaDto
import nsu.manasyan.buildingcompany.companypartsstuff.model.Area
import nsu.manasyan.buildingcompany.companypartsstuff.services.ConstructionManagementService
import nsu.manasyan.buildingcompany.technicalstuff.services.TechnicalSpecialistsService
import org.springframework.stereotype.Component

@Component
class AreaMapper(
    private val technicalSpecialistsService: TechnicalSpecialistsService,
    private val constructionManagementService: ConstructionManagementService
) : Mapper<Area, AreaDto>() {
    override fun toDto(entity: Area): AreaDto {
        val dto = mapper.map(entity, AreaDto::class.java)
        dto.managementId = entity.management?.id
        dto.managerId = entity.manager?.id
        return dto
    }

    override fun toEntity(dto: AreaDto): Area {
        val entity = mapper.map(dto, Area::class.java)
        entity.manager = dto.managerId?.let { technicalSpecialistsService.getEntity(it) }
        entity.management = dto.managementId?.let { constructionManagementService.getEntity(it) }
        return entity
    }
}