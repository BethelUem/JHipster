package com.bethel.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.bethel.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CatalogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatalogDTO.class);
        CatalogDTO catalogDTO1 = new CatalogDTO();
        catalogDTO1.setId(1L);
        CatalogDTO catalogDTO2 = new CatalogDTO();
        assertThat(catalogDTO1).isNotEqualTo(catalogDTO2);
        catalogDTO2.setId(catalogDTO1.getId());
        assertThat(catalogDTO1).isEqualTo(catalogDTO2);
        catalogDTO2.setId(2L);
        assertThat(catalogDTO1).isNotEqualTo(catalogDTO2);
        catalogDTO1.setId(null);
        assertThat(catalogDTO1).isNotEqualTo(catalogDTO2);
    }
}
