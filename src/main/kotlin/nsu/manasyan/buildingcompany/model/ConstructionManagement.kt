package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
@Table(name = "ConstructionManagement")
class ConstructionManagement (
    manager: TechnicalSpecialist,
    @Id @GeneratedValue
    var id: Int? = null
){
    @OneToOne()
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    var manager: TechnicalSpecialist = manager
        set(value){
            value.post = Post.MANAGEMENT_MANAGER
            field = value
        }

    @ManyToMany
    @JoinTable(
        name = "ConstructionManagementArea",
        joinColumns = [JoinColumn(name = "ConstructionManagementId")],
        inverseJoinColumns = [JoinColumn(name = "AreaId")]
    )
    lateinit var areas: MutableSet<Area>
}