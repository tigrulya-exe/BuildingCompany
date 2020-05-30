package nsu.manasyan.buildingcompany.buildingobjects.repositories

import nsu.manasyan.buildingcompany.abstracts.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkType
import nsu.manasyan.buildingcompany.util.filters.Filter
import nsu.manasyan.buildingcompany.util.filters.FilterStringDelegate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WorkTypeRepository :
    JpaFilterRepository<WorkType, Int> {
    @Query(
        """
        select w   
        from WorkType w
        where (:#{#filter.name} is null or lower(w.name) like :#{#filter.name})
    """
    )
    override fun findAllByFilter(
        @Param("filter") filter: Filter<in WorkType>?,
        pageable: Pageable
    ): Page<WorkType>

    @Query("""
        select wt
        from WorkType wt join BuildingObject b
        on b member wt.buildingObjects
        where :buildingObjectId = b.id
    """)
    fun findByBuildingObject(buildingObjectId: Int, pageable: Pageable) : Page<WorkType>

    @Query("""
        select wt
        from WorkType wt 
        join BrigadeObjectWork bow on bow.workType = wt
        join WorkSchedule ws on ws.brigadeWork = bow
        join ScheduleDelay sd on sd = sd.scheduleRow
        left join Area a on a = ws.brigadeWork.buildingObject.area
        where (coalesce(:areaIds, :areaIds) is null or a.id in :areaIds)
        and (coalesce(:managementIds, :managementIds) is null or a.management.id in :managementIds)
    """)
    fun findByAreaManagementDelay(
        areaIds: List<Int>?,
        managementIds: List<Int>?,
        pageable: Pageable
    ) : Page<WorkType>

    fun findByNameIgnoreCase(name: String): Optional<WorkType>
}

class WorkTypeFilter : Filter<WorkType> {
    var name: String? by FilterStringDelegate()
}