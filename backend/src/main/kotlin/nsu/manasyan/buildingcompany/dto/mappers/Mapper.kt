package nsu.manasyan.buildingcompany.dto.mappers

import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.dto.model.PageDto
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import java.util.stream.Collectors

abstract class Mapper<E, D : Dto<E>> {
    @Autowired
    lateinit var mapper: ModelMapper

    abstract fun toDto(entity: E): D

    abstract fun toEntity(dto: D): E

    fun toPageDto(entityPage: Page<E>): PageDto<*> {
        val dtoPage = PageImpl(toDtos(entityPage.content), entityPage.pageable, entityPage.totalElements)
        val pageDto = mapper.map(dtoPage, PageDto::class.java)
        try {
            pageDto.pageNumber = dtoPage.pageable.pageNumber
        } catch (exc: UnsupportedOperationException) {
            pageDto.pageNumber = 0
        }
        return pageDto;
    }

    private fun toDtos(entities: MutableList<E>): MutableList<D> {
        return entities.stream()
            .map { toDto(it) }
            .collect(Collectors.toList())
    }
}