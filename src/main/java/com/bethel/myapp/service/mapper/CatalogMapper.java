package com.bethel.myapp.service.mapper;

import com.bethel.myapp.domain.Catalog;
import com.bethel.myapp.service.dto.CatalogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Catalog} and its DTO {@link CatalogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CatalogMapper extends EntityMapper<CatalogDTO, Catalog> {
    @Mapping(target = "parentId", source = "parentId", qualifiedByName = "id")
    CatalogDTO toDto(Catalog s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CatalogDTO toDtoId(Catalog catalog);
}
