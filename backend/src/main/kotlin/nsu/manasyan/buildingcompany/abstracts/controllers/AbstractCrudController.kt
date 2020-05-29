package nsu.manasyan.buildingcompany.abstracts.controllers

import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import nsu.manasyan.buildingcompany.abstracts.services.CommonCrudService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.annotation.security.RolesAllowed

abstract class AbstractCrudController<E : Identifiable, D : Dto<in E>>(
    open val service: CommonCrudService<E>,
    val mapper: Mapper<E, D>,
    private val entityName: String
) : CommonCrudController<E, D> {

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    override fun getAllEntities(params: FindRequestParameters?): PageDto<*> {
        logger().info("All ${entityName}s were fetched")
        return mapper.toPageDto(service.getAllEntities(params))
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    override fun addEntity(@RequestBody dto: D) {
        val e = mapper.toEntity(dto)
        service.addEntity(mapper.toEntity(dto))
        logger().info("$entityName was added")
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    override fun deleteEntity(@PathVariable id: Int) {
        service.deleteEntity(id)
        logger().info("$entityName #'$id' was deleted")
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    override fun getEntity(@PathVariable id: Int): D {
        logger().info("$entityName #'$id' was queried")
        return mapper.toDto(service.getEntity(id))
    }

    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE')")
    override fun updateEntity(@RequestBody dto: D) {
        service.updateEntity(mapper.toEntity(dto))
        logger().info("$entityName '${dto.id}' was updated")
    }

    @PreAuthorize("hasAuthority('READ')")
    fun <F : Filter<in E>> findAllByFilter(filter: F?, requestParams: FindRequestParameters?): PageDto<*> {
        val page = service.getAllEntitiesByFilter(filter, requestParams)
        logger().info("${entityName}s filter method was queried")
        return mapper.toPageDto(page)
    }
}