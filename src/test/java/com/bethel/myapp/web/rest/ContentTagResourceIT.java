package com.bethel.myapp.web.rest;

import static com.bethel.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bethel.myapp.IntegrationTest;
import com.bethel.myapp.domain.Catalog;
import com.bethel.myapp.domain.Content;
import com.bethel.myapp.domain.ContentTag;
import com.bethel.myapp.repository.ContentTagRepository;
import com.bethel.myapp.service.dto.ContentTagDTO;
import com.bethel.myapp.service.mapper.ContentTagMapper;
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
 * Integration tests for the {@link ContentTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContentTagResourceIT {

    private static final Integer DEFAULT_CREATE_BY_ID = 1;
    private static final Integer UPDATED_CREATE_BY_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_LAST_MODIFIED_BY_ID = 1;
    private static final Integer UPDATED_LAST_MODIFIED_BY_ID = 2;

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/content-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContentTagRepository contentTagRepository;

    @Autowired
    private ContentTagMapper contentTagMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContentTagMockMvc;

    private ContentTag contentTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContentTag createEntity(EntityManager em) {
        ContentTag contentTag = new ContentTag()
            .createById(DEFAULT_CREATE_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedById(DEFAULT_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        // Add required entity
        Content content;
        if (TestUtil.findAll(em, Content.class).isEmpty()) {
            content = ContentResourceIT.createEntity(em);
            em.persist(content);
            em.flush();
        } else {
            content = TestUtil.findAll(em, Content.class).get(0);
        }
        contentTag.setContentId(content);
        // Add required entity
        Catalog catalog;
        if (TestUtil.findAll(em, Catalog.class).isEmpty()) {
            catalog = CatalogResourceIT.createEntity(em);
            em.persist(catalog);
            em.flush();
        } else {
            catalog = TestUtil.findAll(em, Catalog.class).get(0);
        }
        contentTag.setCatalogId(catalog);
        return contentTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContentTag createUpdatedEntity(EntityManager em) {
        ContentTag contentTag = new ContentTag()
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        // Add required entity
        Content content;
        if (TestUtil.findAll(em, Content.class).isEmpty()) {
            content = ContentResourceIT.createUpdatedEntity(em);
            em.persist(content);
            em.flush();
        } else {
            content = TestUtil.findAll(em, Content.class).get(0);
        }
        contentTag.setContentId(content);
        // Add required entity
        Catalog catalog;
        if (TestUtil.findAll(em, Catalog.class).isEmpty()) {
            catalog = CatalogResourceIT.createUpdatedEntity(em);
            em.persist(catalog);
            em.flush();
        } else {
            catalog = TestUtil.findAll(em, Catalog.class).get(0);
        }
        contentTag.setCatalogId(catalog);
        return contentTag;
    }

    @BeforeEach
    public void initTest() {
        contentTag = createEntity(em);
    }

    @Test
    @Transactional
    void createContentTag() throws Exception {
        int databaseSizeBeforeCreate = contentTagRepository.findAll().size();
        // Create the ContentTag
        ContentTagDTO contentTagDTO = contentTagMapper.toDto(contentTag);
        restContentTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contentTagDTO)))
            .andExpect(status().isCreated());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeCreate + 1);
        ContentTag testContentTag = contentTagList.get(contentTagList.size() - 1);
        assertThat(testContentTag.getCreateById()).isEqualTo(DEFAULT_CREATE_BY_ID);
        assertThat(testContentTag.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testContentTag.getLastModifiedById()).isEqualTo(DEFAULT_LAST_MODIFIED_BY_ID);
        assertThat(testContentTag.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createContentTagWithExistingId() throws Exception {
        // Create the ContentTag with an existing ID
        contentTag.setId(1L);
        ContentTagDTO contentTagDTO = contentTagMapper.toDto(contentTag);

        int databaseSizeBeforeCreate = contentTagRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContentTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contentTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContentTags() throws Exception {
        // Initialize the database
        contentTagRepository.saveAndFlush(contentTag);

        // Get all the contentTagList
        restContentTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contentTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].createById").value(hasItem(DEFAULT_CREATE_BY_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(sameInstant(DEFAULT_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].lastModifiedById").value(hasItem(DEFAULT_LAST_MODIFIED_BY_ID)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getContentTag() throws Exception {
        // Initialize the database
        contentTagRepository.saveAndFlush(contentTag);

        // Get the contentTag
        restContentTagMockMvc
            .perform(get(ENTITY_API_URL_ID, contentTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contentTag.getId().intValue()))
            .andExpect(jsonPath("$.createById").value(DEFAULT_CREATE_BY_ID))
            .andExpect(jsonPath("$.createdDate").value(sameInstant(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.lastModifiedById").value(DEFAULT_LAST_MODIFIED_BY_ID))
            .andExpect(jsonPath("$.lastModifiedDate").value(sameInstant(DEFAULT_LAST_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingContentTag() throws Exception {
        // Get the contentTag
        restContentTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContentTag() throws Exception {
        // Initialize the database
        contentTagRepository.saveAndFlush(contentTag);

        int databaseSizeBeforeUpdate = contentTagRepository.findAll().size();

        // Update the contentTag
        ContentTag updatedContentTag = contentTagRepository.findById(contentTag.getId()).get();
        // Disconnect from session so that the updates on updatedContentTag are not directly saved in db
        em.detach(updatedContentTag);
        updatedContentTag
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        ContentTagDTO contentTagDTO = contentTagMapper.toDto(updatedContentTag);

        restContentTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contentTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentTagDTO))
            )
            .andExpect(status().isOk());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeUpdate);
        ContentTag testContentTag = contentTagList.get(contentTagList.size() - 1);
        assertThat(testContentTag.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testContentTag.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testContentTag.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testContentTag.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingContentTag() throws Exception {
        int databaseSizeBeforeUpdate = contentTagRepository.findAll().size();
        contentTag.setId(count.incrementAndGet());

        // Create the ContentTag
        ContentTagDTO contentTagDTO = contentTagMapper.toDto(contentTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContentTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contentTagDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContentTag() throws Exception {
        int databaseSizeBeforeUpdate = contentTagRepository.findAll().size();
        contentTag.setId(count.incrementAndGet());

        // Create the ContentTag
        ContentTagDTO contentTagDTO = contentTagMapper.toDto(contentTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContentTag() throws Exception {
        int databaseSizeBeforeUpdate = contentTagRepository.findAll().size();
        contentTag.setId(count.incrementAndGet());

        // Create the ContentTag
        ContentTagDTO contentTagDTO = contentTagMapper.toDto(contentTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentTagMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contentTagDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContentTagWithPatch() throws Exception {
        // Initialize the database
        contentTagRepository.saveAndFlush(contentTag);

        int databaseSizeBeforeUpdate = contentTagRepository.findAll().size();

        // Update the contentTag using partial update
        ContentTag partialUpdatedContentTag = new ContentTag();
        partialUpdatedContentTag.setId(contentTag.getId());

        partialUpdatedContentTag.createById(UPDATED_CREATE_BY_ID).lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID);

        restContentTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContentTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContentTag))
            )
            .andExpect(status().isOk());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeUpdate);
        ContentTag testContentTag = contentTagList.get(contentTagList.size() - 1);
        assertThat(testContentTag.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testContentTag.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testContentTag.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testContentTag.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateContentTagWithPatch() throws Exception {
        // Initialize the database
        contentTagRepository.saveAndFlush(contentTag);

        int databaseSizeBeforeUpdate = contentTagRepository.findAll().size();

        // Update the contentTag using partial update
        ContentTag partialUpdatedContentTag = new ContentTag();
        partialUpdatedContentTag.setId(contentTag.getId());

        partialUpdatedContentTag
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restContentTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContentTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContentTag))
            )
            .andExpect(status().isOk());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeUpdate);
        ContentTag testContentTag = contentTagList.get(contentTagList.size() - 1);
        assertThat(testContentTag.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testContentTag.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testContentTag.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testContentTag.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingContentTag() throws Exception {
        int databaseSizeBeforeUpdate = contentTagRepository.findAll().size();
        contentTag.setId(count.incrementAndGet());

        // Create the ContentTag
        ContentTagDTO contentTagDTO = contentTagMapper.toDto(contentTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContentTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contentTagDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contentTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContentTag() throws Exception {
        int databaseSizeBeforeUpdate = contentTagRepository.findAll().size();
        contentTag.setId(count.incrementAndGet());

        // Create the ContentTag
        ContentTagDTO contentTagDTO = contentTagMapper.toDto(contentTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contentTagDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContentTag() throws Exception {
        int databaseSizeBeforeUpdate = contentTagRepository.findAll().size();
        contentTag.setId(count.incrementAndGet());

        // Create the ContentTag
        ContentTagDTO contentTagDTO = contentTagMapper.toDto(contentTag);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentTagMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(contentTagDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContentTag in the database
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContentTag() throws Exception {
        // Initialize the database
        contentTagRepository.saveAndFlush(contentTag);

        int databaseSizeBeforeDelete = contentTagRepository.findAll().size();

        // Delete the contentTag
        restContentTagMockMvc
            .perform(delete(ENTITY_API_URL_ID, contentTag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContentTag> contentTagList = contentTagRepository.findAll();
        assertThat(contentTagList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
