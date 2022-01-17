package com.bethel.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.bethel.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserProgramTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserProgram.class);
        UserProgram userProgram1 = new UserProgram();
        userProgram1.setId(1L);
        UserProgram userProgram2 = new UserProgram();
        userProgram2.setId(userProgram1.getId());
        assertThat(userProgram1).isEqualTo(userProgram2);
        userProgram2.setId(2L);
        assertThat(userProgram1).isNotEqualTo(userProgram2);
        userProgram1.setId(null);
        assertThat(userProgram1).isNotEqualTo(userProgram2);
    }
}
