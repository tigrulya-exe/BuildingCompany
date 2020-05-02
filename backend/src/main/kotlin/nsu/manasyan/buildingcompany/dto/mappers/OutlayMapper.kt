package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.OutlayDto
import nsu.manasyan.buildingcompany.model.Outlay
import nsu.manasyan.buildingcompany.services.MaterialsService
import nsu.manasyan.buildingcompany.services.WorkScheduleService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class OutlayMapper(
    private val mapper: ModelMapper,
    private val materialsService: MaterialsService,
    private val scheduleService: WorkScheduleService
) : Mapper<Outlay, OutlayDto> {
    override fun toDto(entity: Outlay): OutlayDto {
        val dto = mapper.map(entity, OutlayDto::class.java)
        dto.materialId = entity.material.id!!
        dto.scheduleId = entity.scheduleRow.id!!
        return dto
    }

    override fun toEntity(dto: OutlayDto): Outlay {
        val entity = mapper.map(dto, Outlay::class.java)
        entity.material = materialsService.getEntity(dto.materialId)
        entity.scheduleRow = scheduleService.getEntity(dto.scheduleId)
        return entity
    }
}