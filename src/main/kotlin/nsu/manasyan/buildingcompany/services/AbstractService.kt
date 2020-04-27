package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.dto.mappers.Mapper
import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.exceptions.NoDataFoundException
import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.entitiesToDtos
import nsu.manasyan.buildingcompany.util.getPageable
import nsu.manasyan.buildingcompany.util.getSort
import org.springframework.data.jpa.repository.JpaRepository

abstract class AbstractService<E : Identifiable>(
    open val repository: JpaRepository<E, Int>,
    private val modelMapper: Mapper<E, Dto<E>>
) : CommonService<E> {
    override fun getAllEntities(parameters: FindRequestParameters?): MutableList<Dto<E>> {
        val sort = getSort(parameters)
        val pageable = getPageable(parameters, sort)
        val entities = with(repository) {
            if (pageable.isPaged) findAll(pageable).content else findAll(sort)
        }

        return entitiesToDtos(entities, modelMapper)
    }

    override fun addEntity(dto: Dto<E>) {
        val entity = modelMapper.toEntity(dto)
        repository.save(entity)
    }

    override fun deleteEntity(id: Int) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return
        }
        throw NoDataFoundException("Wrong id to delete")
    }

    override fun getEntity(id: Int): Dto<E> {
        val entity = repository.findById(id).orElseThrow {
            throw NoDataFoundException("Wrong id to get")
        }

        return modelMapper.toDto(entity)
    }

    override fun updateEntity(dto: Dto<E>) {
        val entity = modelMapper.toEntity(dto)
        val id = entity.id ?: throw IllegalArgumentException("Id required")
        if (repository.existsById(id)) {
            repository.save(entity)
            return
        }
        throw NoDataFoundException("Wrong id")
    }
}