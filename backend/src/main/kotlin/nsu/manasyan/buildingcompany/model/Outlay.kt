package nsu.manasyan.buildingcompany.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "Outlays")
class Outlay(var materialCount: Int) : Identifiable() {
    @ManyToOne
    @JoinColumn(name = "brigadeObjectWorkId", referencedColumnName = "id")
    lateinit var brigadeWork: BrigadeObjectWork

    @ManyToOne
    @JoinColumn(name = "MaterialId", referencedColumnName = "id")
    lateinit var material: Material
}

@Entity
@Table(name = "OutlayExceedances")
class OutlayExceedance(
    @MapsId("id")
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    var outlayRow: Outlay,
    var exceedanceCount: Int
) : Serializable, Identifiable()