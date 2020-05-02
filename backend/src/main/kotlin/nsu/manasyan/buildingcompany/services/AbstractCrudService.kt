package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.exceptions.NoDataFoundException
import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.getPageable
import nsu.manasyan.buildingcompany.util.getSort
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

abstract class AbstractCrudService<E : Identifiable>(open val repository: JpaRepository<E, Int>) : CommonCrudService<E> {
    override fun getAllEntities(parameters: FindRequestParameters?): MutableList<E> {
        val sort = getSort(parameters)
        val pageable = getPageable(parameters, sort)
        return with(repository) {
            if (pageable.isPaged) findAll(pageable).content else findAll(sort)
        }
    }

    @Transactional
    override fun addEntity(entity: E) {
        repository.save(entity)
    }

    override fun deleteEntity(id: Int) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return
        }
        throw NoDataFoundException("Wrong id to delete")
    }

    override fun getEntity(id: Int): E {
        return repository.findById(id).orElseThrow {
            throw NoDataFoundException("Wrong id to get")
        }
    }

    override fun updateEntity(entity: E) {
        val id = entity.id ?: throw IllegalArgumentException("Id required")
        if (repository.existsById(id)) {
            repository.save(entity)
            return
        }
        throw NoDataFoundException("Wrong id")
    }
}