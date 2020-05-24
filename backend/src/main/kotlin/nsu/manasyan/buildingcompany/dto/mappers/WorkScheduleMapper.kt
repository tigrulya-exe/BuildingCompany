package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.WorkScheduleDto
import nsu.manasyan.buildingcompany.model.WorkSchedule
import nsu.manasyan.buildingcompany.services.BrigadeObjectWorkService
import org.springframework.stereotype.Component

@Component
class WorkScheduleMapper(
    private val brigadeObjectWorkService: BrigadeObjectWorkService
) : Mapper<WorkSchedule, WorkScheduleDto>() {
    override fun toDto(entity: WorkSchedule): WorkScheduleDto {
        val dto = mapper.map(entity, WorkScheduleDto::class.java)
        dto.brigadeId = entity.brigadeWork.brigade.id!!
        dto.buildingObjectId = entity.brigadeWork.buildingObject.id!!
        dto.workType = entity.brigadeWork.workType.name
        return dto
    }

    override fun toEntity(dto: WorkScheduleDto): WorkSchedule {
        val entity = mapper.map(dto, WorkSchedule::class.java)
        entity.brigadeWork = brigadeObjectWorkService
            .getOrCreate(dto.brigadeId, dto.workType, dto.buildingObjectId)
        return entity
    }
}