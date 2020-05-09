package nsu.manasyan.buildingcompany.model.workers

import nsu.manasyan.buildingcompany.model.Area
import nsu.manasyan.buildingcompany.model.Post
import javax.persistence.*

@Entity
@Table(name = "TechnicalSpecialists")
@Inheritance(strategy = InheritanceType.JOINED)
class TechnicalSpecialist(
    name: String,
    surname: String,
    patronymic: String?,
    var educationalInstitution: String,
    var experienceYears: Int? = null
) : Person(name, surname, patronymic) {

    @ManyToOne
    @JoinColumn(name = "areaId", referencedColumnName = "Id")
    var area: Area? = null
}

@Entity
@Table(name = "Foremen")
@Inheritance(strategy = InheritanceType.JOINED)
class Foreman(
    name: String,
    surname: String,
    patronymic: String?,
    var knowledgeOfEnglish: Boolean,
    educationalInstitution: String,
    experienceYears: Int? = null
) : TechnicalSpecialist(name, surname, patronymic, educationalInstitution, experienceYears) {
    override var post: Post? =
        Post.FOREMAN
}

@Entity
@Table(name = "Masters")
@Inheritance(strategy = InheritanceType.JOINED)
class Master(
    name: String,
    surname: String,
    patronymic: String?,
    var category: Int,
    educationalInstitution: String,
    experienceYears: Int? = null
) : TechnicalSpecialist(name, surname, patronymic, educationalInstitution, experienceYears) {
    override var post: Post? =
        Post.MASTER
}