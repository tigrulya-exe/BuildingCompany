package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.BrigadeDto
import nsu.manasyan.buildingcompany.model.workers.Brigade
import nsu.manasyan.buildingcompany.repositories.WorkersRepository
import nsu.manasyan.buildingcompany.util.identifiablesToIds
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class BrigadeMapper(
    private val mapper: ModelMapper,
    private val workersRepository: WorkersRepository
) : Mapper<Brigade, BrigadeDto> {
    override fun toDto(entity: Brigade): BrigadeDto {
        val dto = mapper.map(entity, BrigadeDto::class.java)
        dto.managerId = entity.id
        dto.workerIds = identifiablesToIds(entity.workers)
        return dto
    }

    override fun toEntity(dto: BrigadeDto): Brigade {
        val entity = mapper.map(dto, Brigade::class.java)
        entity.manager = dto.managerId?.let {
            workersRepository.findById(it)
                .orElseThrow { throw IllegalArgumentException("Wrong manager id") }
        }

        return entity
    }


}