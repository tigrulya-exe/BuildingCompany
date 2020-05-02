package nsu.manasyan.buildingcompany.util

import nsu.manasyan.buildingcompany.model.Identifiable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import java.util.stream.Collectors

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

fun <E : Identifiable> identifiablesToIds(entities: MutableSet<E>): MutableSet<Int> {
    return entities.stream()
        .map { it.id }
        .collect(Collectors.toSet())
}