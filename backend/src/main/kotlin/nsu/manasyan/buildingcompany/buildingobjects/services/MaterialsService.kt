package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.Material
import nsu.manasyan.buildingcompany.buildingobjects.repositories.MaterialsRepository
import org.springframework.stereotype.Service

@Service
class MaterialsService(materialsRepository: MaterialsRepository) : AbstractCrudService<Material>(materialsRepository)