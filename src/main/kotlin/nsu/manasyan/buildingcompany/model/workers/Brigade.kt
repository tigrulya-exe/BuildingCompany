package nsu.manasyan.buildingcompany.model.workers

import nsu.manasyan.buildingcompany.model.Identifiable
import javax.persistence.*

@Entity
@Table(name = "Brigades")
class Brigade(
    @OneToOne()
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    var manager: Worker
) : Identifiable() {
    @OneToMany(mappedBy = "brigade")
    lateinit var workers: MutableSet<Worker>
}