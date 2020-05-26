package nsu.manasyan.buildingcompany.workers.mappers

import nsu.manasyan.buildingcompany.dto.mappers.Mapper
import nsu.manasyan.buildingcompany.workers.dto.MasonDto
import nsu.manasyan.buildingcompany.workers.model.Mason
import org.springframework.stereotype.Component

@Component
class MasonMapper(
    private val workerMapper: WorkerMapper
) : Mapper<Mason, MasonDto>() {
    override fun toDto(entity: Mason): MasonDto {
        val dto = mapper.map(entity, MasonDto::class.java)
        dto.brigadeId = entity.brigade?.id
        return dto
    }

    override fun toEntity(dto: MasonDto): Mason {
        val entity = mapper.map(dto, Mason::class.java)
        val worker = workerMapper.toEntity(dto)
        entity.brigade = worker.brigade
        return entity
    }
}