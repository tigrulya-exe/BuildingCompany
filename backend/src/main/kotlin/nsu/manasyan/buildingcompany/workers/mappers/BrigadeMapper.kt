package nsu.manasyan.buildingcompany.workers.mappers

import nsu.manasyan.buildingcompany.dto.mappers.Mapper
import nsu.manasyan.buildingcompany.workers.dto.BrigadeDto
import nsu.manasyan.buildingcompany.workers.model.Brigade
import nsu.manasyan.buildingcompany.workers.services.WorkersService
import nsu.manasyan.buildingcompany.util.identifiablesToIds
import org.springframework.stereotype.Component

@Component
class BrigadeMapper(
    private val workersService: WorkersService
) : Mapper<Brigade, BrigadeDto>() {
    override fun toDto(entity: Brigade): BrigadeDto {
        val dto = mapper.map(entity, BrigadeDto::class.java)
        dto.managerId = entity.manager?.id
        dto.workerIds = identifiablesToIds(entity.workers)
        return dto
    }

    override fun toEntity(dto: BrigadeDto): Brigade {
        val entity = mapper.map(dto, Brigade::class.java)
        entity.manager = dto.managerId?.let {
            workersService.getEntity(it)
        }

        return entity
    }


}