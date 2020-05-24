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
    private val machineryRepository: MachineryRepository,
    private val workScheduleRepository: WorkScheduleRepository
) :
    AbstractCrudService<Machinery>(machineryRepository) {

    fun getByBuildingObject(startDateMin: Date?,
                            startDateMax: Date?,
                            buildingObjectId: Int?,
                            params: FindRequestParameters?) : Page<Machinery> {
        val pageable = getPageable(params)
        val machineryFilter = MachineryFilter(null, null, buildingObjectId)
        val firstPart = workScheduleRepository
            .findMachineryByObjectAndDates(startDateMin, startDateMax, buildingObjectId, pageable)
        val secondPart = machineryRepository.findAllByFilter(machineryFilter, pageable)
        val result = firstPart.content.union(secondPart.content).toList()

        return PageImpl(result, pageable, result.size.toLong())
    }


}