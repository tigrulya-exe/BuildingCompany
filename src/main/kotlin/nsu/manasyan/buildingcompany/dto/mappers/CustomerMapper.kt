package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.CustomerDto
import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.model.Customer
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class CustomerMapper(private val mapper: ModelMapper) : Mapper<Customer, Dto<Customer>> {
    override fun toDto(entity: Customer): CustomerDto {
        val dto = mapper.map(entity, CustomerDto::class.java)
        dto.buildingObjects = entity.buildingObjects
            .stream()
            .map { it.id }
            .collect(Collectors.toList())
        return dto
    }

    override fun toEntity(dto: Dto<Customer>): Customer {
        return mapper.map(dto, Customer::class.java)
    }
}