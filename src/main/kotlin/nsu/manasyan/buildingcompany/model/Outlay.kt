package nsu.manasyan.buildingcompany.model

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Outlays")
class Outlay(var materialCount: Int) : Identifiable() {
    @OneToOne
    @JoinColumn(name = "ScheduleRowId", referencedColumnName = "id")
    lateinit var scheduleRow: WorkSchedule

    @ManyToOne
    @JoinColumn(name = "MaterialId", referencedColumnName = "id")
    lateinit var material: Material
}

@Entity
@Table(name = "OutlayExceedances")
class OutlayExceedance (
    @Id
    @OneToOne
    @JoinColumn(name = "outlayId", referencedColumnName = "id")
    var outlayRow: Outlay,
    var exceedanceCount: Int
) : Serializable