package com.bethel.myapp.web.rest;

import static com.bethel.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bethel.myapp.IntegrationTest;
import com.bethel.myapp.domain.Catalog;
import com.bethel.myapp.repository.CatalogRepository;
import com.bethel.myapp.service.dto.CatalogDTO;
import com.bethel.myapp.service.mapper.CatalogMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CatalogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CatalogResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final Integer DEFAULT_CREATE_BY_ID = 1;
    private static final Integer UPDATED_CREATE_BY_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_LAST_MODIFIED_BY_ID = 1;
    private static final Integer UPDATED_LAST_MODIFIED_BY_ID = 2;

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/catalogs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCatalogMockMvc;

    private Catalog catalog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Catalog createEntity(EntityManager em) {
        Catalog catalog = new Catalog()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .active(DEFAULT_ACTIVE)
            .createById(DEFAULT_CREATE_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedById(DEFAULT_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return catalog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Catalog createUpdatedEntity(EntityManager em) {
        Catalog catalog = new Catalog()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .active(UPDATED_ACTIVE)
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return catalog;
    }

    @BeforeEach
    public void initTest() {
        catalog = createEntity(em);
    }

    @Test
    @Transactional
    void createCatalog() throws Exception {
        int databaseSizeBeforeCreate = catalogRepository.findAll().size();
        // Create the Catalog
        CatalogDTO catalogDTO = catalogMapper.toDto(catalog);
        restCatalogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogDTO)))
            .andExpect(status().isCreated());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeCreate + 1);
        Catalog testCatalog = catalogList.get(catalogList.size() - 1);
        assertThat(testCatalog.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCatalog.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCatalog.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCatalog.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testCatalog.getCreateById()).isEqualTo(DEFAULT_CREATE_BY_ID);
        assertThat(testCatalog.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCatalog.getLastModifiedById()).isEqualTo(DEFAULT_LAST_MODIFIED_BY_ID);
        assertThat(testCatalog.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createCatalogWithExistingId() throws Exception {
        // Create the Catalog with an existing ID
        catalog.setId(1L);
        CatalogDTO catalogDTO = catalogMapper.toDto(catalog);

        int databaseSizeBeforeCreate = catalogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatalogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCatalogs() throws Exception {
        // Initialize the database
        catalogRepository.saveAndFlush(catalog);

        // Get all the catalogList
        restCatalogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catalog.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createById").value(hasItem(DEFAULT_CREATE_BY_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(sameInstant(DEFAULT_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].lastModifiedById").value(hasItem(DEFAULT_LAST_MODIFIED_BY_ID)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getCatalog() throws Exception {
        // Initialize the database
        catalogRepository.saveAndFlush(catalog);

        // Get the catalog
        restCatalogMockMvc
            .perform(get(ENTITY_API_URL_ID, catalog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(catalog.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createById").value(DEFAULT_CREATE_BY_ID))
            .andExpect(jsonPath("$.createdDate").value(sameInstant(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.lastModifiedById").value(DEFAULT_LAST_MODIFIED_BY_ID))
            .andExpect(jsonPath("$.lastModifiedDate").value(sameInstant(DEFAULT_LAST_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingCatalog() throws Exception {
        // Get the catalog
        restCatalogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCatalog() throws Exception {
        // Initialize the database
        catalogRepository.saveAndFlush(catalog);

        int databaseSizeBeforeUpdate = catalogRepository.findAll().size();

        // Update the catalog
        Catalog updatedCatalog = catalogRepository.findById(catalog.getId()).get();
        // Disconnect from session so that the updates on updatedCatalog are not directly saved in db
        em.detach(updatedCatalog);
        updatedCatalog
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .active(UPDATED_ACTIVE)
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        CatalogDTO catalogDTO = catalogMapper.toDto(updatedCatalog);

        restCatalogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, catalogDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(catalogDTO))
            )
            .andExpect(status().isOk());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeUpdate);
        Catalog testCatalog = catalogList.get(catalogList.size() - 1);
        assertThat(testCatalog.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCatalog.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCatalog.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCatalog.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testCatalog.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testCatalog.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCatalog.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testCatalog.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingCatalog() throws Exception {
        int databaseSizeBeforeUpdate = catalogRepository.findAll().size();
        catalog.setId(count.incrementAndGet());

        // Create the Catalog
        CatalogDTO catalogDTO = catalogMapper.toDto(catalog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatalogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, catalogDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(catalogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCatalog() throws Exception {
        int databaseSizeBeforeUpdate = catalogRepository.findAll().size();
        catalog.setId(count.incrementAndGet());

        // Create the Catalog
        CatalogDTO catalogDTO = catalogMapper.toDto(catalog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(catalogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCatalog() throws Exception {
        int databaseSizeBeforeUpdate = catalogRepository.findAll().size();
        catalog.setId(count.incrementAndGet());

        // Create the Catalog
        CatalogDTO catalogDTO = catalogMapper.toDto(catalog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCatalogWithPatch() throws Exception {
        // Initialize the database
        catalogRepository.saveAndFlush(catalog);

        int databaseSizeBeforeUpdate = catalogRepository.findAll().size();

        // Update the catalog using partial update
        Catalog partialUpdatedCatalog = new Catalog();
        partialUpdatedCatalog.setId(catalog.getId());

        partialUpdatedCatalog
            .description(UPDATED_DESCRIPTION)
            .active(UPDATED_ACTIVE)
            .createById(UPDATED_CREATE_BY_ID)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID);

        restCatalogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCatalog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCatalog))
            )
            .andExpect(status().isOk());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeUpdate);
        Catalog testCatalog = catalogList.get(catalogList.size() - 1);
        assertThat(testCatalog.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCatalog.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCatalog.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCatalog.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testCatalog.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testCatalog.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCatalog.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testCatalog.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateCatalogWithPatch() throws Exception {
        // Initialize the database
        catalogRepository.saveAndFlush(catalog);

        int databaseSizeBeforeUpdate = catalogRepository.findAll().size();

        // Update the catalog using partial update
        Catalog partialUpdatedCatalog = new Catalog();
        partialUpdatedCatalog.setId(catalog.getId());

        partialUpdatedCatalog
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .active(UPDATED_ACTIVE)
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restCatalogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCatalog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCatalog))
            )
            .andExpect(status().isOk());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeUpdate);
        Catalog testCatalog = catalogList.get(catalogList.size() - 1);
        assertThat(testCatalog.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCatalog.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCatalog.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCatalog.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testCatalog.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testCatalog.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCatalog.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testCatalog.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingCatalog() throws Exception {
        int databaseSizeBeforeUpdate = catalogRepository.findAll().size();
        catalog.setId(count.incrementAndGet());

        // Create the Catalog
        CatalogDTO catalogDTO = catalogMapper.toDto(catalog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatalogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, catalogDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(catalogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCatalog() throws Exception {
        int databaseSizeBeforeUpdate = catalogRepository.findAll().size();
        catalog.setId(count.incrementAndGet());

        // Create the Catalog
        CatalogDTO catalogDTO = catalogMapper.toDto(catalog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(catalogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCatalog() throws Exception {
        int databaseSizeBeforeUpdate = catalogRepository.findAll().size();
        catalog.setId(count.incrementAndGet());

        // Create the Catalog
        CatalogDTO catalogDTO = catalogMapper.toDto(catalog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(catalogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Catalog in the database
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCatalog() throws Exception {
        // Initialize the database
        catalogRepository.saveAndFlush(catalog);

        int databaseSizeBeforeDelete = catalogRepository.findAll().size();

        // Delete the catalog
        restCatalogMockMvc
            .perform(delete(ENTITY_API_URL_ID, catalog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Catalog> catalogList = catalogRepository.findAll();
        assertThat(catalogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
