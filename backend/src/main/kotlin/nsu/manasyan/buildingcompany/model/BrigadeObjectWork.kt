package nsu.manasyan.buildingcompany.model

import nsu.manasyan.buildingcompany.model.workers.Brigade
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "BrigadeObjectWork")
class BrigadeObjectWork(
    @ManyToOne
    @JoinColumn(name = "objectId", referencedColumnName = "id")
    var buildingObject: BuildingObject,

    @ManyToOne
    @JoinColumn(name = "workTypeId", referencedColumnName = "id")
    var workType: WorkType,

    @ManyToOne
    @JoinColumn(name = "brigadeId", referencedColumnName = "id")
    var brigade: Brigade
) : Identifiable()