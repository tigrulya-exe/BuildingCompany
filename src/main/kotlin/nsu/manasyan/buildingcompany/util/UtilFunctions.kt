package nsu.manasyan.buildingcompany.util

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

fun getPageable(parameters: FindRequestParameters?, sort: Sort): Pageable {
    return parameters?.page?.let { page ->
        parameters.size?.let { from ->
            sort.let {
                PageRequest.of(page, from, sort)
            }
        }
    } ?: Pageable.unpaged()
}

fun getSort(parameters: FindRequestParameters?): Sort {
    return parameters?.sortColumnName?.let {
        when (parameters.order) {
            FindRequestParameters.Order.DESCENDING -> Sort.by(it).descending()
            else -> Sort.by(it).ascending()
        }
    } ?: Sort.unsorted()
}

fun <T> findAllEntities(repository: JpaRepository<T, Int>, parameters: FindRequestParameters?): MutableList<T> {
    val sort = getSort(parameters)
    val pageable = getPageable(parameters, sort)
    with(repository) {
        return if (pageable.isPaged) findAll(pageable).content else findAll(sort)
    }
}