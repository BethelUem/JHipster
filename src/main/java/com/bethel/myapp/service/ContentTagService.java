package com.bethel.myapp.service;

import com.bethel.myapp.service.dto.ContentTagDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.bethel.myapp.domain.ContentTag}.
 */
public interface ContentTagService {
    /**
     * Save a contentTag.
     *
     * @param contentTagDTO the entity to save.
     * @return the persisted entity.
     */
    ContentTagDTO save(ContentTagDTO contentTagDTO);

    /**
     * Partially updates a contentTag.
     *
     * @param contentTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ContentTagDTO> partialUpdate(ContentTagDTO contentTagDTO);

    /**
     * Get all the contentTags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContentTagDTO> findAll(Pageable pageable);

    /**
     * Get the "id" contentTag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContentTagDTO> findOne(Long id);

    /**
     * Delete the "id" contentTag.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
