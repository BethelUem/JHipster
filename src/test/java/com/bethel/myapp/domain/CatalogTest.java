package com.bethel.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.bethel.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CatalogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Catalog.class);
        Catalog catalog1 = new Catalog();
        catalog1.setId(1L);
        Catalog catalog2 = new Catalog();
        catalog2.setId(catalog1.getId());
        assertThat(catalog1).isEqualTo(catalog2);
        catalog2.setId(2L);
        assertThat(catalog1).isNotEqualTo(catalog2);
        catalog1.setId(null);
        assertThat(catalog1).isNotEqualTo(catalog2);
    }
}
