package nsu.manasyan.buildingcompany.abstracts.controllers

import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import nsu.manasyan.buildingcompany.util.FindRequestParameters

interface CommonCrudController<E : Identifiable, D : Dto<in E>> {
    fun getAllEntities(params: FindRequestParameters?): PageDto<*>

    fun addEntity(dto: D)

    fun deleteEntity(id: Int)

    fun getEntity(id: Int): D

    fun updateEntity(dto: D)
}