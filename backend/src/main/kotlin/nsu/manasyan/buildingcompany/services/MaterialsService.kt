package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.Material
import nsu.manasyan.buildingcompany.repositories.MaterialsRepository
import org.springframework.stereotype.Service

@Service
class MaterialsService(materialsRepository: MaterialsRepository) : AbstractCrudService<Material>(materialsRepository)