package nsu.manasyan.buildingcompany.security.controllers

import nsu.manasyan.buildingcompany.abstracts.controllers.AbstractCrudController
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.abstracts.dto.PageDto
import nsu.manasyan.buildingcompany.abstracts.mappers.Mapper
import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import nsu.manasyan.buildingcompany.abstracts.services.CommonCrudService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

abstract class AbstractSecuredCrudController<E : Identifiable, D : Dto<in E>>(
    service: CommonCrudService<E>,
    mapper: Mapper<E, D>,
    entityName: String
) : AbstractCrudController<E, D>(service, mapper, entityName) {

    @GetMapping
    @PreAuthorize("hasAuthority('READ_USERS')")
    override fun getAllEntities(params: FindRequestParameters?): PageDto<*> {
        return super.getAllEntities(params)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EDIT_USERS')")
    override fun addEntity(@RequestBody dto: D) {
        return super.addEntity(dto)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EDIT_USERS')")
    override fun deleteEntity(@PathVariable id: Int) {
        return super.deleteEntity(id)
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_USERS')")
    override fun getEntity(@PathVariable id: Int): D {
        return super.getEntity(id)
    }

    @PutMapping
    @PreAuthorize("hasAuthority('EDIT_USERS')")
    override fun updateEntity(@RequestBody dto: D) {
        return super.updateEntity(dto)
    }

    @PreAuthorize("hasAuthority('READ_USERS')")
    override fun <F : Filter<in E>> findAllByFilter(filter: F?, requestParams: FindRequestParameters?): PageDto<*> {
        return super.findAllByFilter(filter, requestParams)
    }
}