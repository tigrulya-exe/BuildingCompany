package nsu.manasyan.buildingcompany.buildingobjects.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.buildingobjects.dto.OutlayDto
import nsu.manasyan.buildingcompany.buildingobjects.model.Outlay
import nsu.manasyan.buildingcompany.buildingobjects.services.BuildingObjectService
import nsu.manasyan.buildingcompany.buildingobjects.services.MaterialsService
import org.springframework.stereotype.Component

@Component
class OutlayMapper(
    private val materialsService: MaterialsService,
    private val buildingObjectService: BuildingObjectService
) : Mapper<Outlay, OutlayDto>() {
    override fun toDto(entity: Outlay): OutlayDto {
        val dto = mapper.map(entity, OutlayDto::class.java)
        dto.materialId = entity.material.id!!
        dto.buildingObjectId = entity.buildingObject.id!!
        return dto
    }

    override fun toEntity(dto: OutlayDto): Outlay {
        val entity = mapper.map(dto, Outlay::class.java)
        entity.material = materialsService.getEntity(dto.materialId)
        entity.buildingObject = buildingObjectService.getEntity(dto.buildingObjectId)
        return entity
    }
}