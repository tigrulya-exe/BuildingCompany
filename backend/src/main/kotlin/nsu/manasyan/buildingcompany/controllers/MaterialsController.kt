package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.dto.mappers.MaterialMapper
import nsu.manasyan.buildingcompany.dto.model.MaterialDto
import nsu.manasyan.buildingcompany.model.Material
import nsu.manasyan.buildingcompany.services.MaterialsService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application-path}/materials")
class MaterialsController(
    service: MaterialsService,
    mapper: MaterialMapper
) : AbstractCrudController<Material, MaterialDto>(service, mapper, "Material")