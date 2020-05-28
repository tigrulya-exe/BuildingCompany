package nsu.manasyan.buildingcompany.companypartsstuff.model

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import nsu.manasyan.buildingcompany.abstracts.model.Post
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
    var areas = mutableSetOf<Area>()
}