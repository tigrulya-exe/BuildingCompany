package nsu.manasyan.buildingcompany.buildingobjects.mappers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.buildingobjects.dto.WorkTypeDto
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkType
import org.springframework.stereotype.Component

@Component
class WorkTypeMapper : Mapper<WorkType, WorkTypeDto>() {
    override fun toDto(entity: WorkType): WorkTypeDto {
        return mapper.map(entity, WorkTypeDto::class.java)
    }

    override fun toEntity(dto: WorkTypeDto): WorkType {
        return mapper.map(dto, WorkType::class.java)
    }
}