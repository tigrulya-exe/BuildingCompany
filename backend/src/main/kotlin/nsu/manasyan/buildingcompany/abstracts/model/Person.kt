package nsu.manasyan.buildingcompany.abstracts.model

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