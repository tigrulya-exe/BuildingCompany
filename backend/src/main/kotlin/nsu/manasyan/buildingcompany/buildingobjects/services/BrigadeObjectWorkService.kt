package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.BrigadeObjectWork
import nsu.manasyan.buildingcompany.buildingobjects.repositories.BrigadeObjectWorkRepository
import nsu.manasyan.buildingcompany.workers.services.BrigadeService
import org.springframework.stereotype.Service

@Service
class BrigadeObjectWorkService(
    private val bOWrepository: BrigadeObjectWorkRepository,
    private val brigadeService: BrigadeService,
    private val buildingObjectService: BuildingObjectService,
    private val workTypeService: WorkTypeService
) : AbstractCrudService<BrigadeObjectWork>(bOWrepository) {

    fun getOrCreate(brigadeId: Int, workTypeName: String, objectId: Int): BrigadeObjectWork {
        val brigade = brigadeService.getEntity(brigadeId)
        val workType = workTypeService.getOrCreateByName(workTypeName)
        val buildingObject = buildingObjectService.getEntity(objectId)

        val entity = bOWrepository
            .findDistinctByBrigadeAndWorkTypeAndBuildingObject(brigade, workType, buildingObject)
        return entity.orElse(bOWrepository.save(
            BrigadeObjectWork(
                buildingObject,
                workType,
                brigade
            )
        ))
    }
}