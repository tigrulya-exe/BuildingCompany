package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.BuildingObjectDto
import nsu.manasyan.buildingcompany.model.BuildingObject
import nsu.manasyan.buildingcompany.model.ConstructionMachinery
import nsu.manasyan.buildingcompany.repositories.MachineryRepository
import nsu.manasyan.buildingcompany.services.CustomersService
import nsu.manasyan.buildingcompany.util.identifiablesToIds
import org.modelmapper.ModelMapper
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component

@Component
class BuildingObjectMapper(
    private val mapper: ModelMapper,
    private val customersService: CustomersService,
    private val machineryRepository: MachineryRepository
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
//        val specification = getMachinerySpecification(dto.machinery)
//        entity.machinery = machineryRepository.findAll(specification).toMutableSet()
        return entity
    }

    private fun getMachinerySpecification(machineIds: MutableSet<Int>?): Specification<ConstructionMachinery> {
        return Specification { root, query, cb ->
            root.get<ConstructionMachinery>("id").`in`(machineIds)
        }
    }
}