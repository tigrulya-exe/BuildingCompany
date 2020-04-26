package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
class Brigade(
    @OneToOne()
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    var manager: Worker,
    @Id @GeneratedValue
    var id: Int? = null
) {
    @OneToMany(mappedBy = "brigade")
    lateinit var workers : MutableSet<Worker>
}