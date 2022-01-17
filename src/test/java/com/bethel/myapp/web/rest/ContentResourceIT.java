package com.bethel.myapp.web.rest;

import static com.bethel.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bethel.myapp.IntegrationTest;
import com.bethel.myapp.domain.Content;
import com.bethel.myapp.repository.ContentRepository;
import com.bethel.myapp.service.dto.ContentDTO;
import com.bethel.myapp.service.mapper.ContentMapper;
import java.time.Duration;
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
 * Integration tests for the {@link ContentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContentResourceIT {

    private static final ZonedDateTime DEFAULT_RECORDING_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_RECORDING_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_EDITION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EDITION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_SIGNATURE_ORIGINAL = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_ORIGINAL = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATURE_EDITED = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_EDITED = "BBBBBBBBBB";

    private static final String DEFAULT_EVENT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_EVENT_SLOGAN = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_SLOGAN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_CONDUCTION = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CONDUCTION = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION_CONDUCTION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION_CONDUCTION = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIALTIES_PANEL = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALTIES_PANEL = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_PANEL = "AAAAAAAAAA";
    private static final String UPDATED_NAME_PANEL = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_TITLE_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_BIBLICAL_PASSAGE = "AAAAAAAAAA";
    private static final String UPDATED_BIBLICAL_PASSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_SYNOPSIS_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SYNOPSIS_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STORYLINE = "AAAAAAAAAA";
    private static final String UPDATED_STORYLINE = "BBBBBBBBBB";

    private static final String DEFAULT_MEDIA_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_MEDIA_CONTENT = "BBBBBBBBBB";

    private static final Duration DEFAULT_DURATION_ORIGINAL = Duration.ofHours(6);
    private static final Duration UPDATED_DURATION_ORIGINAL = Duration.ofHours(12);

    private static final Duration DEFAULT_DURATION_EDITED = Duration.ofHours(6);
    private static final Duration UPDATED_DURATION_EDITED = Duration.ofHours(12);

    private static final String DEFAULT_SCENOGRAPHY = "AAAAAAAAAA";
    private static final String UPDATED_SCENOGRAPHY = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCER = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCER = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTION_ASSISTANT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTION_ASSISTANT = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATOR_VTR_PLAY_OUT = "AAAAAAAAAA";
    private static final String UPDATED_OPERATOR_VTR_PLAY_OUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTION_CREDITS = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTION_CREDITS = "BBBBBBBBBB";

    private static final String DEFAULT_CAST_ACTORS = "AAAAAAAAAA";
    private static final String UPDATED_CAST_ACTORS = "BBBBBBBBBB";

    private static final String DEFAULT_ARCHIVE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ARCHIVE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_INTERPRETER_TRANSLATOR = "AAAAAAAAAA";
    private static final String UPDATED_INTERPRETER_TRANSLATOR = "BBBBBBBBBB";

    private static final String DEFAULT_DUBBING = "AAAAAAAAAA";
    private static final String UPDATED_DUBBING = "BBBBBBBBBB";

    private static final String DEFAULT_SUBTITLE_CC = "AAAAAAAAAA";
    private static final String UPDATED_SUBTITLE_CC = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOGUER_ORIGINAL = "AAAAAAAAAA";
    private static final String UPDATED_CATALOGUER_ORIGINAL = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOGUER_EDITED = "AAAAAAAAAA";
    private static final String UPDATED_CATALOGUER_EDITED = "BBBBBBBBBB";

    private static final String DEFAULT_POSTPRODUCTION_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_POSTPRODUCTION_EDITOR = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATOR_INGESTION = "AAAAAAAAAA";
    private static final String UPDATED_OPERATOR_INGESTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SERVICE_OTT = false;
    private static final Boolean UPDATED_SERVICE_OTT = true;

    private static final Boolean DEFAULT_ARCHIVADO_LTO_ORIGINAL = false;
    private static final Boolean UPDATED_ARCHIVADO_LTO_ORIGINAL = true;

    private static final Boolean DEFAULT_ARCHIVADO_LTO_EDITED = false;
    private static final Boolean UPDATED_ARCHIVADO_LTO_EDITED = true;

    private static final String DEFAULT_OBSERVATIONS = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATIONS = "BBBBBBBBBB";

    private static final String DEFAULT_RIGHTS_MANAGEMENT = "AAAAAAAAAA";
    private static final String UPDATED_RIGHTS_MANAGEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOGUER_PRODUCTION = "AAAAAAAAAA";
    private static final String UPDATED_CATALOGUER_PRODUCTION = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOGUER_INGEST = "AAAAAAAAAA";
    private static final String UPDATED_CATALOGUER_INGEST = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOGUER_MASTER = "AAAAAAAAAA";
    private static final String UPDATED_CATALOGUER_MASTER = "BBBBBBBBBB";

    private static final String DEFAULT_URL_EDITION = "AAAAAAAAAA";
    private static final String UPDATED_URL_EDITION = "BBBBBBBBBB";

    private static final String DEFAULT_URL_ORIGINAL = "AAAAAAAAAA";
    private static final String UPDATED_URL_ORIGINAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_CREATE_BY_ID = 1;
    private static final Integer UPDATED_CREATE_BY_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_LAST_MODIFIED_BY_ID = 1;
    private static final Integer UPDATED_LAST_MODIFIED_BY_ID = 2;

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/contents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContentMockMvc;

    private Content content;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Content createEntity(EntityManager em) {
        Content content = new Content()
            .recordingDate(DEFAULT_RECORDING_DATE)
            .editionDate(DEFAULT_EDITION_DATE)
            .signatureOriginal(DEFAULT_SIGNATURE_ORIGINAL)
            .signatureEdited(DEFAULT_SIGNATURE_EDITED)
            .eventReason(DEFAULT_EVENT_REASON)
            .eventSlogan(DEFAULT_EVENT_SLOGAN)
            .nameConduction(DEFAULT_NAME_CONDUCTION)
            .positionConduction(DEFAULT_POSITION_CONDUCTION)
            .specialtiesPanel(DEFAULT_SPECIALTIES_PANEL)
            .namePanel(DEFAULT_NAME_PANEL)
            .titleSubject(DEFAULT_TITLE_SUBJECT)
            .biblicalPassage(DEFAULT_BIBLICAL_PASSAGE)
            .synopsisDescription(DEFAULT_SYNOPSIS_DESCRIPTION)
            .storyline(DEFAULT_STORYLINE)
            .mediaContent(DEFAULT_MEDIA_CONTENT)
            .durationOriginal(DEFAULT_DURATION_ORIGINAL)
            .durationEdited(DEFAULT_DURATION_EDITED)
            .scenography(DEFAULT_SCENOGRAPHY)
            .location(DEFAULT_LOCATION)
            .city(DEFAULT_CITY)
            .departmentState(DEFAULT_DEPARTMENT_STATE)
            .producer(DEFAULT_PRODUCER)
            .productionAssistant(DEFAULT_PRODUCTION_ASSISTANT)
            .operatorVTRPlayOut(DEFAULT_OPERATOR_VTR_PLAY_OUT)
            .productionCredits(DEFAULT_PRODUCTION_CREDITS)
            .castActors(DEFAULT_CAST_ACTORS)
            .archiveStatus(DEFAULT_ARCHIVE_STATUS)
            .interpreterTranslator(DEFAULT_INTERPRETER_TRANSLATOR)
            .dubbing(DEFAULT_DUBBING)
            .subtitleCC(DEFAULT_SUBTITLE_CC)
            .cataloguerOriginal(DEFAULT_CATALOGUER_ORIGINAL)
            .cataloguerEdited(DEFAULT_CATALOGUER_EDITED)
            .postproductionEditor(DEFAULT_POSTPRODUCTION_EDITOR)
            .operatorIngestion(DEFAULT_OPERATOR_INGESTION)
            .serviceOTT(DEFAULT_SERVICE_OTT)
            .archivadoLTOOriginal(DEFAULT_ARCHIVADO_LTO_ORIGINAL)
            .archivadoLTOEdited(DEFAULT_ARCHIVADO_LTO_EDITED)
            .observations(DEFAULT_OBSERVATIONS)
            .rightsManagement(DEFAULT_RIGHTS_MANAGEMENT)
            .cataloguerProduction(DEFAULT_CATALOGUER_PRODUCTION)
            .cataloguerIngest(DEFAULT_CATALOGUER_INGEST)
            .cataloguerMaster(DEFAULT_CATALOGUER_MASTER)
            .urlEdition(DEFAULT_URL_EDITION)
            .urlOriginal(DEFAULT_URL_ORIGINAL)
            .createById(DEFAULT_CREATE_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedById(DEFAULT_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return content;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Content createUpdatedEntity(EntityManager em) {
        Content content = new Content()
            .recordingDate(UPDATED_RECORDING_DATE)
            .editionDate(UPDATED_EDITION_DATE)
            .signatureOriginal(UPDATED_SIGNATURE_ORIGINAL)
            .signatureEdited(UPDATED_SIGNATURE_EDITED)
            .eventReason(UPDATED_EVENT_REASON)
            .eventSlogan(UPDATED_EVENT_SLOGAN)
            .nameConduction(UPDATED_NAME_CONDUCTION)
            .positionConduction(UPDATED_POSITION_CONDUCTION)
            .specialtiesPanel(UPDATED_SPECIALTIES_PANEL)
            .namePanel(UPDATED_NAME_PANEL)
            .titleSubject(UPDATED_TITLE_SUBJECT)
            .biblicalPassage(UPDATED_BIBLICAL_PASSAGE)
            .synopsisDescription(UPDATED_SYNOPSIS_DESCRIPTION)
            .storyline(UPDATED_STORYLINE)
            .mediaContent(UPDATED_MEDIA_CONTENT)
            .durationOriginal(UPDATED_DURATION_ORIGINAL)
            .durationEdited(UPDATED_DURATION_EDITED)
            .scenography(UPDATED_SCENOGRAPHY)
            .location(UPDATED_LOCATION)
            .city(UPDATED_CITY)
            .departmentState(UPDATED_DEPARTMENT_STATE)
            .producer(UPDATED_PRODUCER)
            .productionAssistant(UPDATED_PRODUCTION_ASSISTANT)
            .operatorVTRPlayOut(UPDATED_OPERATOR_VTR_PLAY_OUT)
            .productionCredits(UPDATED_PRODUCTION_CREDITS)
            .castActors(UPDATED_CAST_ACTORS)
            .archiveStatus(UPDATED_ARCHIVE_STATUS)
            .interpreterTranslator(UPDATED_INTERPRETER_TRANSLATOR)
            .dubbing(UPDATED_DUBBING)
            .subtitleCC(UPDATED_SUBTITLE_CC)
            .cataloguerOriginal(UPDATED_CATALOGUER_ORIGINAL)
            .cataloguerEdited(UPDATED_CATALOGUER_EDITED)
            .postproductionEditor(UPDATED_POSTPRODUCTION_EDITOR)
            .operatorIngestion(UPDATED_OPERATOR_INGESTION)
            .serviceOTT(UPDATED_SERVICE_OTT)
            .archivadoLTOOriginal(UPDATED_ARCHIVADO_LTO_ORIGINAL)
            .archivadoLTOEdited(UPDATED_ARCHIVADO_LTO_EDITED)
            .observations(UPDATED_OBSERVATIONS)
            .rightsManagement(UPDATED_RIGHTS_MANAGEMENT)
            .cataloguerProduction(UPDATED_CATALOGUER_PRODUCTION)
            .cataloguerIngest(UPDATED_CATALOGUER_INGEST)
            .cataloguerMaster(UPDATED_CATALOGUER_MASTER)
            .urlEdition(UPDATED_URL_EDITION)
            .urlOriginal(UPDATED_URL_ORIGINAL)
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return content;
    }

    @BeforeEach
    public void initTest() {
        content = createEntity(em);
    }

    @Test
    @Transactional
    void createContent() throws Exception {
        int databaseSizeBeforeCreate = contentRepository.findAll().size();
        // Create the Content
        ContentDTO contentDTO = contentMapper.toDto(content);
        restContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contentDTO)))
            .andExpect(status().isCreated());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeCreate + 1);
        Content testContent = contentList.get(contentList.size() - 1);
        assertThat(testContent.getRecordingDate()).isEqualTo(DEFAULT_RECORDING_DATE);
        assertThat(testContent.getEditionDate()).isEqualTo(DEFAULT_EDITION_DATE);
        assertThat(testContent.getSignatureOriginal()).isEqualTo(DEFAULT_SIGNATURE_ORIGINAL);
        assertThat(testContent.getSignatureEdited()).isEqualTo(DEFAULT_SIGNATURE_EDITED);
        assertThat(testContent.getEventReason()).isEqualTo(DEFAULT_EVENT_REASON);
        assertThat(testContent.getEventSlogan()).isEqualTo(DEFAULT_EVENT_SLOGAN);
        assertThat(testContent.getNameConduction()).isEqualTo(DEFAULT_NAME_CONDUCTION);
        assertThat(testContent.getPositionConduction()).isEqualTo(DEFAULT_POSITION_CONDUCTION);
        assertThat(testContent.getSpecialtiesPanel()).isEqualTo(DEFAULT_SPECIALTIES_PANEL);
        assertThat(testContent.getNamePanel()).isEqualTo(DEFAULT_NAME_PANEL);
        assertThat(testContent.getTitleSubject()).isEqualTo(DEFAULT_TITLE_SUBJECT);
        assertThat(testContent.getBiblicalPassage()).isEqualTo(DEFAULT_BIBLICAL_PASSAGE);
        assertThat(testContent.getSynopsisDescription()).isEqualTo(DEFAULT_SYNOPSIS_DESCRIPTION);
        assertThat(testContent.getStoryline()).isEqualTo(DEFAULT_STORYLINE);
        assertThat(testContent.getMediaContent()).isEqualTo(DEFAULT_MEDIA_CONTENT);
        assertThat(testContent.getDurationOriginal()).isEqualTo(DEFAULT_DURATION_ORIGINAL);
        assertThat(testContent.getDurationEdited()).isEqualTo(DEFAULT_DURATION_EDITED);
        assertThat(testContent.getScenography()).isEqualTo(DEFAULT_SCENOGRAPHY);
        assertThat(testContent.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testContent.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testContent.getDepartmentState()).isEqualTo(DEFAULT_DEPARTMENT_STATE);
        assertThat(testContent.getProducer()).isEqualTo(DEFAULT_PRODUCER);
        assertThat(testContent.getProductionAssistant()).isEqualTo(DEFAULT_PRODUCTION_ASSISTANT);
        assertThat(testContent.getOperatorVTRPlayOut()).isEqualTo(DEFAULT_OPERATOR_VTR_PLAY_OUT);
        assertThat(testContent.getProductionCredits()).isEqualTo(DEFAULT_PRODUCTION_CREDITS);
        assertThat(testContent.getCastActors()).isEqualTo(DEFAULT_CAST_ACTORS);
        assertThat(testContent.getArchiveStatus()).isEqualTo(DEFAULT_ARCHIVE_STATUS);
        assertThat(testContent.getInterpreterTranslator()).isEqualTo(DEFAULT_INTERPRETER_TRANSLATOR);
        assertThat(testContent.getDubbing()).isEqualTo(DEFAULT_DUBBING);
        assertThat(testContent.getSubtitleCC()).isEqualTo(DEFAULT_SUBTITLE_CC);
        assertThat(testContent.getCataloguerOriginal()).isEqualTo(DEFAULT_CATALOGUER_ORIGINAL);
        assertThat(testContent.getCataloguerEdited()).isEqualTo(DEFAULT_CATALOGUER_EDITED);
        assertThat(testContent.getPostproductionEditor()).isEqualTo(DEFAULT_POSTPRODUCTION_EDITOR);
        assertThat(testContent.getOperatorIngestion()).isEqualTo(DEFAULT_OPERATOR_INGESTION);
        assertThat(testContent.getServiceOTT()).isEqualTo(DEFAULT_SERVICE_OTT);
        assertThat(testContent.getArchivadoLTOOriginal()).isEqualTo(DEFAULT_ARCHIVADO_LTO_ORIGINAL);
        assertThat(testContent.getArchivadoLTOEdited()).isEqualTo(DEFAULT_ARCHIVADO_LTO_EDITED);
        assertThat(testContent.getObservations()).isEqualTo(DEFAULT_OBSERVATIONS);
        assertThat(testContent.getRightsManagement()).isEqualTo(DEFAULT_RIGHTS_MANAGEMENT);
        assertThat(testContent.getCataloguerProduction()).isEqualTo(DEFAULT_CATALOGUER_PRODUCTION);
        assertThat(testContent.getCataloguerIngest()).isEqualTo(DEFAULT_CATALOGUER_INGEST);
        assertThat(testContent.getCataloguerMaster()).isEqualTo(DEFAULT_CATALOGUER_MASTER);
        assertThat(testContent.getUrlEdition()).isEqualTo(DEFAULT_URL_EDITION);
        assertThat(testContent.getUrlOriginal()).isEqualTo(DEFAULT_URL_ORIGINAL);
        assertThat(testContent.getCreateById()).isEqualTo(DEFAULT_CREATE_BY_ID);
        assertThat(testContent.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testContent.getLastModifiedById()).isEqualTo(DEFAULT_LAST_MODIFIED_BY_ID);
        assertThat(testContent.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createContentWithExistingId() throws Exception {
        // Create the Content with an existing ID
        content.setId(1L);
        ContentDTO contentDTO = contentMapper.toDto(content);

        int databaseSizeBeforeCreate = contentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContents() throws Exception {
        // Initialize the database
        contentRepository.saveAndFlush(content);

        // Get all the contentList
        restContentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(content.getId().intValue())))
            .andExpect(jsonPath("$.[*].recordingDate").value(hasItem(sameInstant(DEFAULT_RECORDING_DATE))))
            .andExpect(jsonPath("$.[*].editionDate").value(hasItem(sameInstant(DEFAULT_EDITION_DATE))))
            .andExpect(jsonPath("$.[*].signatureOriginal").value(hasItem(DEFAULT_SIGNATURE_ORIGINAL)))
            .andExpect(jsonPath("$.[*].signatureEdited").value(hasItem(DEFAULT_SIGNATURE_EDITED)))
            .andExpect(jsonPath("$.[*].eventReason").value(hasItem(DEFAULT_EVENT_REASON)))
            .andExpect(jsonPath("$.[*].eventSlogan").value(hasItem(DEFAULT_EVENT_SLOGAN)))
            .andExpect(jsonPath("$.[*].nameConduction").value(hasItem(DEFAULT_NAME_CONDUCTION)))
            .andExpect(jsonPath("$.[*].positionConduction").value(hasItem(DEFAULT_POSITION_CONDUCTION)))
            .andExpect(jsonPath("$.[*].specialtiesPanel").value(hasItem(DEFAULT_SPECIALTIES_PANEL)))
            .andExpect(jsonPath("$.[*].namePanel").value(hasItem(DEFAULT_NAME_PANEL)))
            .andExpect(jsonPath("$.[*].titleSubject").value(hasItem(DEFAULT_TITLE_SUBJECT)))
            .andExpect(jsonPath("$.[*].biblicalPassage").value(hasItem(DEFAULT_BIBLICAL_PASSAGE)))
            .andExpect(jsonPath("$.[*].synopsisDescription").value(hasItem(DEFAULT_SYNOPSIS_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].storyline").value(hasItem(DEFAULT_STORYLINE)))
            .andExpect(jsonPath("$.[*].mediaContent").value(hasItem(DEFAULT_MEDIA_CONTENT)))
            .andExpect(jsonPath("$.[*].durationOriginal").value(hasItem(DEFAULT_DURATION_ORIGINAL.toString())))
            .andExpect(jsonPath("$.[*].durationEdited").value(hasItem(DEFAULT_DURATION_EDITED.toString())))
            .andExpect(jsonPath("$.[*].scenography").value(hasItem(DEFAULT_SCENOGRAPHY)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].departmentState").value(hasItem(DEFAULT_DEPARTMENT_STATE)))
            .andExpect(jsonPath("$.[*].producer").value(hasItem(DEFAULT_PRODUCER)))
            .andExpect(jsonPath("$.[*].productionAssistant").value(hasItem(DEFAULT_PRODUCTION_ASSISTANT)))
            .andExpect(jsonPath("$.[*].operatorVTRPlayOut").value(hasItem(DEFAULT_OPERATOR_VTR_PLAY_OUT)))
            .andExpect(jsonPath("$.[*].productionCredits").value(hasItem(DEFAULT_PRODUCTION_CREDITS)))
            .andExpect(jsonPath("$.[*].castActors").value(hasItem(DEFAULT_CAST_ACTORS)))
            .andExpect(jsonPath("$.[*].archiveStatus").value(hasItem(DEFAULT_ARCHIVE_STATUS)))
            .andExpect(jsonPath("$.[*].interpreterTranslator").value(hasItem(DEFAULT_INTERPRETER_TRANSLATOR)))
            .andExpect(jsonPath("$.[*].dubbing").value(hasItem(DEFAULT_DUBBING)))
            .andExpect(jsonPath("$.[*].subtitleCC").value(hasItem(DEFAULT_SUBTITLE_CC)))
            .andExpect(jsonPath("$.[*].cataloguerOriginal").value(hasItem(DEFAULT_CATALOGUER_ORIGINAL)))
            .andExpect(jsonPath("$.[*].cataloguerEdited").value(hasItem(DEFAULT_CATALOGUER_EDITED)))
            .andExpect(jsonPath("$.[*].postproductionEditor").value(hasItem(DEFAULT_POSTPRODUCTION_EDITOR)))
            .andExpect(jsonPath("$.[*].operatorIngestion").value(hasItem(DEFAULT_OPERATOR_INGESTION)))
            .andExpect(jsonPath("$.[*].serviceOTT").value(hasItem(DEFAULT_SERVICE_OTT.booleanValue())))
            .andExpect(jsonPath("$.[*].archivadoLTOOriginal").value(hasItem(DEFAULT_ARCHIVADO_LTO_ORIGINAL.booleanValue())))
            .andExpect(jsonPath("$.[*].archivadoLTOEdited").value(hasItem(DEFAULT_ARCHIVADO_LTO_EDITED.booleanValue())))
            .andExpect(jsonPath("$.[*].observations").value(hasItem(DEFAULT_OBSERVATIONS)))
            .andExpect(jsonPath("$.[*].rightsManagement").value(hasItem(DEFAULT_RIGHTS_MANAGEMENT)))
            .andExpect(jsonPath("$.[*].cataloguerProduction").value(hasItem(DEFAULT_CATALOGUER_PRODUCTION)))
            .andExpect(jsonPath("$.[*].cataloguerIngest").value(hasItem(DEFAULT_CATALOGUER_INGEST)))
            .andExpect(jsonPath("$.[*].cataloguerMaster").value(hasItem(DEFAULT_CATALOGUER_MASTER)))
            .andExpect(jsonPath("$.[*].urlEdition").value(hasItem(DEFAULT_URL_EDITION)))
            .andExpect(jsonPath("$.[*].urlOriginal").value(hasItem(DEFAULT_URL_ORIGINAL)))
            .andExpect(jsonPath("$.[*].createById").value(hasItem(DEFAULT_CREATE_BY_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(sameInstant(DEFAULT_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].lastModifiedById").value(hasItem(DEFAULT_LAST_MODIFIED_BY_ID)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getContent() throws Exception {
        // Initialize the database
        contentRepository.saveAndFlush(content);

        // Get the content
        restContentMockMvc
            .perform(get(ENTITY_API_URL_ID, content.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(content.getId().intValue()))
            .andExpect(jsonPath("$.recordingDate").value(sameInstant(DEFAULT_RECORDING_DATE)))
            .andExpect(jsonPath("$.editionDate").value(sameInstant(DEFAULT_EDITION_DATE)))
            .andExpect(jsonPath("$.signatureOriginal").value(DEFAULT_SIGNATURE_ORIGINAL))
            .andExpect(jsonPath("$.signatureEdited").value(DEFAULT_SIGNATURE_EDITED))
            .andExpect(jsonPath("$.eventReason").value(DEFAULT_EVENT_REASON))
            .andExpect(jsonPath("$.eventSlogan").value(DEFAULT_EVENT_SLOGAN))
            .andExpect(jsonPath("$.nameConduction").value(DEFAULT_NAME_CONDUCTION))
            .andExpect(jsonPath("$.positionConduction").value(DEFAULT_POSITION_CONDUCTION))
            .andExpect(jsonPath("$.specialtiesPanel").value(DEFAULT_SPECIALTIES_PANEL))
            .andExpect(jsonPath("$.namePanel").value(DEFAULT_NAME_PANEL))
            .andExpect(jsonPath("$.titleSubject").value(DEFAULT_TITLE_SUBJECT))
            .andExpect(jsonPath("$.biblicalPassage").value(DEFAULT_BIBLICAL_PASSAGE))
            .andExpect(jsonPath("$.synopsisDescription").value(DEFAULT_SYNOPSIS_DESCRIPTION))
            .andExpect(jsonPath("$.storyline").value(DEFAULT_STORYLINE))
            .andExpect(jsonPath("$.mediaContent").value(DEFAULT_MEDIA_CONTENT))
            .andExpect(jsonPath("$.durationOriginal").value(DEFAULT_DURATION_ORIGINAL.toString()))
            .andExpect(jsonPath("$.durationEdited").value(DEFAULT_DURATION_EDITED.toString()))
            .andExpect(jsonPath("$.scenography").value(DEFAULT_SCENOGRAPHY))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.departmentState").value(DEFAULT_DEPARTMENT_STATE))
            .andExpect(jsonPath("$.producer").value(DEFAULT_PRODUCER))
            .andExpect(jsonPath("$.productionAssistant").value(DEFAULT_PRODUCTION_ASSISTANT))
            .andExpect(jsonPath("$.operatorVTRPlayOut").value(DEFAULT_OPERATOR_VTR_PLAY_OUT))
            .andExpect(jsonPath("$.productionCredits").value(DEFAULT_PRODUCTION_CREDITS))
            .andExpect(jsonPath("$.castActors").value(DEFAULT_CAST_ACTORS))
            .andExpect(jsonPath("$.archiveStatus").value(DEFAULT_ARCHIVE_STATUS))
            .andExpect(jsonPath("$.interpreterTranslator").value(DEFAULT_INTERPRETER_TRANSLATOR))
            .andExpect(jsonPath("$.dubbing").value(DEFAULT_DUBBING))
            .andExpect(jsonPath("$.subtitleCC").value(DEFAULT_SUBTITLE_CC))
            .andExpect(jsonPath("$.cataloguerOriginal").value(DEFAULT_CATALOGUER_ORIGINAL))
            .andExpect(jsonPath("$.cataloguerEdited").value(DEFAULT_CATALOGUER_EDITED))
            .andExpect(jsonPath("$.postproductionEditor").value(DEFAULT_POSTPRODUCTION_EDITOR))
            .andExpect(jsonPath("$.operatorIngestion").value(DEFAULT_OPERATOR_INGESTION))
            .andExpect(jsonPath("$.serviceOTT").value(DEFAULT_SERVICE_OTT.booleanValue()))
            .andExpect(jsonPath("$.archivadoLTOOriginal").value(DEFAULT_ARCHIVADO_LTO_ORIGINAL.booleanValue()))
            .andExpect(jsonPath("$.archivadoLTOEdited").value(DEFAULT_ARCHIVADO_LTO_EDITED.booleanValue()))
            .andExpect(jsonPath("$.observations").value(DEFAULT_OBSERVATIONS))
            .andExpect(jsonPath("$.rightsManagement").value(DEFAULT_RIGHTS_MANAGEMENT))
            .andExpect(jsonPath("$.cataloguerProduction").value(DEFAULT_CATALOGUER_PRODUCTION))
            .andExpect(jsonPath("$.cataloguerIngest").value(DEFAULT_CATALOGUER_INGEST))
            .andExpect(jsonPath("$.cataloguerMaster").value(DEFAULT_CATALOGUER_MASTER))
            .andExpect(jsonPath("$.urlEdition").value(DEFAULT_URL_EDITION))
            .andExpect(jsonPath("$.urlOriginal").value(DEFAULT_URL_ORIGINAL))
            .andExpect(jsonPath("$.createById").value(DEFAULT_CREATE_BY_ID))
            .andExpect(jsonPath("$.createdDate").value(sameInstant(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.lastModifiedById").value(DEFAULT_LAST_MODIFIED_BY_ID))
            .andExpect(jsonPath("$.lastModifiedDate").value(sameInstant(DEFAULT_LAST_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingContent() throws Exception {
        // Get the content
        restContentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContent() throws Exception {
        // Initialize the database
        contentRepository.saveAndFlush(content);

        int databaseSizeBeforeUpdate = contentRepository.findAll().size();

        // Update the content
        Content updatedContent = contentRepository.findById(content.getId()).get();
        // Disconnect from session so that the updates on updatedContent are not directly saved in db
        em.detach(updatedContent);
        updatedContent
            .recordingDate(UPDATED_RECORDING_DATE)
            .editionDate(UPDATED_EDITION_DATE)
            .signatureOriginal(UPDATED_SIGNATURE_ORIGINAL)
            .signatureEdited(UPDATED_SIGNATURE_EDITED)
            .eventReason(UPDATED_EVENT_REASON)
            .eventSlogan(UPDATED_EVENT_SLOGAN)
            .nameConduction(UPDATED_NAME_CONDUCTION)
            .positionConduction(UPDATED_POSITION_CONDUCTION)
            .specialtiesPanel(UPDATED_SPECIALTIES_PANEL)
            .namePanel(UPDATED_NAME_PANEL)
            .titleSubject(UPDATED_TITLE_SUBJECT)
            .biblicalPassage(UPDATED_BIBLICAL_PASSAGE)
            .synopsisDescription(UPDATED_SYNOPSIS_DESCRIPTION)
            .storyline(UPDATED_STORYLINE)
            .mediaContent(UPDATED_MEDIA_CONTENT)
            .durationOriginal(UPDATED_DURATION_ORIGINAL)
            .durationEdited(UPDATED_DURATION_EDITED)
            .scenography(UPDATED_SCENOGRAPHY)
            .location(UPDATED_LOCATION)
            .city(UPDATED_CITY)
            .departmentState(UPDATED_DEPARTMENT_STATE)
            .producer(UPDATED_PRODUCER)
            .productionAssistant(UPDATED_PRODUCTION_ASSISTANT)
            .operatorVTRPlayOut(UPDATED_OPERATOR_VTR_PLAY_OUT)
            .productionCredits(UPDATED_PRODUCTION_CREDITS)
            .castActors(UPDATED_CAST_ACTORS)
            .archiveStatus(UPDATED_ARCHIVE_STATUS)
            .interpreterTranslator(UPDATED_INTERPRETER_TRANSLATOR)
            .dubbing(UPDATED_DUBBING)
            .subtitleCC(UPDATED_SUBTITLE_CC)
            .cataloguerOriginal(UPDATED_CATALOGUER_ORIGINAL)
            .cataloguerEdited(UPDATED_CATALOGUER_EDITED)
            .postproductionEditor(UPDATED_POSTPRODUCTION_EDITOR)
            .operatorIngestion(UPDATED_OPERATOR_INGESTION)
            .serviceOTT(UPDATED_SERVICE_OTT)
            .archivadoLTOOriginal(UPDATED_ARCHIVADO_LTO_ORIGINAL)
            .archivadoLTOEdited(UPDATED_ARCHIVADO_LTO_EDITED)
            .observations(UPDATED_OBSERVATIONS)
            .rightsManagement(UPDATED_RIGHTS_MANAGEMENT)
            .cataloguerProduction(UPDATED_CATALOGUER_PRODUCTION)
            .cataloguerIngest(UPDATED_CATALOGUER_INGEST)
            .cataloguerMaster(UPDATED_CATALOGUER_MASTER)
            .urlEdition(UPDATED_URL_EDITION)
            .urlOriginal(UPDATED_URL_ORIGINAL)
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        ContentDTO contentDTO = contentMapper.toDto(updatedContent);

        restContentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentDTO))
            )
            .andExpect(status().isOk());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
        Content testContent = contentList.get(contentList.size() - 1);
        assertThat(testContent.getRecordingDate()).isEqualTo(UPDATED_RECORDING_DATE);
        assertThat(testContent.getEditionDate()).isEqualTo(UPDATED_EDITION_DATE);
        assertThat(testContent.getSignatureOriginal()).isEqualTo(UPDATED_SIGNATURE_ORIGINAL);
        assertThat(testContent.getSignatureEdited()).isEqualTo(UPDATED_SIGNATURE_EDITED);
        assertThat(testContent.getEventReason()).isEqualTo(UPDATED_EVENT_REASON);
        assertThat(testContent.getEventSlogan()).isEqualTo(UPDATED_EVENT_SLOGAN);
        assertThat(testContent.getNameConduction()).isEqualTo(UPDATED_NAME_CONDUCTION);
        assertThat(testContent.getPositionConduction()).isEqualTo(UPDATED_POSITION_CONDUCTION);
        assertThat(testContent.getSpecialtiesPanel()).isEqualTo(UPDATED_SPECIALTIES_PANEL);
        assertThat(testContent.getNamePanel()).isEqualTo(UPDATED_NAME_PANEL);
        assertThat(testContent.getTitleSubject()).isEqualTo(UPDATED_TITLE_SUBJECT);
        assertThat(testContent.getBiblicalPassage()).isEqualTo(UPDATED_BIBLICAL_PASSAGE);
        assertThat(testContent.getSynopsisDescription()).isEqualTo(UPDATED_SYNOPSIS_DESCRIPTION);
        assertThat(testContent.getStoryline()).isEqualTo(UPDATED_STORYLINE);
        assertThat(testContent.getMediaContent()).isEqualTo(UPDATED_MEDIA_CONTENT);
        assertThat(testContent.getDurationOriginal()).isEqualTo(UPDATED_DURATION_ORIGINAL);
        assertThat(testContent.getDurationEdited()).isEqualTo(UPDATED_DURATION_EDITED);
        assertThat(testContent.getScenography()).isEqualTo(UPDATED_SCENOGRAPHY);
        assertThat(testContent.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testContent.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testContent.getDepartmentState()).isEqualTo(UPDATED_DEPARTMENT_STATE);
        assertThat(testContent.getProducer()).isEqualTo(UPDATED_PRODUCER);
        assertThat(testContent.getProductionAssistant()).isEqualTo(UPDATED_PRODUCTION_ASSISTANT);
        assertThat(testContent.getOperatorVTRPlayOut()).isEqualTo(UPDATED_OPERATOR_VTR_PLAY_OUT);
        assertThat(testContent.getProductionCredits()).isEqualTo(UPDATED_PRODUCTION_CREDITS);
        assertThat(testContent.getCastActors()).isEqualTo(UPDATED_CAST_ACTORS);
        assertThat(testContent.getArchiveStatus()).isEqualTo(UPDATED_ARCHIVE_STATUS);
        assertThat(testContent.getInterpreterTranslator()).isEqualTo(UPDATED_INTERPRETER_TRANSLATOR);
        assertThat(testContent.getDubbing()).isEqualTo(UPDATED_DUBBING);
        assertThat(testContent.getSubtitleCC()).isEqualTo(UPDATED_SUBTITLE_CC);
        assertThat(testContent.getCataloguerOriginal()).isEqualTo(UPDATED_CATALOGUER_ORIGINAL);
        assertThat(testContent.getCataloguerEdited()).isEqualTo(UPDATED_CATALOGUER_EDITED);
        assertThat(testContent.getPostproductionEditor()).isEqualTo(UPDATED_POSTPRODUCTION_EDITOR);
        assertThat(testContent.getOperatorIngestion()).isEqualTo(UPDATED_OPERATOR_INGESTION);
        assertThat(testContent.getServiceOTT()).isEqualTo(UPDATED_SERVICE_OTT);
        assertThat(testContent.getArchivadoLTOOriginal()).isEqualTo(UPDATED_ARCHIVADO_LTO_ORIGINAL);
        assertThat(testContent.getArchivadoLTOEdited()).isEqualTo(UPDATED_ARCHIVADO_LTO_EDITED);
        assertThat(testContent.getObservations()).isEqualTo(UPDATED_OBSERVATIONS);
        assertThat(testContent.getRightsManagement()).isEqualTo(UPDATED_RIGHTS_MANAGEMENT);
        assertThat(testContent.getCataloguerProduction()).isEqualTo(UPDATED_CATALOGUER_PRODUCTION);
        assertThat(testContent.getCataloguerIngest()).isEqualTo(UPDATED_CATALOGUER_INGEST);
        assertThat(testContent.getCataloguerMaster()).isEqualTo(UPDATED_CATALOGUER_MASTER);
        assertThat(testContent.getUrlEdition()).isEqualTo(UPDATED_URL_EDITION);
        assertThat(testContent.getUrlOriginal()).isEqualTo(UPDATED_URL_ORIGINAL);
        assertThat(testContent.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testContent.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testContent.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testContent.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingContent() throws Exception {
        int databaseSizeBeforeUpdate = contentRepository.findAll().size();
        content.setId(count.incrementAndGet());

        // Create the Content
        ContentDTO contentDTO = contentMapper.toDto(content);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContent() throws Exception {
        int databaseSizeBeforeUpdate = contentRepository.findAll().size();
        content.setId(count.incrementAndGet());

        // Create the Content
        ContentDTO contentDTO = contentMapper.toDto(content);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContent() throws Exception {
        int databaseSizeBeforeUpdate = contentRepository.findAll().size();
        content.setId(count.incrementAndGet());

        // Create the Content
        ContentDTO contentDTO = contentMapper.toDto(content);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contentDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContentWithPatch() throws Exception {
        // Initialize the database
        contentRepository.saveAndFlush(content);

        int databaseSizeBeforeUpdate = contentRepository.findAll().size();

        // Update the content using partial update
        Content partialUpdatedContent = new Content();
        partialUpdatedContent.setId(content.getId());

        partialUpdatedContent
            .recordingDate(UPDATED_RECORDING_DATE)
            .signatureOriginal(UPDATED_SIGNATURE_ORIGINAL)
            .signatureEdited(UPDATED_SIGNATURE_EDITED)
            .nameConduction(UPDATED_NAME_CONDUCTION)
            .positionConduction(UPDATED_POSITION_CONDUCTION)
            .biblicalPassage(UPDATED_BIBLICAL_PASSAGE)
            .storyline(UPDATED_STORYLINE)
            .productionCredits(UPDATED_PRODUCTION_CREDITS)
            .archiveStatus(UPDATED_ARCHIVE_STATUS)
            .dubbing(UPDATED_DUBBING)
            .subtitleCC(UPDATED_SUBTITLE_CC)
            .operatorIngestion(UPDATED_OPERATOR_INGESTION)
            .serviceOTT(UPDATED_SERVICE_OTT)
            .archivadoLTOEdited(UPDATED_ARCHIVADO_LTO_EDITED)
            .observations(UPDATED_OBSERVATIONS)
            .rightsManagement(UPDATED_RIGHTS_MANAGEMENT)
            .urlOriginal(UPDATED_URL_ORIGINAL)
            .createById(UPDATED_CREATE_BY_ID);

        restContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContent))
            )
            .andExpect(status().isOk());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
        Content testContent = contentList.get(contentList.size() - 1);
        assertThat(testContent.getRecordingDate()).isEqualTo(UPDATED_RECORDING_DATE);
        assertThat(testContent.getEditionDate()).isEqualTo(DEFAULT_EDITION_DATE);
        assertThat(testContent.getSignatureOriginal()).isEqualTo(UPDATED_SIGNATURE_ORIGINAL);
        assertThat(testContent.getSignatureEdited()).isEqualTo(UPDATED_SIGNATURE_EDITED);
        assertThat(testContent.getEventReason()).isEqualTo(DEFAULT_EVENT_REASON);
        assertThat(testContent.getEventSlogan()).isEqualTo(DEFAULT_EVENT_SLOGAN);
        assertThat(testContent.getNameConduction()).isEqualTo(UPDATED_NAME_CONDUCTION);
        assertThat(testContent.getPositionConduction()).isEqualTo(UPDATED_POSITION_CONDUCTION);
        assertThat(testContent.getSpecialtiesPanel()).isEqualTo(DEFAULT_SPECIALTIES_PANEL);
        assertThat(testContent.getNamePanel()).isEqualTo(DEFAULT_NAME_PANEL);
        assertThat(testContent.getTitleSubject()).isEqualTo(DEFAULT_TITLE_SUBJECT);
        assertThat(testContent.getBiblicalPassage()).isEqualTo(UPDATED_BIBLICAL_PASSAGE);
        assertThat(testContent.getSynopsisDescription()).isEqualTo(DEFAULT_SYNOPSIS_DESCRIPTION);
        assertThat(testContent.getStoryline()).isEqualTo(UPDATED_STORYLINE);
        assertThat(testContent.getMediaContent()).isEqualTo(DEFAULT_MEDIA_CONTENT);
        assertThat(testContent.getDurationOriginal()).isEqualTo(DEFAULT_DURATION_ORIGINAL);
        assertThat(testContent.getDurationEdited()).isEqualTo(DEFAULT_DURATION_EDITED);
        assertThat(testContent.getScenography()).isEqualTo(DEFAULT_SCENOGRAPHY);
        assertThat(testContent.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testContent.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testContent.getDepartmentState()).isEqualTo(DEFAULT_DEPARTMENT_STATE);
        assertThat(testContent.getProducer()).isEqualTo(DEFAULT_PRODUCER);
        assertThat(testContent.getProductionAssistant()).isEqualTo(DEFAULT_PRODUCTION_ASSISTANT);
        assertThat(testContent.getOperatorVTRPlayOut()).isEqualTo(DEFAULT_OPERATOR_VTR_PLAY_OUT);
        assertThat(testContent.getProductionCredits()).isEqualTo(UPDATED_PRODUCTION_CREDITS);
        assertThat(testContent.getCastActors()).isEqualTo(DEFAULT_CAST_ACTORS);
        assertThat(testContent.getArchiveStatus()).isEqualTo(UPDATED_ARCHIVE_STATUS);
        assertThat(testContent.getInterpreterTranslator()).isEqualTo(DEFAULT_INTERPRETER_TRANSLATOR);
        assertThat(testContent.getDubbing()).isEqualTo(UPDATED_DUBBING);
        assertThat(testContent.getSubtitleCC()).isEqualTo(UPDATED_SUBTITLE_CC);
        assertThat(testContent.getCataloguerOriginal()).isEqualTo(DEFAULT_CATALOGUER_ORIGINAL);
        assertThat(testContent.getCataloguerEdited()).isEqualTo(DEFAULT_CATALOGUER_EDITED);
        assertThat(testContent.getPostproductionEditor()).isEqualTo(DEFAULT_POSTPRODUCTION_EDITOR);
        assertThat(testContent.getOperatorIngestion()).isEqualTo(UPDATED_OPERATOR_INGESTION);
        assertThat(testContent.getServiceOTT()).isEqualTo(UPDATED_SERVICE_OTT);
        assertThat(testContent.getArchivadoLTOOriginal()).isEqualTo(DEFAULT_ARCHIVADO_LTO_ORIGINAL);
        assertThat(testContent.getArchivadoLTOEdited()).isEqualTo(UPDATED_ARCHIVADO_LTO_EDITED);
        assertThat(testContent.getObservations()).isEqualTo(UPDATED_OBSERVATIONS);
        assertThat(testContent.getRightsManagement()).isEqualTo(UPDATED_RIGHTS_MANAGEMENT);
        assertThat(testContent.getCataloguerProduction()).isEqualTo(DEFAULT_CATALOGUER_PRODUCTION);
        assertThat(testContent.getCataloguerIngest()).isEqualTo(DEFAULT_CATALOGUER_INGEST);
        assertThat(testContent.getCataloguerMaster()).isEqualTo(DEFAULT_CATALOGUER_MASTER);
        assertThat(testContent.getUrlEdition()).isEqualTo(DEFAULT_URL_EDITION);
        assertThat(testContent.getUrlOriginal()).isEqualTo(UPDATED_URL_ORIGINAL);
        assertThat(testContent.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testContent.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testContent.getLastModifiedById()).isEqualTo(DEFAULT_LAST_MODIFIED_BY_ID);
        assertThat(testContent.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateContentWithPatch() throws Exception {
        // Initialize the database
        contentRepository.saveAndFlush(content);

        int databaseSizeBeforeUpdate = contentRepository.findAll().size();

        // Update the content using partial update
        Content partialUpdatedContent = new Content();
        partialUpdatedContent.setId(content.getId());

        partialUpdatedContent
            .recordingDate(UPDATED_RECORDING_DATE)
            .editionDate(UPDATED_EDITION_DATE)
            .signatureOriginal(UPDATED_SIGNATURE_ORIGINAL)
            .signatureEdited(UPDATED_SIGNATURE_EDITED)
            .eventReason(UPDATED_EVENT_REASON)
            .eventSlogan(UPDATED_EVENT_SLOGAN)
            .nameConduction(UPDATED_NAME_CONDUCTION)
            .positionConduction(UPDATED_POSITION_CONDUCTION)
            .specialtiesPanel(UPDATED_SPECIALTIES_PANEL)
            .namePanel(UPDATED_NAME_PANEL)
            .titleSubject(UPDATED_TITLE_SUBJECT)
            .biblicalPassage(UPDATED_BIBLICAL_PASSAGE)
            .synopsisDescription(UPDATED_SYNOPSIS_DESCRIPTION)
            .storyline(UPDATED_STORYLINE)
            .mediaContent(UPDATED_MEDIA_CONTENT)
            .durationOriginal(UPDATED_DURATION_ORIGINAL)
            .durationEdited(UPDATED_DURATION_EDITED)
            .scenography(UPDATED_SCENOGRAPHY)
            .location(UPDATED_LOCATION)
            .city(UPDATED_CITY)
            .departmentState(UPDATED_DEPARTMENT_STATE)
            .producer(UPDATED_PRODUCER)
            .productionAssistant(UPDATED_PRODUCTION_ASSISTANT)
            .operatorVTRPlayOut(UPDATED_OPERATOR_VTR_PLAY_OUT)
            .productionCredits(UPDATED_PRODUCTION_CREDITS)
            .castActors(UPDATED_CAST_ACTORS)
            .archiveStatus(UPDATED_ARCHIVE_STATUS)
            .interpreterTranslator(UPDATED_INTERPRETER_TRANSLATOR)
            .dubbing(UPDATED_DUBBING)
            .subtitleCC(UPDATED_SUBTITLE_CC)
            .cataloguerOriginal(UPDATED_CATALOGUER_ORIGINAL)
            .cataloguerEdited(UPDATED_CATALOGUER_EDITED)
            .postproductionEditor(UPDATED_POSTPRODUCTION_EDITOR)
            .operatorIngestion(UPDATED_OPERATOR_INGESTION)
            .serviceOTT(UPDATED_SERVICE_OTT)
            .archivadoLTOOriginal(UPDATED_ARCHIVADO_LTO_ORIGINAL)
            .archivadoLTOEdited(UPDATED_ARCHIVADO_LTO_EDITED)
            .observations(UPDATED_OBSERVATIONS)
            .rightsManagement(UPDATED_RIGHTS_MANAGEMENT)
            .cataloguerProduction(UPDATED_CATALOGUER_PRODUCTION)
            .cataloguerIngest(UPDATED_CATALOGUER_INGEST)
            .cataloguerMaster(UPDATED_CATALOGUER_MASTER)
            .urlEdition(UPDATED_URL_EDITION)
            .urlOriginal(UPDATED_URL_ORIGINAL)
            .createById(UPDATED_CREATE_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedById(UPDATED_LAST_MODIFIED_BY_ID)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContent))
            )
            .andExpect(status().isOk());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
        Content testContent = contentList.get(contentList.size() - 1);
        assertThat(testContent.getRecordingDate()).isEqualTo(UPDATED_RECORDING_DATE);
        assertThat(testContent.getEditionDate()).isEqualTo(UPDATED_EDITION_DATE);
        assertThat(testContent.getSignatureOriginal()).isEqualTo(UPDATED_SIGNATURE_ORIGINAL);
        assertThat(testContent.getSignatureEdited()).isEqualTo(UPDATED_SIGNATURE_EDITED);
        assertThat(testContent.getEventReason()).isEqualTo(UPDATED_EVENT_REASON);
        assertThat(testContent.getEventSlogan()).isEqualTo(UPDATED_EVENT_SLOGAN);
        assertThat(testContent.getNameConduction()).isEqualTo(UPDATED_NAME_CONDUCTION);
        assertThat(testContent.getPositionConduction()).isEqualTo(UPDATED_POSITION_CONDUCTION);
        assertThat(testContent.getSpecialtiesPanel()).isEqualTo(UPDATED_SPECIALTIES_PANEL);
        assertThat(testContent.getNamePanel()).isEqualTo(UPDATED_NAME_PANEL);
        assertThat(testContent.getTitleSubject()).isEqualTo(UPDATED_TITLE_SUBJECT);
        assertThat(testContent.getBiblicalPassage()).isEqualTo(UPDATED_BIBLICAL_PASSAGE);
        assertThat(testContent.getSynopsisDescription()).isEqualTo(UPDATED_SYNOPSIS_DESCRIPTION);
        assertThat(testContent.getStoryline()).isEqualTo(UPDATED_STORYLINE);
        assertThat(testContent.getMediaContent()).isEqualTo(UPDATED_MEDIA_CONTENT);
        assertThat(testContent.getDurationOriginal()).isEqualTo(UPDATED_DURATION_ORIGINAL);
        assertThat(testContent.getDurationEdited()).isEqualTo(UPDATED_DURATION_EDITED);
        assertThat(testContent.getScenography()).isEqualTo(UPDATED_SCENOGRAPHY);
        assertThat(testContent.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testContent.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testContent.getDepartmentState()).isEqualTo(UPDATED_DEPARTMENT_STATE);
        assertThat(testContent.getProducer()).isEqualTo(UPDATED_PRODUCER);
        assertThat(testContent.getProductionAssistant()).isEqualTo(UPDATED_PRODUCTION_ASSISTANT);
        assertThat(testContent.getOperatorVTRPlayOut()).isEqualTo(UPDATED_OPERATOR_VTR_PLAY_OUT);
        assertThat(testContent.getProductionCredits()).isEqualTo(UPDATED_PRODUCTION_CREDITS);
        assertThat(testContent.getCastActors()).isEqualTo(UPDATED_CAST_ACTORS);
        assertThat(testContent.getArchiveStatus()).isEqualTo(UPDATED_ARCHIVE_STATUS);
        assertThat(testContent.getInterpreterTranslator()).isEqualTo(UPDATED_INTERPRETER_TRANSLATOR);
        assertThat(testContent.getDubbing()).isEqualTo(UPDATED_DUBBING);
        assertThat(testContent.getSubtitleCC()).isEqualTo(UPDATED_SUBTITLE_CC);
        assertThat(testContent.getCataloguerOriginal()).isEqualTo(UPDATED_CATALOGUER_ORIGINAL);
        assertThat(testContent.getCataloguerEdited()).isEqualTo(UPDATED_CATALOGUER_EDITED);
        assertThat(testContent.getPostproductionEditor()).isEqualTo(UPDATED_POSTPRODUCTION_EDITOR);
        assertThat(testContent.getOperatorIngestion()).isEqualTo(UPDATED_OPERATOR_INGESTION);
        assertThat(testContent.getServiceOTT()).isEqualTo(UPDATED_SERVICE_OTT);
        assertThat(testContent.getArchivadoLTOOriginal()).isEqualTo(UPDATED_ARCHIVADO_LTO_ORIGINAL);
        assertThat(testContent.getArchivadoLTOEdited()).isEqualTo(UPDATED_ARCHIVADO_LTO_EDITED);
        assertThat(testContent.getObservations()).isEqualTo(UPDATED_OBSERVATIONS);
        assertThat(testContent.getRightsManagement()).isEqualTo(UPDATED_RIGHTS_MANAGEMENT);
        assertThat(testContent.getCataloguerProduction()).isEqualTo(UPDATED_CATALOGUER_PRODUCTION);
        assertThat(testContent.getCataloguerIngest()).isEqualTo(UPDATED_CATALOGUER_INGEST);
        assertThat(testContent.getCataloguerMaster()).isEqualTo(UPDATED_CATALOGUER_MASTER);
        assertThat(testContent.getUrlEdition()).isEqualTo(UPDATED_URL_EDITION);
        assertThat(testContent.getUrlOriginal()).isEqualTo(UPDATED_URL_ORIGINAL);
        assertThat(testContent.getCreateById()).isEqualTo(UPDATED_CREATE_BY_ID);
        assertThat(testContent.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testContent.getLastModifiedById()).isEqualTo(UPDATED_LAST_MODIFIED_BY_ID);
        assertThat(testContent.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingContent() throws Exception {
        int databaseSizeBeforeUpdate = contentRepository.findAll().size();
        content.setId(count.incrementAndGet());

        // Create the Content
        ContentDTO contentDTO = contentMapper.toDto(content);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContent() throws Exception {
        int databaseSizeBeforeUpdate = contentRepository.findAll().size();
        content.setId(count.incrementAndGet());

        // Create the Content
        ContentDTO contentDTO = contentMapper.toDto(content);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContent() throws Exception {
        int databaseSizeBeforeUpdate = contentRepository.findAll().size();
        content.setId(count.incrementAndGet());

        // Create the Content
        ContentDTO contentDTO = contentMapper.toDto(content);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(contentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Content in the database
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContent() throws Exception {
        // Initialize the database
        contentRepository.saveAndFlush(content);

        int databaseSizeBeforeDelete = contentRepository.findAll().size();

        // Delete the content
        restContentMockMvc
            .perform(delete(ENTITY_API_URL_ID, content.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
