package com.bethel.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContentTagMapperTest {

    private ContentTagMapper contentTagMapper;

    @BeforeEach
    public void setUp() {
        contentTagMapper = new ContentTagMapperImpl();
    }
}
