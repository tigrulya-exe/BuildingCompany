package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.logger
import org.springframework.stereotype.Service
import javax.persistence.EntityManager


@Service
class NativeQueryService(private val entityManager: EntityManager) {
    fun getQueryResults(query: String): MutableList<*> {
        return try {
            logger().info("New sql query: '$query'")
            entityManager.createNativeQuery(query).resultList
        } catch (exc: Exception) {
            logger().error("SQL query exception: ${exc.localizedMessage}")
            arrayListOf<Any>();
            //            throw NoDataFoundException("No data")
        }
    }
}