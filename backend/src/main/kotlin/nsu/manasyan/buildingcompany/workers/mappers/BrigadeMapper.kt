package nsu.manasyan.buildingcompany.workers.mappers

import nsu.manasyan.buildingcompany.dto.mappers.Mapper
import nsu.manasyan.buildingcompany.services.ConstructionManagementService
import nsu.manasyan.buildingcompany.workers.dto.BrigadeDto
import nsu.manasyan.buildingcompany.workers.model.Brigade
import nsu.manasyan.buildingcompany.workers.services.WorkersService
import nsu.manasyan.buildingcompany.util.identifiablesToIds
import org.springframework.stereotype.Component

@Component
class BrigadeMapper(
    private val workersService: WorkersService,
    private val constructionManagementService: ConstructionManagementService
) : Mapper<Brigade, BrigadeDto>() {
    override fun toDto(entity: Brigade): BrigadeDto {
        val dto = mapper.map(entity, BrigadeDto::class.java)
        dto.apply {
            managerId = entity.manager?.id
            workerIds = identifiablesToIds(entity.workers)
            constructionManagementId = entity.constructionManagement.id!!
        }
        return dto
    }

    override fun toEntity(dto: BrigadeDto): Brigade {
        val entity = mapper.map(dto, Brigade::class.java)
        entity.apply {
            manager = dto.managerId?.let {
                workersService.getEntity(it)
            }
            constructionManagement = constructionManagementService.getEntity(dto.constructionManagementId)
        }
        return entity
    }
}