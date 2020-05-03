package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.CustomerDto
import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.util.identifiablesToIds
import org.springframework.stereotype.Component

@Component
class CustomerMapper : Mapper<Customer, CustomerDto>() {
    override fun toDto(entity: Customer): CustomerDto {
        val dto = mapper.map(entity, CustomerDto::class.java)
        dto.buildingObjectIds = identifiablesToIds(entity.buildingObjects)
        return dto
    }

    override fun toEntity(dto: CustomerDto): Customer {
        return mapper.map(dto, Customer::class.java)
    }
}