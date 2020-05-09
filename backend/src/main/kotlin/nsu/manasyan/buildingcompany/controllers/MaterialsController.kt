package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.MaterialMapper
import nsu.manasyan.buildingcompany.dto.model.MaterialDto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import nsu.manasyan.buildingcompany.model.Material
import nsu.manasyan.buildingcompany.repositories.AreaFilter
import nsu.manasyan.buildingcompany.repositories.MaterialFilter
import nsu.manasyan.buildingcompany.services.MaterialsService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}/materials")
class MaterialsController(
    service: MaterialsService,
    mapper: MaterialMapper
) : AbstractCrudController<Material, MaterialDto>(service, mapper, "Material"){

    @GetMapping("/filter")
    fun getAllEntitiesByFilter(filter: MaterialFilter?, params: FindRequestParameters?) : PageDto<*> {
        return super.findAllByFilter(filter, params)
    }
}