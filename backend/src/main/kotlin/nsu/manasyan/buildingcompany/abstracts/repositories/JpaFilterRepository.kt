package nsu.manasyan.buildingcompany.abstracts.repositories

import nsu.manasyan.buildingcompany.util.filters.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface JpaFilterRepository<E, Id> : JpaRepository<E, Id>, JpaSpecificationExecutor<E> {
    fun findAllByFilter(filter: Filter<in E>?, pageable: Pageable): Page<E>
}