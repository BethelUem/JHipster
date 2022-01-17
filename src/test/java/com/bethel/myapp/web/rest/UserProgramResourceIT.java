package com.bethel.myapp.web.rest;

import static com.bethel.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bethel.myapp.IntegrationTest;
import com.bethel.myapp.domain.UserProgram;
import com.bethel.myapp.repository.UserProgramRepository;
import com.bethel.myapp.service.dto.UserProgramDTO;
import com.bethel.myapp.service.mapper.UserProgramMapper;
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
 * Integration tests for the {@link UserProgramResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserProgramResourceIT {

    private static final Integer DEFAULT_USER = 1;
    private static final Integer UPDATED_USER = 2;

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

    private static final String ENTITY_API_URL = "/api/user-programs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserProgramRepository userProgramRepository;

    @Autowired
    private UserProgramMapper userProgramMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserProgramMockMvc;

    private UserProgram userProgram;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserProgram createEntity(EntityManager em) {
        UserProgram userProgram = new UserProgram()
            .user(DEFAULT_USER)
            .active(DEFAULT_ACTIVE)
            .createById(DEFAULT_CREATE_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedById(DEFAULT_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return userProgram;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserProgram createUpdatedEntity(EntityManager em) {
        UserProgram userProgram = new UserProgram()
            .user(UPDATED_USER)
            .active(UPDATED_ACTIVE)
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return userProgram;
    }

    @BeforeEach
    public void initTest() {
        userProgram = createEntity(em);
    }

    @Test
    @Transactional
    void createUserProgram() throws Exception {
        int databaseSizeBeforeCreate = userProgramRepository.findAll().size();
        // Create the UserProgram
        UserProgramDTO userProgramDTO = userProgramMapper.toDto(userProgram);
        restUserProgramMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userProgramDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeCreate + 1);
        UserProgram testUserProgram = userProgramList.get(userProgramList.size() - 1);
        assertThat(testUserProgram.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testUserProgram.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testUserProgram.getCreateById()).isEqualTo(DEFAULT_CREATE_BY_ID);
        assertThat(testUserProgram.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserProgram.getLastModifiedById()).isEqualTo(DEFAULT_LAST_MODIFIED_BY_ID);
        assertThat(testUserProgram.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createUserProgramWithExistingId() throws Exception {
        // Create the UserProgram with an existing ID
        userProgram.setId(1L);
        UserProgramDTO userProgramDTO = userProgramMapper.toDto(userProgram);

        int databaseSizeBeforeCreate = userProgramRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserProgramMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userProgramDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUserPrograms() throws Exception {
        // Initialize the database
        userProgramRepository.saveAndFlush(userProgram);

        // Get all the userProgramList
        restUserProgramMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userProgram.getId().intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createById").value(hasItem(DEFAULT_CREATE_BY_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(sameInstant(DEFAULT_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].lastModifiedById").value(hasItem(DEFAULT_LAST_MODIFIED_BY_ID)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getUserProgram() throws Exception {
        // Initialize the database
        userProgramRepository.saveAndFlush(userProgram);

        // Get the userProgram
        restUserProgramMockMvc
            .perform(get(ENTITY_API_URL_ID, userProgram.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userProgram.getId().intValue()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createById").value(DEFAULT_CREATE_BY_ID))
            .andExpect(jsonPath("$.createdDate").value(sameInstant(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.lastModifiedById").value(DEFAULT_LAST_MODIFIED_BY_ID))
            .andExpect(jsonPath("$.lastModifiedDate").value(sameInstant(DEFAULT_LAST_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingUserProgram() throws Exception {
        // Get the userProgram
        restUserProgramMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUserProgram() throws Exception {
        // Initialize the database
        userProgramRepository.saveAndFlush(userProgram);

        int databaseSizeBeforeUpdate = userProgramRepository.findAll().size();

        // Update the userProgram
        UserProgram updatedUserProgram = userProgramRepository.findById(userProgram.getId()).get();
        // Disconnect from session so that the updates on updatedUserProgram are not directly saved in db
        em.detach(updatedUserProgram);
        updatedUserProgram
            .user(UPDATED_USER)
            .active(UPDATED_ACTIVE)
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        UserProgramDTO userProgramDTO = userProgramMapper.toDto(updatedUserProgram);

        restUserProgramMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userProgramDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userProgramDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeUpdate);
        UserProgram testUserProgram = userProgramList.get(userProgramList.size() - 1);
        assertThat(testUserProgram.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testUserProgram.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testUserProgram.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testUserProgram.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserProgram.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testUserProgram.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingUserProgram() throws Exception {
        int databaseSizeBeforeUpdate = userProgramRepository.findAll().size();
        userProgram.setId(count.incrementAndGet());

        // Create the UserProgram
        UserProgramDTO userProgramDTO = userProgramMapper.toDto(userProgram);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserProgramMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userProgramDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userProgramDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserProgram() throws Exception {
        int databaseSizeBeforeUpdate = userProgramRepository.findAll().size();
        userProgram.setId(count.incrementAndGet());

        // Create the UserProgram
        UserProgramDTO userProgramDTO = userProgramMapper.toDto(userProgram);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserProgramMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userProgramDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserProgram() throws Exception {
        int databaseSizeBeforeUpdate = userProgramRepository.findAll().size();
        userProgram.setId(count.incrementAndGet());

        // Create the UserProgram
        UserProgramDTO userProgramDTO = userProgramMapper.toDto(userProgram);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserProgramMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userProgramDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserProgramWithPatch() throws Exception {
        // Initialize the database
        userProgramRepository.saveAndFlush(userProgram);

        int databaseSizeBeforeUpdate = userProgramRepository.findAll().size();

        // Update the userProgram using partial update
        UserProgram partialUpdatedUserProgram = new UserProgram();
        partialUpdatedUserProgram.setId(userProgram.getId());

        partialUpdatedUserProgram
            .user(UPDATED_USER)
            .active(UPDATED_ACTIVE)
            .createById(UPDATED_CREATE_BY_ID)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID);

        restUserProgramMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserProgram.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserProgram))
            )
            .andExpect(status().isOk());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeUpdate);
        UserProgram testUserProgram = userProgramList.get(userProgramList.size() - 1);
        assertThat(testUserProgram.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testUserProgram.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testUserProgram.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testUserProgram.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserProgram.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testUserProgram.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateUserProgramWithPatch() throws Exception {
        // Initialize the database
        userProgramRepository.saveAndFlush(userProgram);

        int databaseSizeBeforeUpdate = userProgramRepository.findAll().size();

        // Update the userProgram using partial update
        UserProgram partialUpdatedUserProgram = new UserProgram();
        partialUpdatedUserProgram.setId(userProgram.getId());

        partialUpdatedUserProgram
            .user(UPDATED_USER)
            .active(UPDATED_ACTIVE)
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restUserProgramMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserProgram.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserProgram))
            )
            .andExpect(status().isOk());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeUpdate);
        UserProgram testUserProgram = userProgramList.get(userProgramList.size() - 1);
        assertThat(testUserProgram.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testUserProgram.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testUserProgram.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testUserProgram.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserProgram.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testUserProgram.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingUserProgram() throws Exception {
        int databaseSizeBeforeUpdate = userProgramRepository.findAll().size();
        userProgram.setId(count.incrementAndGet());

        // Create the UserProgram
        UserProgramDTO userProgramDTO = userProgramMapper.toDto(userProgram);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserProgramMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userProgramDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userProgramDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserProgram() throws Exception {
        int databaseSizeBeforeUpdate = userProgramRepository.findAll().size();
        userProgram.setId(count.incrementAndGet());

        // Create the UserProgram
        UserProgramDTO userProgramDTO = userProgramMapper.toDto(userProgram);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserProgramMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userProgramDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserProgram() throws Exception {
        int databaseSizeBeforeUpdate = userProgramRepository.findAll().size();
        userProgram.setId(count.incrementAndGet());

        // Create the UserProgram
        UserProgramDTO userProgramDTO = userProgramMapper.toDto(userProgram);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserProgramMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(userProgramDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserProgram in the database
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserProgram() throws Exception {
        // Initialize the database
        userProgramRepository.saveAndFlush(userProgram);

        int databaseSizeBeforeDelete = userProgramRepository.findAll().size();

        // Delete the userProgram
        restUserProgramMockMvc
            .perform(delete(ENTITY_API_URL_ID, userProgram.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserProgram> userProgramList = userProgramRepository.findAll();
        assertThat(userProgramList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
