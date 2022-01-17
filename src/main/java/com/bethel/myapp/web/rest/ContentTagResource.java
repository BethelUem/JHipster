package com.bethel.myapp.web.rest;

import com.bethel.myapp.repository.ContentTagRepository;
import com.bethel.myapp.service.ContentTagService;
import com.bethel.myapp.service.dto.ContentTagDTO;
import com.bethel.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.bethel.myapp.domain.ContentTag}.
 */
@RestController
@RequestMapping("/api")
public class ContentTagResource {

    private final Logger log = LoggerFactory.getLogger(ContentTagResource.class);

    private static final String ENTITY_NAME = "contentTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContentTagService contentTagService;

    private final ContentTagRepository contentTagRepository;

    public ContentTagResource(ContentTagService contentTagService, ContentTagRepository contentTagRepository) {
        this.contentTagService = contentTagService;
        this.contentTagRepository = contentTagRepository;
    }

    /**
     * {@code POST  /content-tags} : Create a new contentTag.
     *
     * @param contentTagDTO the contentTagDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contentTagDTO, or with status {@code 400 (Bad Request)} if the contentTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/content-tags")
    public ResponseEntity<ContentTagDTO> createContentTag(@Valid @RequestBody ContentTagDTO contentTagDTO) throws URISyntaxException {
        log.debug("REST request to save ContentTag : {}", contentTagDTO);
        if (contentTagDTO.getId() != null) {
            throw new BadRequestAlertException("A new contentTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContentTagDTO result = contentTagService.save(contentTagDTO);
        return ResponseEntity
            .created(new URI("/api/content-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /content-tags/:id} : Updates an existing contentTag.
     *
     * @param id the id of the contentTagDTO to save.
     * @param contentTagDTO the contentTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contentTagDTO,
     * or with status {@code 400 (Bad Request)} if the contentTagDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contentTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/content-tags/{id}")
    public ResponseEntity<ContentTagDTO> updateContentTag(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContentTagDTO contentTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ContentTag : {}, {}", id, contentTagDTO);
        if (contentTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contentTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contentTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContentTagDTO result = contentTagService.save(contentTagDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contentTagDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /content-tags/:id} : Partial updates given fields of an existing contentTag, field will ignore if it is null
     *
     * @param id the id of the contentTagDTO to save.
     * @param contentTagDTO the contentTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contentTagDTO,
     * or with status {@code 400 (Bad Request)} if the contentTagDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contentTagDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contentTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/content-tags/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContentTagDTO> partialUpdateContentTag(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContentTagDTO contentTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContentTag partially : {}, {}", id, contentTagDTO);
        if (contentTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contentTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contentTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContentTagDTO> result = contentTagService.partialUpdate(contentTagDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contentTagDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /content-tags} : get all the contentTags.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contentTags in body.
     */
    @GetMapping("/content-tags")
    public ResponseEntity<List<ContentTagDTO>> getAllContentTags(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ContentTags");
        Page<ContentTagDTO> page = contentTagService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /content-tags/:id} : get the "id" contentTag.
     *
     * @param id the id of the contentTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contentTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/content-tags/{id}")
    public ResponseEntity<ContentTagDTO> getContentTag(@PathVariable Long id) {
        log.debug("REST request to get ContentTag : {}", id);
        Optional<ContentTagDTO> contentTagDTO = contentTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contentTagDTO);
    }

    /**
     * {@code DELETE  /content-tags/:id} : delete the "id" contentTag.
     *
     * @param id the id of the contentTagDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/content-tags/{id}")
    public ResponseEntity<Void> deleteContentTag(@PathVariable Long id) {
        log.debug("REST request to delete ContentTag : {}", id);
        contentTagService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
