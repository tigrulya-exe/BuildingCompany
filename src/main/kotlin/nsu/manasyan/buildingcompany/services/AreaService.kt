package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.dto.mappers.AreaMapper
import nsu.manasyan.buildingcompany.model.Area
import nsu.manasyan.buildingcompany.repositories.AreaRepository
import org.springframework.stereotype.Service

@Service
class AreaService(repository: AreaRepository, mapper: AreaMapper) : AbstractCrudService<Area>(repository, mapper)