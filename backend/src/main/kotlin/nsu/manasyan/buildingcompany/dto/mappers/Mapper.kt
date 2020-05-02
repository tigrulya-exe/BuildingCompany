package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.Dto
import java.util.stream.Collectors

// todo put modelMapper here
interface Mapper<E, D : Dto<E>> {
    fun toDto(entity: E): D

    fun toEntity(dto: D): E

    fun toDtos(entities: MutableList<E>): MutableList<D> {
        return entities.stream()
            .map { toDto(it) }
            .collect(Collectors.toList())
    }
}