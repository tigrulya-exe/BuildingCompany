package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.services.CommonCrudService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.*

abstract class AbstractCrudController<E : Identifiable, D : Dto<E>>(
    val service: CommonCrudService<E>,
    private val entityName: String
) : CommonCrudController<E, D> {

    @GetMapping
    override fun getAllEntities(params: FindRequestParameters?): MutableList<Dto<E>> {
        logger().info("All ${entityName}s were fetched")
        return service.getAllEntities(params)
    }

    @PostMapping
    override fun addEntity(@RequestBody dto: D) {
        service.addEntity(dto)
        logger().info("$entityName was added")
    }

    @DeleteMapping("/{id}")
    override fun deleteEntity(@PathVariable id: Int) {
        service.deleteEntity(id)
        logger().info("$entityName #'$id' was deleted")
    }

    @GetMapping("/{id}")
    override fun getEntity(@PathVariable id: Int): Dto<E> {
        logger().info("$entityName #'$id' was queried")
        return service.getEntity(id)
    }

    @PutMapping
    override fun updateEntity(@RequestBody dto: D) {
        service.updateEntity(dto)
        logger().info("$entityName '${dto.id}' was updated")
    }
}