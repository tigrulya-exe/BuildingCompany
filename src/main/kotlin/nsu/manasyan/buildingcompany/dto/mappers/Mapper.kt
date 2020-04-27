package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.Dto

interface Mapper<E, D : Dto<E>> {
    fun toDto(entity: E): D

    fun toEntity(dto: Dto<E>): E
}