package nsu.manasyan.buildingcompany.workers.model

import nsu.manasyan.buildingcompany.model.Post
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table


@Entity
@Table(name = "Masons")
class Mason(
    name: String,
    surname: String,
    patronymic: String?,
    var bricksPerHour: Int,
    experienceYears: Int? = null
) : Worker(name, surname, patronymic, experienceYears) {
    @Enumerated(EnumType.STRING)
    override var post: Post? = Post.MASON
}