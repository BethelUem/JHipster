package com.bethel.myapp.repository;

import com.bethel.myapp.domain.UserProgram;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UserProgram entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserProgramRepository extends JpaRepository<UserProgram, Long> {}
