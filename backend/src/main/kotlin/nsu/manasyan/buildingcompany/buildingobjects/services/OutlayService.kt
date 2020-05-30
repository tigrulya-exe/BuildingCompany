package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.Outlay
import nsu.manasyan.buildingcompany.buildingobjects.model.OutlayExceedance
import nsu.manasyan.buildingcompany.buildingobjects.repositories.OutlayExceedanceRepository
import nsu.manasyan.buildingcompany.buildingobjects.repositories.OutlayRepository
import org.springframework.stereotype.Service

@Service
class OutlayService(
    outlayRepository: OutlayRepository,
    private val outlayExceedanceRepository: OutlayExceedanceRepository
) : AbstractCrudService<Outlay>(outlayRepository) {

    fun getExceedanceByOutlayId(outlayId: Int): OutlayExceedance? {
        val outlay = getEntity(outlayId)
        return outlayExceedanceRepository
            .getByOutlayRow(outlay)
            .orElseGet(null)
    }
}