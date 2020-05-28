package nsu.manasyan.buildingcompany.buildingobjects.model

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import nsu.manasyan.buildingcompany.companypartsstuff.model.Machinery
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "WorkSchedule")
class WorkSchedule(var startDate: Date, var endDate: Date) : Identifiable() {
    @ManyToOne
    @JoinColumn(name = "brigadeObjectWorkId", referencedColumnName = "id")
    lateinit var brigadeWork: BrigadeObjectWork

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "WorkScheduleMachinery")
    var machinery = mutableListOf<Machinery>()
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