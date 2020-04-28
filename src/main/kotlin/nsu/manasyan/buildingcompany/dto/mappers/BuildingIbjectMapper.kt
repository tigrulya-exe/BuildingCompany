package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.BuildingObjectDto
import nsu.manasyan.buildingcompany.model.BuildingObject
import nsu.manasyan.buildingcompany.services.CustomersService
import nsu.manasyan.buildingcompany.services.MachineryService
import nsu.manasyan.buildingcompany.util.identifiablesToIds
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class BuildingObjectMapper(
    private val mapper: ModelMapper,
    private val customersService: CustomersService,
    private val machineryService: MachineryService
) : Mapper<BuildingObject, BuildingObjectDto> {
    override fun toDto(entity: BuildingObject): BuildingObjectDto {
        val dto = mapper.map(entity, BuildingObjectDto::class.java)
        dto.customerId = entity.customer.id!!
        dto.machinery = identifiablesToIds(entity.machinery)
        return dto
    }

    override fun toEntity(dto: BuildingObjectDto): BuildingObject {
        val entity = mapper.map(dto, BuildingObject::class.java)
        entity.customer = customersService.getEntity(dto.customerId)
        TODO()
    }
}