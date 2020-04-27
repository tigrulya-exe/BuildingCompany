package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.exceptions.NoDataFoundException
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class NativeQueryService(private val entityManager: EntityManager) {
    fun getQueryResults(query: String): MutableList<*> {
        try {
            return entityManager.createNativeQuery(query).resultList
        } catch (exc : Exception){
            throw NoDataFoundException("No data")
        }
    }
}