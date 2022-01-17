package com.bethel.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserProgramMapperTest {

    private UserProgramMapper userProgramMapper;

    @BeforeEach
    public void setUp() {
        userProgramMapper = new UserProgramMapperImpl();
    }
}
