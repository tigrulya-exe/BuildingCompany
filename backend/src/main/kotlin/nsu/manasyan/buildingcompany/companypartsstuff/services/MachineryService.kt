package nsu.manasyan.buildingcompany.companypartsstuff.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.companypartsstuff.model.Machinery
import nsu.manasyan.buildingcompany.companypartsstuff.repositories.MachineryRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class MachineryService(
    private val machineryRepository: MachineryRepository
) :
    AbstractCrudService<Machinery>(machineryRepository) {

    fun getByBuildingObject(
        startDateMin: Date?,
        startDateMax: Date?,
        buildingObjectId: Int?,
        params: FindRequestParameters?
    ): Page<Machinery> {
        val pageable = getPageable(params)
        return machineryRepository.findByBuildingObjectOrWorkSchedule(
            startDateMin,
            startDateMax,
            buildingObjectId,
            pageable
        )
    }

    fun getByConstructionManagements(
        constructionManagementIds: List<Int>,
        params: FindRequestParameters?
    ): Page<Machinery> {
        val pageable = getPageable(params)
        return machineryRepository.findByConstructionManagements(constructionManagementIds, pageable)
    }

}