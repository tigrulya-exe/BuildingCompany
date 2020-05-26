package nsu.manasyan.buildingcompany.model

import nsu.manasyan.buildingcompany.buildingobjects.model.BuildingObject
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "Outlays")
class Outlay(var materialCount: Int) : Identifiable() {
    @OneToOne
    @JoinColumn(name = "objectId", referencedColumnName = "id")
    lateinit var buildingObject: BuildingObject

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