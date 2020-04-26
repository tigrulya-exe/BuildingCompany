package nsu.manasyan.buildingcompany.model

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Identifiable {
    @Id
    @GeneratedValue
    var id: Int? = null
}