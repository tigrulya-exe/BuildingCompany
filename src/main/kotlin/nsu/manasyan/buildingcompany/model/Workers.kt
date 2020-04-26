package nsu.manasyan.buildingcompany.model

import javax.persistence.*

@Entity
@Table(name = "Persons")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Person(var name: String,
                      var surname: String,
                      var patronymic: String?,
                      @Id @GeneratedValue
                      var id:Int? = null,
                      @Transient
                      open var post: Post? = null)

@Entity
@Table(name = "Workers")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Worker (
    name: String,
    surname: String,
    patronymic: String?,
    var experienceYears: Int? = null
) : Person(name, surname, patronymic){

    @ManyToOne
    @JoinTable(
        name = "BrigadeWorker",
        joinColumns = [JoinColumn(name = "WorkerId")],
        inverseJoinColumns = [JoinColumn(name = "BrigadeId")]
    )
    lateinit var brigade: Brigade
}

@Entity
@Table(name = "Masons")
class Mason(
    name: String,
    surname: String,
    patronymic: String?,
    var bricksPerHour: Int,
    experienceYears: Int? = null
) : Worker(name, surname, patronymic, experienceYears){
    override var post: Post? = Post.MASON
}

@Entity
@Table(name = "Locksmiths")
class Locksmith(
    name: String,
    surname: String,
    patronymic: String?,
    var category: Int,
    var higherEducation: Boolean,
    experienceYears: Int? = null
) : Worker(name, surname, patronymic, experienceYears){
    override var post: Post? = Post.LOCKSMITH
}

