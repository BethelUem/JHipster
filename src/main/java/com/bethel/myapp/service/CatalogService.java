package com.bethel.myapp.service;

import com.bethel.myapp.service.dto.CatalogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.bethel.myapp.domain.Catalog}.
 */
public interface CatalogService {
    /**
     * Save a catalog.
     *
     * @param catalogDTO the entity to save.
     * @return the persisted entity.
     */
    CatalogDTO save(CatalogDTO catalogDTO);

    /**
     * Partially updates a catalog.
     *
     * @param catalogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CatalogDTO> partialUpdate(CatalogDTO catalogDTO);

    /**
     * Get all the catalogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CatalogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" catalog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CatalogDTO> findOne(Long id);

    /**
     * Delete the "id" catalog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
