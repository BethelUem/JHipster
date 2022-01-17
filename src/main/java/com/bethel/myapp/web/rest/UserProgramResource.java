package com.bethel.myapp.web.rest;

import com.bethel.myapp.repository.UserProgramRepository;
import com.bethel.myapp.service.UserProgramService;
import com.bethel.myapp.service.dto.UserProgramDTO;
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
 * REST controller for managing {@link com.bethel.myapp.domain.UserProgram}.
 */
@RestController
@RequestMapping("/api")
public class UserProgramResource {

    private final Logger log = LoggerFactory.getLogger(UserProgramResource.class);

    private static final String ENTITY_NAME = "userProgram";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserProgramService userProgramService;

    private final UserProgramRepository userProgramRepository;

    public UserProgramResource(UserProgramService userProgramService, UserProgramRepository userProgramRepository) {
        this.userProgramService = userProgramService;
        this.userProgramRepository = userProgramRepository;
    }

    /**
     * {@code POST  /user-programs} : Create a new userProgram.
     *
     * @param userProgramDTO the userProgramDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userProgramDTO, or with status {@code 400 (Bad Request)} if the userProgram has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-programs")
    public ResponseEntity<UserProgramDTO> createUserProgram(@RequestBody UserProgramDTO userProgramDTO) throws URISyntaxException {
        log.debug("REST request to save UserProgram : {}", userProgramDTO);
        if (userProgramDTO.getId() != null) {
            throw new BadRequestAlertException("A new userProgram cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserProgramDTO result = userProgramService.save(userProgramDTO);
        return ResponseEntity
            .created(new URI("/api/user-programs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-programs/:id} : Updates an existing userProgram.
     *
     * @param id the id of the userProgramDTO to save.
     * @param userProgramDTO the userProgramDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userProgramDTO,
     * or with status {@code 400 (Bad Request)} if the userProgramDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userProgramDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-programs/{id}")
    public ResponseEntity<UserProgramDTO> updateUserProgram(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserProgramDTO userProgramDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserProgram : {}, {}", id, userProgramDTO);
        if (userProgramDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userProgramDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userProgramRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserProgramDTO result = userProgramService.save(userProgramDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userProgramDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-programs/:id} : Partial updates given fields of an existing userProgram, field will ignore if it is null
     *
     * @param id the id of the userProgramDTO to save.
     * @param userProgramDTO the userProgramDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userProgramDTO,
     * or with status {@code 400 (Bad Request)} if the userProgramDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userProgramDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userProgramDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-programs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserProgramDTO> partialUpdateUserProgram(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserProgramDTO userProgramDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserProgram partially : {}, {}", id, userProgramDTO);
        if (userProgramDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userProgramDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userProgramRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserProgramDTO> result = userProgramService.partialUpdate(userProgramDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userProgramDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /user-programs} : get all the userPrograms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userPrograms in body.
     */
    @GetMapping("/user-programs")
    public ResponseEntity<List<UserProgramDTO>> getAllUserPrograms(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of UserPrograms");
        Page<UserProgramDTO> page = userProgramService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-programs/:id} : get the "id" userProgram.
     *
     * @param id the id of the userProgramDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userProgramDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-programs/{id}")
    public ResponseEntity<UserProgramDTO> getUserProgram(@PathVariable Long id) {
        log.debug("REST request to get UserProgram : {}", id);
        Optional<UserProgramDTO> userProgramDTO = userProgramService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userProgramDTO);
    }

    /**
     * {@code DELETE  /user-programs/:id} : delete the "id" userProgram.
     *
     * @param id the id of the userProgramDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-programs/{id}")
    public ResponseEntity<Void> deleteUserProgram(@PathVariable Long id) {
        log.debug("REST request to delete UserProgram : {}", id);
        userProgramService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
