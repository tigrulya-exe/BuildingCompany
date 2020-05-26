package nsu.manasyan.buildingcompany.buildingobjects.mappers

import nsu.manasyan.buildingcompany.buildingobjects.dto.BridgeDto
import nsu.manasyan.buildingcompany.buildingobjects.model.Bridge
import nsu.manasyan.buildingcompany.dto.mappers.Mapper
import org.springframework.stereotype.Component

@Component
class BridgeMapper(
    private val buildingObjectMapper: BuildingObjectMapper
) : Mapper<Bridge, BridgeDto>() {
    override fun toDto(entity: Bridge): BridgeDto {
        val dto = mapper.map(entity, BridgeDto::class.java)
        val objectDto = buildingObjectMapper.toDto(entity)
        dto.apply {
            customerId = objectDto.customerId
            areaId = objectDto.areaId
        }
        return dto
    }

    override fun toEntity(dto: BridgeDto): Bridge {
        val entity = mapper.map(dto, Bridge::class.java)
        val objectEntity = buildingObjectMapper.toEntity(dto)
        entity.apply {
            customer = objectEntity.customer
            area = objectEntity.area
        }
        return entity
    }
}