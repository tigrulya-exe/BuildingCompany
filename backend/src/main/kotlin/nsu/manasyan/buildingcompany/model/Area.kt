package nsu.manasyan.buildingcompany.model

import nsu.manasyan.buildingcompany.technicalstuff.model.TechnicalSpecialist
import javax.persistence.*

@Entity
@Table(name = "Areas")
class Area(manager: TechnicalSpecialist) : Identifiable() {
    @OneToOne
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    var manager: TechnicalSpecialist? = manager
        set(value) {
            value?.post = Post.AREA_MANAGER
            field = value
        }

    @ManyToOne
    @JoinColumn(name = "managementId", referencedColumnName = "id")
    var management: ConstructionManagement? = null
}