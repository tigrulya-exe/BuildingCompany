package nsu.manasyan.buildingcompany.buildingobjects.mappers

import nsu.manasyan.buildingcompany.buildingobjects.dto.BuildingObjectDto
import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.companypartsstuff.services.AreaService
import nsu.manasyan.buildingcompany.customers.services.CustomersService
import org.springframework.stereotype.Component

@Component
class BuildingObjectMapper(
    private val customersService: CustomersService,
    private val areaService: AreaService
) : Mapper<BuildingObject, BuildingObjectDto>() {
    override fun toDto(entity: BuildingObject): BuildingObjectDto {
        val dto = mapper.map(entity, BuildingObjectDto::class.java)
        dto.customerId = entity.customer?.id
        dto.areaId = entity.area.id!!
        return dto
    }

    override fun toEntity(dto: BuildingObjectDto): BuildingObject {
        val entity = mapper.map(dto, BuildingObject::class.java)
        entity.customer = dto.customerId?.let { customersService.getEntity(it) }
        entity.area = areaService.getEntity(dto.areaId)
        return entity
    }

}