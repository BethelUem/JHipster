package com.bethel.myapp.service.mapper;

import com.bethel.myapp.domain.UserProgram;
import com.bethel.myapp.service.dto.UserProgramDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserProgram} and its DTO {@link UserProgramDTO}.
 */
@Mapper(componentModel = "spring", uses = { CatalogMapper.class })
public interface UserProgramMapper extends EntityMapper<UserProgramDTO, UserProgram> {
    @Mapping(target = "catProgram", source = "catProgram", qualifiedByName = "id")
    UserProgramDTO toDto(UserProgram s);
}
