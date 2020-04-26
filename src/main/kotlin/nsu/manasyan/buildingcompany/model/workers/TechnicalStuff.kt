package nsu.manasyan.buildingcompany.model.workers

import nsu.manasyan.buildingcompany.model.Post
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.Table

@Entity
@Table(name = "TechnicalSpecialists")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class TechnicalSpecialist(
    name: String,
    surname: String,
    patronymic: String?,
    var educationalInstitution: String,
    var experienceYears: Int? = null
) : Person(name, surname, patronymic)

@Entity
@Table(name = "Foremen")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Foreman(
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
abstract class Master(
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