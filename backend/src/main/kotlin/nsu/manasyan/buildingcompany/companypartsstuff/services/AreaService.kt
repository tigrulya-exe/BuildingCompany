package nsu.manasyan.buildingcompany.companypartsstuff.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.companypartsstuff.model.Area
import nsu.manasyan.buildingcompany.companypartsstuff.repositories.AreaRepository
import org.springframework.stereotype.Service

@Service
class AreaService(repository: AreaRepository) : AbstractCrudService<Area>(repository)