package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.Material
import nsu.manasyan.buildingcompany.buildingobjects.repositories.MaterialsRepository
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import nsu.manasyan.buildingcompany.util.getNullableList
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class MaterialsService(
    private val materialsRepository: MaterialsRepository
) : AbstractCrudService<Material>(materialsRepository) {

    fun findByAreaManagementExceedance(
        areaIds: List<Int>?,
        managementIds: List<Int>?,
        params: FindRequestParameters?): Page<Material> {

        val pageable = getPageable(params)
        return materialsRepository.findByAreaManagementExceedance(
            getNullableList(areaIds),
            getNullableList(managementIds),
            pageable
        )
    }
}