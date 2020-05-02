package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.util.FindRequestParameters

interface CommonCrudService<E> {
    fun getAllEntities(parameters: FindRequestParameters?): MutableList<E>

    fun addEntity(entity: E)

    fun deleteEntity(id: Int)

    fun getEntity(id: Int): E

    fun updateEntity(entity: E)
}