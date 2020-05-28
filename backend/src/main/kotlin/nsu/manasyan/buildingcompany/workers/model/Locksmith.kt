package nsu.manasyan.buildingcompany.workers.model

import nsu.manasyan.buildingcompany.abstracts.model.Post
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "Locksmiths")
class Locksmith(
    name: String,
    surname: String,
    patronymic: String?,
    var category: Int,
    var higherEducation: Boolean,
    experienceYears: Int? = null
) : Worker(name, surname, patronymic, experienceYears) {

    @Enumerated(EnumType.STRING)
    override var post: Post? = null
}