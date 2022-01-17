package com.bethel.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatalogMapperTest {

    private CatalogMapper catalogMapper;

    @BeforeEach
    public void setUp() {
        catalogMapper = new CatalogMapperImpl();
    }
}
