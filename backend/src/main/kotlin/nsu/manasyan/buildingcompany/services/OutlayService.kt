package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.Outlay
import nsu.manasyan.buildingcompany.repositories.OutlayRepository
import org.springframework.stereotype.Service

@Service
class OutlayService(outlayRepository: OutlayRepository) : AbstractCrudService<Outlay>(outlayRepository)