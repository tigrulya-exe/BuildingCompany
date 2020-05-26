package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.ConstructionManagementDto
import nsu.manasyan.buildingcompany.model.Area
import nsu.manasyan.buildingcompany.model.ConstructionManagement
import nsu.manasyan.buildingcompany.repositories.AreaRepository
import nsu.manasyan.buildingcompany.technicalstuff.services.TechnicalSpecialistsService
import nsu.manasyan.buildingcompany.util.identifiablesToIds
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component

@Component
class ConstructionManagementMapper(
    private val technicalSpecialistsService: TechnicalSpecialistsService,
    private val areaRepository: AreaRepository
) : Mapper<ConstructionManagement, ConstructionManagementDto>() {
    override fun toDto(entity: ConstructionManagement): ConstructionManagementDto {
        val dto = mapper.map(entity, ConstructionManagementDto::class.java)
        dto.managerId = entity.manager?.id
        dto.areaIds = identifiablesToIds(entity.areas)
        return dto
    }

    override fun toEntity(dto: ConstructionManagementDto): ConstructionManagement {
        val entity = mapper.map(dto, ConstructionManagement::class.java)
        entity.manager = dto.managerId?.let { technicalSpecialistsService.getEntity(it) }
//        entity.areas = areaRepository.findAll(getAreasSpecification(dto.areaIds)).toMutableSet()
        return entity
    }

    private fun getAreasSpecification(areaIds: MutableSet<Int>?): Specification<Area> {
        return Specification { root, query, cb ->
            root.get<Area>("id").`in`(areaIds)
        }
    }
}