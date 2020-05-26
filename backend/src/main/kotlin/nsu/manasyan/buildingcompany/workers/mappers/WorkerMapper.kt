package nsu.manasyan.buildingcompany.workers.mappers

import nsu.manasyan.buildingcompany.dto.mappers.Mapper
import nsu.manasyan.buildingcompany.workers.dto.WorkerDto
import nsu.manasyan.buildingcompany.workers.model.Worker
import nsu.manasyan.buildingcompany.workers.services.BrigadeService
import org.springframework.stereotype.Component

@Component
class WorkerMapper(
    private val brigadeService: BrigadeService
) : Mapper<Worker, WorkerDto>() {
    override fun toDto(entity: Worker): WorkerDto {
        val dto = mapper.map(entity, WorkerDto::class.java)
        dto.brigadeId = entity.brigade?.id
        return dto
    }

    override fun toEntity(dto: WorkerDto): Worker {
        val entity = mapper.map(dto, Worker::class.java)
        entity.brigade = dto.brigadeId?.let { brigadeService.getEntity(it) }
        return entity
    }
}