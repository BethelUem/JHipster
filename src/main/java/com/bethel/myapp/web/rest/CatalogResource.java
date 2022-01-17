package com.bethel.myapp.web.rest;

import com.bethel.myapp.repository.CatalogRepository;
import com.bethel.myapp.service.CatalogService;
import com.bethel.myapp.service.dto.CatalogDTO;
import com.bethel.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.bethel.myapp.domain.Catalog}.
 */
@RestController
@RequestMapping("/api")
public class CatalogResource {

    private final Logger log = LoggerFactory.getLogger(CatalogResource.class);

    private static final String ENTITY_NAME = "catalog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CatalogService catalogService;

    private final CatalogRepository catalogRepository;

    public CatalogResource(CatalogService catalogService, CatalogRepository catalogRepository) {
        this.catalogService = catalogService;
        this.catalogRepository = catalogRepository;
    }

    /**
     * {@code POST  /catalogs} : Create a new catalog.
     *
     * @param catalogDTO the catalogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new catalogDTO, or with status {@code 400 (Bad Request)} if the catalog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/catalogs")
    public ResponseEntity<CatalogDTO> createCatalog(@RequestBody CatalogDTO catalogDTO) throws URISyntaxException {
        log.debug("REST request to save Catalog : {}", catalogDTO);
        if (catalogDTO.getId() != null) {
            throw new BadRequestAlertException("A new catalog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatalogDTO result = catalogService.save(catalogDTO);
        return ResponseEntity
            .created(new URI("/api/catalogs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /catalogs/:id} : Updates an existing catalog.
     *
     * @param id the id of the catalogDTO to save.
     * @param catalogDTO the catalogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated catalogDTO,
     * or with status {@code 400 (Bad Request)} if the catalogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the catalogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/catalogs/{id}")
    public ResponseEntity<CatalogDTO> updateCatalog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CatalogDTO catalogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Catalog : {}, {}", id, catalogDTO);
        if (catalogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, catalogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!catalogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CatalogDTO result = catalogService.save(catalogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, catalogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /catalogs/:id} : Partial updates given fields of an existing catalog, field will ignore if it is null
     *
     * @param id the id of the catalogDTO to save.
     * @param catalogDTO the catalogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated catalogDTO,
     * or with status {@code 400 (Bad Request)} if the catalogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the catalogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the catalogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/catalogs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CatalogDTO> partialUpdateCatalog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CatalogDTO catalogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Catalog partially : {}, {}", id, catalogDTO);
        if (catalogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, catalogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!catalogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CatalogDTO> result = catalogService.partialUpdate(catalogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, catalogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /catalogs} : get all the catalogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of catalogs in body.
     */
    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogDTO>> getAllCatalogs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Catalogs");
        Page<CatalogDTO> page = catalogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /catalogs/:id} : get the "id" catalog.
     *
     * @param id the id of the catalogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the catalogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/catalogs/{id}")
    public ResponseEntity<CatalogDTO> getCatalog(@PathVariable Long id) {
        log.debug("REST request to get Catalog : {}", id);
        Optional<CatalogDTO> catalogDTO = catalogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catalogDTO);
    }

    /**
     * {@code DELETE  /catalogs/:id} : delete the "id" catalog.
     *
     * @param id the id of the catalogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/catalogs/{id}")
    public ResponseEntity<Void> deleteCatalog(@PathVariable Long id) {
        log.debug("REST request to delete Catalog : {}", id);
        catalogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
