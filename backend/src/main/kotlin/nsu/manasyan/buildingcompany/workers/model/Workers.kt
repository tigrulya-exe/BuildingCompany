package nsu.manasyan.buildingcompany.workers.model

import nsu.manasyan.buildingcompany.model.workers.Person
import javax.persistence.*

@Entity
@Table(name = "Workers")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Worker(
    name: String,
    surname: String,
    patronymic: String?,
    var experienceYears: Int? = null
) : Person(name, surname, patronymic) {

    @ManyToOne
    @JoinColumn(name = "brigadeId", referencedColumnName = "id")
    var brigade: Brigade? = null
}