package nsu.manasyan.buildingcompany.companypartsstuff.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.buildingobjects.services.BuildingObjectService
import nsu.manasyan.buildingcompany.companypartsstuff.dto.MachineryDto
import nsu.manasyan.buildingcompany.companypartsstuff.model.Machinery
import org.springframework.stereotype.Component

@Component
class MachineryMapper(private val buildingObjectService: BuildingObjectService) :
    Mapper<Machinery, MachineryDto>() {
    override fun toDto(entity: Machinery): MachineryDto {
        val dto = mapper.map(entity, MachineryDto::class.java)
        dto.buildingObjectId = entity.buildingObject?.id
        return dto
    }

    override fun toEntity(dto: MachineryDto): Machinery {
        val entity = mapper.map(dto, Machinery::class.java)
        entity.buildingObject = dto.buildingObjectId?.let {
            buildingObjectService.getEntity(it)
        }
        return entity
    }
}