package com.bethel.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.bethel.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContentTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContentTag.class);
        ContentTag contentTag1 = new ContentTag();
        contentTag1.setId(1L);
        ContentTag contentTag2 = new ContentTag();
        contentTag2.setId(contentTag1.getId());
        assertThat(contentTag1).isEqualTo(contentTag2);
        contentTag2.setId(2L);
        assertThat(contentTag1).isNotEqualTo(contentTag2);
        contentTag1.setId(null);
        assertThat(contentTag1).isNotEqualTo(contentTag2);
    }
}
