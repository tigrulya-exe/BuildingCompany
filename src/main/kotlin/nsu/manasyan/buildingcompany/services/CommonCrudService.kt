package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.util.FindRequestParameters

interface CommonCrudService<E> {
    fun getAllEntities(parameters: FindRequestParameters?): MutableList<Dto<E>>

    fun addEntity(dto: Dto<E>)

    fun deleteEntity(id: Int)

    fun getEntity(id: Int): Dto<E>

    fun updateEntity(dto: Dto<E>)
}