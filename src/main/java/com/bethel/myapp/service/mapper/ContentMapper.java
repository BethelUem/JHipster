package com.bethel.myapp.service.mapper;

import com.bethel.myapp.domain.Content;
import com.bethel.myapp.service.dto.ContentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Content} and its DTO {@link ContentDTO}.
 */
@Mapper(componentModel = "spring", uses = { CatalogMapper.class })
public interface ContentMapper extends EntityMapper<ContentDTO, Content> {
    @Mapping(target = "catContentType", source = "catContentType", qualifiedByName = "id")
    @Mapping(target = "catTypeConduction", source = "catTypeConduction", qualifiedByName = "id")
    @Mapping(target = "catProgram", source = "catProgram", qualifiedByName = "id")
    @Mapping(target = "catShiftRecording", source = "catShiftRecording", qualifiedByName = "id")
    @Mapping(target = "catTargetAudience", source = "catTargetAudience", qualifiedByName = "id")
    @Mapping(target = "catCountry", source = "catCountry", qualifiedByName = "id")
    @Mapping(target = "catProductionCompany", source = "catProductionCompany", qualifiedByName = "id")
    @Mapping(target = "catArchivalCollection", source = "catArchivalCollection", qualifiedByName = "id")
    @Mapping(target = "catOriginalLanguage", source = "catOriginalLanguage", qualifiedByName = "id")
    @Mapping(target = "catInterpreterLanguage", source = "catInterpreterLanguage", qualifiedByName = "id")
    @Mapping(target = "catDubbingLanguage", source = "catDubbingLanguage", qualifiedByName = "id")
    @Mapping(target = "catSubtitleLanguage", source = "catSubtitleLanguage", qualifiedByName = "id")
    @Mapping(target = "catTvCensorship", source = "catTvCensorship", qualifiedByName = "id")
    @Mapping(target = "catClassificationCategory", source = "catClassificationCategory", qualifiedByName = "id")
    @Mapping(target = "catGenreProgram", source = "catGenreProgram", qualifiedByName = "id")
    @Mapping(target = "catFormatProgram", source = "catFormatProgram", qualifiedByName = "id")
    @Mapping(target = "catResolutionOriginal", source = "catResolutionOriginal", qualifiedByName = "id")
    @Mapping(target = "catResolutionEdited", source = "catResolutionEdited", qualifiedByName = "id")
    @Mapping(target = "catCatalogingLevelOriginal", source = "catCatalogingLevelOriginal", qualifiedByName = "id")
    @Mapping(target = "catCatalogingLevelEdited", source = "catCatalogingLevelEdited", qualifiedByName = "id")
    @Mapping(target = "catVideoQuality", source = "catVideoQuality", qualifiedByName = "id")
    @Mapping(target = "catAudioQuality", source = "catAudioQuality", qualifiedByName = "id")
    @Mapping(target = "catMusicalGroup", source = "catMusicalGroup", qualifiedByName = "id")
    @Mapping(target = "catMusicalGenre", source = "catMusicalGenre", qualifiedByName = "id")
    @Mapping(target = "catApproved", source = "catApproved", qualifiedByName = "id")
    ContentDTO toDto(Content s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContentDTO toDtoId(Content content);
}
