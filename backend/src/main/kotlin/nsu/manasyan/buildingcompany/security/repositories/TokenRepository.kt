package nsu.manasyan.buildingcompany.security.repositories

import nsu.manasyan.buildingcompany.security.model.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TokenRepository : JpaRepository<Token, Int> {
    fun findByType(type: Token.Type): Optional<Token>

    fun findByStringRepresentationAndType(stringRepresentation: String, type: Token.Type): Optional<Token>
}