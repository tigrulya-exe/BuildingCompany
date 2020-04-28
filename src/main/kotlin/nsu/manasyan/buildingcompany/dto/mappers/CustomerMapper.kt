package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.CustomerDto
import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.model.Customer
import nsu.manasyan.buildingcompany.util.identifiablesToIds
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class CustomerMapper(private val mapper: ModelMapper) : Mapper<Customer, CustomerDto> {
    override fun toDto(entity: Customer): CustomerDto {
        val dto = mapper.map(entity, CustomerDto::class.java)
        dto.buildingObjects = identifiablesToIds(entity.buildingObjects)
        return dto
    }

    override fun toEntity(dto: CustomerDto): Customer {
        return mapper.map(dto, Customer::class.java)
    }
}