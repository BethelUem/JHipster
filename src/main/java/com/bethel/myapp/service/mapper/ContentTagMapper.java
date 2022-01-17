package com.bethel.myapp.service.mapper;

import com.bethel.myapp.domain.ContentTag;
import com.bethel.myapp.service.dto.ContentTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContentTag} and its DTO {@link ContentTagDTO}.
 */
@Mapper(componentModel = "spring", uses = { ContentMapper.class, CatalogMapper.class })
public interface ContentTagMapper extends EntityMapper<ContentTagDTO, ContentTag> {
    @Mapping(target = "contentId", source = "contentId", qualifiedByName = "id")
    @Mapping(target = "catalogId", source = "catalogId", qualifiedByName = "id")
    ContentTagDTO toDto(ContentTag s);
}
