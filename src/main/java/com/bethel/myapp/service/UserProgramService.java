package com.bethel.myapp.service;

import com.bethel.myapp.service.dto.UserProgramDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.bethel.myapp.domain.UserProgram}.
 */
public interface UserProgramService {
    /**
     * Save a userProgram.
     *
     * @param userProgramDTO the entity to save.
     * @return the persisted entity.
     */
    UserProgramDTO save(UserProgramDTO userProgramDTO);

    /**
     * Partially updates a userProgram.
     *
     * @param userProgramDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserProgramDTO> partialUpdate(UserProgramDTO userProgramDTO);

    /**
     * Get all the userPrograms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserProgramDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userProgram.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserProgramDTO> findOne(Long id);

    /**
     * Delete the "id" userProgram.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
