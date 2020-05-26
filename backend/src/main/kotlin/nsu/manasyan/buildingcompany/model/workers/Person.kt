package nsu.manasyan.buildingcompany.model.workers

import nsu.manasyan.buildingcompany.model.Identifiable
import nsu.manasyan.buildingcompany.model.Post
import javax.persistence.*

@Entity
@Table(name = "Persons")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Person(
    var name: String,
    var surname: String,
    var patronymic: String?,
    @Enumerated(EnumType.STRING)
    open var post: Post? = null
) : Identifiable()