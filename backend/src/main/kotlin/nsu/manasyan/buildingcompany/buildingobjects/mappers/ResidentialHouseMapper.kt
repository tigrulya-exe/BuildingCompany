package nsu.manasyan.buildingcompany.buildingobjects.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.buildingobjects.dto.ResidentialHouseDto
import nsu.manasyan.buildingcompany.buildingobjects.model.ResidentialHouse
import org.springframework.stereotype.Component

@Component
class ResidentialHouseMapper(
    private val buildingObjectMapper: BuildingObjectMapper
) : Mapper<ResidentialHouse, ResidentialHouseDto>() {
    override fun toDto(entity: ResidentialHouse): ResidentialHouseDto {
        val dto = mapper.map(entity, ResidentialHouseDto::class.java)
        val objectDto = buildingObjectMapper.toDto(entity)
        dto.apply {
            customerId = objectDto.customerId
            areaId = objectDto.areaId
        }
        return dto
    }

    override fun toEntity(dto: ResidentialHouseDto): ResidentialHouse {
        val entity = mapper.map(dto, ResidentialHouse::class.java)
        val objectEntity = buildingObjectMapper.toEntity(dto)
        entity.apply {
            customer = objectEntity.customer
            area = objectEntity.area
        }
        return entity
    }
}