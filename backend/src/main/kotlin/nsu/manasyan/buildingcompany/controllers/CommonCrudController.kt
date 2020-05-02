package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.util.FindRequestParameters

interface CommonCrudController<E : Identifiable, D : Dto<E>> {
    fun getAllEntities(params: FindRequestParameters?): MutableList<D>

    fun addEntity(dto: D)

    fun deleteEntity(id: Int)

    fun getEntity(id: Int): D

    fun updateEntity(dto: D)
}