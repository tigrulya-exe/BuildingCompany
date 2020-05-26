package nsu.manasyan.buildingcompany.workers.model

import nsu.manasyan.buildingcompany.model.Post
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Transient

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
    @Transient
    override var post: Post? =
        Post.LOCKSMITH
}