package nsu.manasyan.buildingcompany.model

import nsu.manasyan.buildingcompany.model.workers.Brigade
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "WorkSchedule")
class WorkSchedule(var startDate: Date, var endDate: Date) : Identifiable() {
    @ManyToOne
    @JoinColumn(name = "objectId", referencedColumnName = "id")
    lateinit var buildingObject: BuildingObject

    @ManyToOne
    @JoinColumn(name = "workTypeId", referencedColumnName = "id")
    lateinit var workType: WorkType

    @ManyToOne
    @JoinColumn(name = "brigadeId", referencedColumnName = "id")
    lateinit var brigade: Brigade
}

@Entity
@Table(name = "ScheduleDelays")
class ScheduleDelay(
    @MapsId("id")
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    var scheduleRow: WorkSchedule,
    var delay: Date
) : Serializable, Identifiable()