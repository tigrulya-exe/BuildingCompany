package nsu.manasyan.buildingcompany.model

import nsu.manasyan.buildingcompany.technicalstuff.model.TechnicalSpecialist
import javax.persistence.*

@Entity
@Table(name = "ConstructionManagement")
class ConstructionManagement(manager: TechnicalSpecialist?) : Identifiable() {
    @OneToOne
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    var manager: TechnicalSpecialist? = manager
        set(value) {
            value?.post = Post.MANAGEMENT_MANAGER
            field = value
        }

    @OneToMany
    lateinit var areas: MutableSet<Area>
}