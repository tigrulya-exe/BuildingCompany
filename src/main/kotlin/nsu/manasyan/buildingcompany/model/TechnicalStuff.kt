package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
@Table(name = "TechnicalSpecialists")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class TechnicalSpecialist (
    name: String,
    surname: String,
    patronymic: String?,
    var educationalInstitution: String,
    var experienceYears: Int? = null
) : Person(name, surname, patronymic)

@Entity
@Table(name = "Foremen")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Foreman (
    name: String,
    surname: String,
    patronymic: String?,
    var knowledgeOfEnglish: Boolean,
    educationalInstitution: String,
    experienceYears: Int? = null
) : TechnicalSpecialist(name, surname, patronymic, educationalInstitution, experienceYears){
    override var post: Post? = Post.FOREMAN
}

@Entity
@Table(name = "Masters")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Master (
    name: String,
    surname: String,
    patronymic: String?,
    var category: Int,
    educationalInstitution: String,
    experienceYears: Int? = null
) : TechnicalSpecialist(name, surname, patronymic, educationalInstitution, experienceYears){
    override var post: Post? = Post.MASTER
}