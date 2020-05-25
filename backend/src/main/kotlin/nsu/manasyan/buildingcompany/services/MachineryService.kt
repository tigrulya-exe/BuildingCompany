package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.Machinery
import nsu.manasyan.buildingcompany.repositories.MachineryFilter
import nsu.manasyan.buildingcompany.repositories.MachineryRepository
import nsu.manasyan.buildingcompany.repositories.WorkScheduleFilter
import nsu.manasyan.buildingcompany.repositories.WorkScheduleRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Service
class MachineryService(
    private val machineryRepository: MachineryRepository
) :
    AbstractCrudService<Machinery>(machineryRepository) {

    fun getByBuildingObject(startDateMin: Date?,
                            startDateMax: Date?,
                            buildingObjectId: Int?,
                            params: FindRequestParameters?) : Page<Machinery> {
        val pageable = getPageable(params)
        return machineryRepository.findByBuildingObjectOrWorkSchedule(startDateMin, startDateMax, buildingObjectId, pageable)
    }


}