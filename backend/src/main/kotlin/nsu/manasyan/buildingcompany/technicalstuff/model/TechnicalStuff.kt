package nsu.manasyan.buildingcompany.technicalstuff.model

import nsu.manasyan.buildingcompany.companypartsstuff.model.Area
import nsu.manasyan.buildingcompany.abstracts.model.Post
import nsu.manasyan.buildingcompany.abstracts.model.Person
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
class Foreman(
    name: String,
    surname: String,
    patronymic: String?,
    var knowledgeOfEnglish: Boolean,
    educationalInstitution: String,
    experienceYears: Int? = null
) : TechnicalSpecialist(name, surname, patronymic, educationalInstitution, experienceYears) {
    @Enumerated(EnumType.STRING)
    override var post: Post? =
        Post.FOREMAN
}

@Entity
@Table(name = "Masters")
class Master(
    name: String,
    surname: String,
    patronymic: String?,
    var category: Int,
    educationalInstitution: String,
    experienceYears: Int? = null
) : TechnicalSpecialist(name, surname, patronymic, educationalInstitution, experienceYears) {
    @Enumerated(EnumType.STRING)
    override var post: Post? =
        Post.MASTER
}