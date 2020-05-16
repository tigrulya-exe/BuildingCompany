package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.OutlayDto
import nsu.manasyan.buildingcompany.model.Outlay
import nsu.manasyan.buildingcompany.services.BrigadeObjectWorkService
import nsu.manasyan.buildingcompany.services.MaterialsService
import nsu.manasyan.buildingcompany.services.WorkScheduleService
import org.springframework.stereotype.Component

@Component
class OutlayMapper(
    private val materialsService: MaterialsService,
    private val brigadeObjectWorkService: BrigadeObjectWorkService
) : Mapper<Outlay, OutlayDto>() {
    override fun toDto(entity: Outlay): OutlayDto {
        val dto = mapper.map(entity, OutlayDto::class.java)
        dto.materialId = entity.material.id!!
        dto.brigadeId = entity.brigadeWork.brigade.id!!
        dto.buildingObjectId = entity.brigadeWork.buildingObject.id!!
        dto.workTypeId = entity.brigadeWork.workType.id!!
        return dto
    }

    override fun toEntity(dto: OutlayDto): Outlay {
        val entity = mapper.map(dto, Outlay::class.java)
        entity.material = materialsService.getEntity(dto.materialId)
        entity.brigadeWork = brigadeObjectWorkService
            .getOrCreate(dto.brigadeId, dto.workTypeId, dto.buildingObjectId)
        return entity
    }
}