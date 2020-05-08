package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.Mapper
import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.services.CommonCrudService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.*

abstract class AbstractCrudController<E : Identifiable, D : Dto<E>>(
    open val service: CommonCrudService<E>,
    val mapper: Mapper<E, D>,
    private val entityName: String
) : CommonCrudController<E, D> {

    @GetMapping
    override fun getAllEntities(params: FindRequestParameters?): PageDto<*> {
        logger().info("All ${entityName}s were fetched")
//        return mapper.toDtos(service.getAllEntities(params))
        return mapper.toPageDto(service.getAllEntities(params))
    }

    @PostMapping
    override fun addEntity(@RequestBody dto: D) {
        val e = mapper.toEntity(dto)
        service.addEntity(mapper.toEntity(dto))
        logger().info("$entityName was added")
    }

    @DeleteMapping("/{id}")
    override fun deleteEntity(@PathVariable id: Int) {
        service.deleteEntity(id)
        logger().info("$entityName #'$id' was deleted")
    }

    @GetMapping("/{id}")
    override fun getEntity(@PathVariable id: Int): D {
        logger().info("$entityName #'$id' was queried")
        return mapper.toDto(service.getEntity(id))
    }

    @PutMapping
    override fun updateEntity(@RequestBody dto: D) {
        service.updateEntity(mapper.toEntity(dto))
        logger().info("$entityName '${dto.id}' was updated")
    }
}