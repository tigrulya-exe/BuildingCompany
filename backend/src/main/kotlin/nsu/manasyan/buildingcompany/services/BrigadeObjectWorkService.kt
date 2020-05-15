package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.BrigadeObjectWork
import nsu.manasyan.buildingcompany.repositories.BrigadeObjectWorkRepository
import org.springframework.stereotype.Service

@Service
class BrigadeObjectWorkService(
    private val bOWrepository: BrigadeObjectWorkRepository,
    private val brigadeService: BrigadeService,
    private val buildingObjectService: BuildingObjectService,
    private val workTypeService: WorkTypeService
) : AbstractCrudService<BrigadeObjectWork>(bOWrepository) {

    fun getOrCreate(brigadeId: Int, workTypeId: Int, objectId: Int) : BrigadeObjectWork {
        val brigade = brigadeService.getEntity(brigadeId)
        val workType = workTypeService.getEntity(workTypeId)
        val buildingObject = buildingObjectService.getEntity(objectId)

        val entity = bOWrepository
            .findDistinctByBrigadeAndWorkTypeAndBuildingObject(brigade, workType, buildingObject)
        return entity.orElse(bOWrepository.save(BrigadeObjectWork(buildingObject, workType, brigade)))
    }
}