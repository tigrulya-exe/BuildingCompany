package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.WorkScheduleDto
import nsu.manasyan.buildingcompany.model.WorkSchedule
import nsu.manasyan.buildingcompany.services.BrigadeService
import nsu.manasyan.buildingcompany.services.BuildingObjectService
import nsu.manasyan.buildingcompany.services.WorkTypeService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class WorkScheduleMapper(
    private val mapper: ModelMapper,
    private val brigadeService: BrigadeService,
    private val buildingObjectService: BuildingObjectService,
    private val workTypeService: WorkTypeService
    ) : Mapper<WorkSchedule, WorkScheduleDto> {
    override fun toDto(entity: WorkSchedule): WorkScheduleDto {
        val dto = mapper.map(entity, WorkScheduleDto::class.java)
        dto.brigadeId = entity.brigade.id!!
        dto.buildingObjectId = entity.buildingObject.id!!
        dto.workTypeId = entity.workType.id!!
        return dto
    }

    override fun toEntity(dto: WorkScheduleDto): WorkSchedule {
        val entity = mapper.map(dto, WorkSchedule::class.java)
        entity.brigade = brigadeService.getEntity(dto.brigadeId)
        entity.buildingObject = buildingObjectService.getEntity(dto.buildingObjectId)
        entity.workType = workTypeService.getEntity(dto.workTypeId)
        return entity
    }
}