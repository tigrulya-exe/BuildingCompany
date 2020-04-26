package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
class Area(
    manager: TechnicalSpecialist,
    @Id @GeneratedValue
    var id: Int? = null
) {
    @OneToOne()
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    var manager: TechnicalSpecialist = manager
        set(value){
            value.post = Post.AREA_MANAGER
            field = value
        }

    @ManyToMany(mappedBy = "areas")
    var managements: MutableSet<ConstructionManagement> = mutableSetOf()
}