package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.exceptions.NoDataFoundException
import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.TechnicalSpecialistFilter
import nsu.manasyan.buildingcompany.util.getPageable
import nsu.manasyan.buildingcompany.util.getSort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

abstract class AbstractCrudService<E : Identifiable>(
    open val repository: JpaRepository<E, Int>) :
    CommonCrudService<E> {

    @Value("\${application.default-page-size}")
    private lateinit var defaultPageSize: Number

    override fun getAllEntities(parameters: FindRequestParameters?): Page<E> {
        val pageable = getPageable(parameters)
        return repository.findAll(pageable)
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

    protected fun getPageable(parameters: FindRequestParameters?) : Pageable {
        val sort = getSort(parameters)
        val pageable = getPageable(parameters, sort)
        val intPageSize = defaultPageSize.toInt()
        return if(pageable.isPaged) pageable else PageRequest.of(0, intPageSize, sort)
    }
}