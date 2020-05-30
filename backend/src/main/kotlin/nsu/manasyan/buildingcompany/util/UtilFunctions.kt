package nsu.manasyan.buildingcompany.util

import nsu.manasyan.buildingcompany.abstracts.model.Identifiable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import java.util.stream.Collectors

fun getPageable(parameters: FindRequestParameters?, sort: Sort): Pageable {
    return parameters?.page?.let { page ->
        parameters.pageSize?.let { from ->
            sort.let {
                PageRequest.of(page, from, sort)
            }
        }
    } ?: Pageable.unpaged()
}

fun <E> getNullableList(list: List<E>?) : List<E>?{
    return if(list?.size != 0) list else null
}

fun getSort(parameters: FindRequestParameters?): Sort {
    return parameters?.orderBy?.let {
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
