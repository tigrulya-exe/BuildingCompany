package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.MachineryDto
import nsu.manasyan.buildingcompany.model.ConstructionMachinery
import nsu.manasyan.buildingcompany.services.BuildingObjectService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class MachineryMapper(private val mapper: ModelMapper, private val buildingObjectService: BuildingObjectService) :
    Mapper<ConstructionMachinery, MachineryDto> {
    override fun toDto(entity: ConstructionMachinery): MachineryDto {
        val dto = mapper.map(entity, MachineryDto::class.java)
        dto.buildingObjectId = entity.buildingObject?.id
        return dto
    }

    override fun toEntity(dto: MachineryDto): ConstructionMachinery {
        val entity = mapper.map(dto, ConstructionMachinery::class.java)
        entity.buildingObject = dto.buildingObjectId?.let {
            buildingObjectService.getEntity(it)
        }
        return entity
    }
}