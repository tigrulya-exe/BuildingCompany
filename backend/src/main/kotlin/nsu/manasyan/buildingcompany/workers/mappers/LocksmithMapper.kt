package nsu.manasyan.buildingcompany.workers.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.abstracts.model.Post
import nsu.manasyan.buildingcompany.workers.dto.LocksmithDto
import nsu.manasyan.buildingcompany.workers.model.Locksmith
import org.springframework.stereotype.Component

@Component
class LocksmithMapper(
    private val workerMapper: WorkerMapper
) : Mapper<Locksmith, LocksmithDto>() {
    override fun toDto(entity: Locksmith): LocksmithDto {
        val dto = mapper.map(entity, LocksmithDto::class.java)
        dto.brigadeId = entity.brigade?.id
        return dto
    }

    override fun toEntity(dto: LocksmithDto): Locksmith {
        val entity = mapper.map(dto, Locksmith::class.java)
        val worker = workerMapper.toEntity(dto)
        entity.apply {
            brigade = worker.brigade
            post = Post.LOCKSMITH
        }
        return entity
    }
}