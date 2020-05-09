package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page

interface CommonCrudService<E> {
    fun getAllEntities(parameters: FindRequestParameters?): Page<E>

    fun getAllEntitiesByFilter(filter: Filter<E>?, parameters: FindRequestParameters?): Page<E>

    fun addEntity(entity: E)

    fun deleteEntity(id: Int)

    fun getEntity(id: Int): E

    fun updateEntity(entity: E)
}