package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.Outlay
import nsu.manasyan.buildingcompany.buildingobjects.repositories.OutlayRepository
import org.springframework.stereotype.Service

@Service
class OutlayService(outlayRepository: OutlayRepository) : AbstractCrudService<Outlay>(outlayRepository)