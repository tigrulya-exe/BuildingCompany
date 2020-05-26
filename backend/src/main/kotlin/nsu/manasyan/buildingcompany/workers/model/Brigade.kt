package nsu.manasyan.buildingcompany.workers.model

import nsu.manasyan.buildingcompany.model.ConstructionManagement
import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.workers.model.Worker
import javax.persistence.*

@Entity
@Table(name = "Brigades")
class Brigade(
    @OneToOne
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    var manager: Worker?,

    @ManyToOne
    @JoinColumn(name = "managementId", referencedColumnName = "id")
    var constructionManagement: ConstructionManagement
) : Identifiable() {
    @OneToMany(mappedBy = "brigade")
    lateinit var workers: MutableSet<Worker>
}