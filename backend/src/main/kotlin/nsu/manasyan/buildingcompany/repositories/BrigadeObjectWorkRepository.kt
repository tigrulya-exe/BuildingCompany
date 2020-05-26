package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.BrigadeObjectWork
import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import nsu.manasyan.buildingcompany.model.WorkType
import nsu.manasyan.buildingcompany.workers.model.Brigade
import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BrigadeObjectWorkRepository : JpaFilterRepository<BrigadeObjectWork, Int>{
    @Query(
        """
        select b
        from BrigadeObjectWork b
        where (:#{#filter.brigadeId} is null or b.brigade.id = :#{#filter.brigadeId})
        and (:#{#filter.objectId} is null or b.buildingObject.id = :#{#filter.objectId})
        and (:#{#filter.workTypeId} is null or b.workType.id = :#{#filter.workTypeId})
    """
    )
    override fun findAllByFilter(filter: Filter<in BrigadeObjectWork>?, pageable: Pageable): Page<BrigadeObjectWork>

    fun findDistinctByBrigadeAndWorkTypeAndBuildingObject(
        brigade: Brigade,
        workType: WorkType,
        buildingObject: BuildingObject
    ): Optional<BrigadeObjectWork>
}