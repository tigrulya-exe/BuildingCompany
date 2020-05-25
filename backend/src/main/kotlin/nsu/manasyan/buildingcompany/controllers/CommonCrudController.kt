package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.util.FindRequestParameters

interface CommonCrudController<E : Identifiable, D : Dto<in E>> {
    fun getAllEntities(params: FindRequestParameters?): PageDto<*>

    fun addEntity(dto: D)

    fun deleteEntity(id: Int)

    fun getEntity(id: Int): D

    fun updateEntity(dto: D)
}