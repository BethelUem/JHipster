package com.bethel.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.bethel.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContentTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContentTagDTO.class);
        ContentTagDTO contentTagDTO1 = new ContentTagDTO();
        contentTagDTO1.setId(1L);
        ContentTagDTO contentTagDTO2 = new ContentTagDTO();
        assertThat(contentTagDTO1).isNotEqualTo(contentTagDTO2);
        contentTagDTO2.setId(contentTagDTO1.getId());
        assertThat(contentTagDTO1).isEqualTo(contentTagDTO2);
        contentTagDTO2.setId(2L);
        assertThat(contentTagDTO1).isNotEqualTo(contentTagDTO2);
        contentTagDTO1.setId(null);
        assertThat(contentTagDTO1).isNotEqualTo(contentTagDTO2);
    }
}
