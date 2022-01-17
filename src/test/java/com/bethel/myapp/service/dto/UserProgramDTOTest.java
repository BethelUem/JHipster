package com.bethel.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.bethel.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserProgramDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserProgramDTO.class);
        UserProgramDTO userProgramDTO1 = new UserProgramDTO();
        userProgramDTO1.setId(1L);
        UserProgramDTO userProgramDTO2 = new UserProgramDTO();
        assertThat(userProgramDTO1).isNotEqualTo(userProgramDTO2);
        userProgramDTO2.setId(userProgramDTO1.getId());
        assertThat(userProgramDTO1).isEqualTo(userProgramDTO2);
        userProgramDTO2.setId(2L);
        assertThat(userProgramDTO1).isNotEqualTo(userProgramDTO2);
        userProgramDTO1.setId(null);
        assertThat(userProgramDTO1).isNotEqualTo(userProgramDTO2);
    }
}
