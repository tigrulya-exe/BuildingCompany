package nsu.manasyan.buildingcompany.abstracts.services

import nsu.manasyan.buildingcompany.abstracts.dto.NativeQueryResultsDto
import nsu.manasyan.buildingcompany.exceptions.NoDataFoundException
import nsu.manasyan.buildingcompany.logger
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.hibernate.exception.GenericJDBCException
import org.springframework.stereotype.Service
import java.math.BigInteger
import javax.persistence.EntityManager


@Service
class NativeQueryService(private val entityManager: EntityManager) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 500
    }

    fun getQueryResults(queryString: String, params: FindRequestParameters?): NativeQueryResultsDto {
        val page = getPage(params)
        val pageSize = getPageSize(params)
        var totalCount = 0

        return try {
            logger().info("New sql query: '$queryString'")

            val query = entityManager
                .createNativeQuery(queryString)
                .setFirstResult(page * pageSize)
                .setMaxResults(pageSize)

            if (query.resultList.size > 0) {
                totalCount = getTotalCount(queryString)
            }
            NativeQueryResultsDto(query.resultList, totalCount)
        } catch (exc: Exception) {
            logger().error("SQL query exception: ${exc.localizedMessage}")
            when (exc.cause) {
                is GenericJDBCException -> NativeQueryResultsDto(arrayListOf<Any>(), totalCount)
                else -> throw NoDataFoundException("No data")
            }
        }
    }

    private fun getTotalCount(query: String): Int {
        val countArray = entityManager
            .createNativeQuery("SELECT count(*) from($query) AS QUERY").resultList
        return if (countArray.size > 0) (countArray[0] as BigInteger).toInt() else 0
    }

    private fun getPageSize(params: FindRequestParameters?): Int {
        return params?.pageSize?.let { it } ?: DEFAULT_PAGE_SIZE
    }

    private fun getPage(params: FindRequestParameters?): Int {
        return params?.page?.let { it } ?: 0
    }
}