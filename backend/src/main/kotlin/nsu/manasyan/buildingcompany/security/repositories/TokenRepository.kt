package nsu.manasyan.buildingcompany.security.repositories

import nsu.manasyan.buildingcompany.security.model.Token
import nsu.manasyan.buildingcompany.security.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TokenRepository : JpaRepository<Token, Int> {
    fun deleteByUserAndType(user: User, type: Token.Type)

    fun findByStringRepresentationAndType(stringRepresentation: String, type: Token.Type): Optional<Token>
}