package nsu.manasyan.buildingcompany.workers.model

import nsu.manasyan.buildingcompany.model.Post
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Transient


@Entity
@Table(name = "Masons")
class Mason(
    name: String,
    surname: String,
    patronymic: String?,
    var bricksPerHour: Int,
    experienceYears: Int? = null
) : Worker(name, surname, patronymic, experienceYears) {
    @Transient
    override var post:
            Post? = Post.MASON
}