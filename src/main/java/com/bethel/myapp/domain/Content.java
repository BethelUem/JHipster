package com.bethel.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Content.
 */
@Entity
@Table(name = "content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "recording_date")
    private ZonedDateTime recordingDate;

    @Column(name = "edition_date")
    private ZonedDateTime editionDate;

    @Column(name = "signature_original")
    private String signatureOriginal;

    @Column(name = "signature_edited")
    private String signatureEdited;

    @Column(name = "event_reason")
    private String eventReason;

    @Column(name = "event_slogan")
    private String eventSlogan;

    @Column(name = "name_conduction")
    private String nameConduction;

    @Column(name = "position_conduction")
    private String positionConduction;

    @Column(name = "specialties_panel")
    private String specialtiesPanel;

    @Column(name = "name_panel")
    private String namePanel;

    @Column(name = "title_subject")
    private String titleSubject;

    @Column(name = "biblical_passage")
    private String biblicalPassage;

    @Column(name = "synopsis_description")
    private String synopsisDescription;

    @Column(name = "storyline")
    private String storyline;

    @Column(name = "media_content")
    private String mediaContent;

    @Column(name = "duration_original")
    private Duration durationOriginal;

    @Column(name = "duration_edited")
    private Duration durationEdited;

    @Column(name = "scenography")
    private String scenography;

    @Column(name = "location")
    private String location;

    @Column(name = "city")
    private String city;

    @Column(name = "department_state")
    private String departmentState;

    @Column(name = "producer")
    private String producer;

    @Column(name = "production_assistant")
    private String productionAssistant;

    @Column(name = "operator_vtr_play_out")
    private String operatorVTRPlayOut;

    @Column(name = "production_credits")
    private String productionCredits;

    @Column(name = "cast_actors")
    private String castActors;

    @Column(name = "archive_status")
    private String archiveStatus;

    @Column(name = "interpreter_translator")
    private String interpreterTranslator;

    @Column(name = "dubbing")
    private String dubbing;

    @Column(name = "subtitle_cc")
    private String subtitleCC;

    @Column(name = "cataloguer_original")
    private String cataloguerOriginal;

    @Column(name = "cataloguer_edited")
    private String cataloguerEdited;

    @Column(name = "postproduction_editor")
    private String postproductionEditor;

    @Column(name = "operator_ingestion")
    private String operatorIngestion;

    @Column(name = "service_ott")
    private Boolean serviceOTT;

    @Column(name = "archivado_lto_original")
    private Boolean archivadoLTOOriginal;

    @Column(name = "archivado_lto_edited")
    private Boolean archivadoLTOEdited;

    @Column(name = "observations")
    private String observations;

    @Column(name = "rights_management")
    private String rightsManagement;

    @Column(name = "cataloguer_production")
    private String cataloguerProduction;

    @Column(name = "cataloguer_ingest")
    private String cataloguerIngest;

    @Column(name = "cataloguer_master")
    private String cataloguerMaster;

    @Column(name = "url_edition")
    private String urlEdition;

    @Column(name = "url_original")
    private String urlOriginal;

    @Column(name = "create_by_id")
    private Integer createById;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "last_modified_by_id")
    private Integer lastModifiedById;

    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;

    @OneToMany(mappedBy = "contentId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentId", "catalogId" }, allowSetters = true)
    private Set<ContentTag> catTags = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catContentType;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catTypeConduction;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catProgram;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catShiftRecording;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catTargetAudience;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catCountry;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catProductionCompany;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catArchivalCollection;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catOriginalLanguage;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catInterpreterLanguage;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catDubbingLanguage;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catSubtitleLanguage;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catTvCensorship;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catClassificationCategory;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catGenreProgram;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catFormatProgram;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catResolutionOriginal;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catResolutionEdited;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catCatalogingLevelOriginal;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catCatalogingLevelEdited;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catVideoQuality;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catAudioQuality;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catMusicalGroup;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catMusicalGenre;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catApproved;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Content id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRecordingDate() {
        return this.recordingDate;
    }

    public Content recordingDate(ZonedDateTime recordingDate) {
        this.setRecordingDate(recordingDate);
        return this;
    }

    public void setRecordingDate(ZonedDateTime recordingDate) {
        this.recordingDate = recordingDate;
    }

    public ZonedDateTime getEditionDate() {
        return this.editionDate;
    }

    public Content editionDate(ZonedDateTime editionDate) {
        this.setEditionDate(editionDate);
        return this;
    }

    public void setEditionDate(ZonedDateTime editionDate) {
        this.editionDate = editionDate;
    }

    public String getSignatureOriginal() {
        return this.signatureOriginal;
    }

    public Content signatureOriginal(String signatureOriginal) {
        this.setSignatureOriginal(signatureOriginal);
        return this;
    }

    public void setSignatureOriginal(String signatureOriginal) {
        this.signatureOriginal = signatureOriginal;
    }

    public String getSignatureEdited() {
        return this.signatureEdited;
    }

    public Content signatureEdited(String signatureEdited) {
        this.setSignatureEdited(signatureEdited);
        return this;
    }

    public void setSignatureEdited(String signatureEdited) {
        this.signatureEdited = signatureEdited;
    }

    public String getEventReason() {
        return this.eventReason;
    }

    public Content eventReason(String eventReason) {
        this.setEventReason(eventReason);
        return this;
    }

    public void setEventReason(String eventReason) {
        this.eventReason = eventReason;
    }

    public String getEventSlogan() {
        return this.eventSlogan;
    }

    public Content eventSlogan(String eventSlogan) {
        this.setEventSlogan(eventSlogan);
        return this;
    }

    public void setEventSlogan(String eventSlogan) {
        this.eventSlogan = eventSlogan;
    }

    public String getNameConduction() {
        return this.nameConduction;
    }

    public Content nameConduction(String nameConduction) {
        this.setNameConduction(nameConduction);
        return this;
    }

    public void setNameConduction(String nameConduction) {
        this.nameConduction = nameConduction;
    }

    public String getPositionConduction() {
        return this.positionConduction;
    }

    public Content positionConduction(String positionConduction) {
        this.setPositionConduction(positionConduction);
        return this;
    }

    public void setPositionConduction(String positionConduction) {
        this.positionConduction = positionConduction;
    }

    public String getSpecialtiesPanel() {
        return this.specialtiesPanel;
    }

    public Content specialtiesPanel(String specialtiesPanel) {
        this.setSpecialtiesPanel(specialtiesPanel);
        return this;
    }

    public void setSpecialtiesPanel(String specialtiesPanel) {
        this.specialtiesPanel = specialtiesPanel;
    }

    public String getNamePanel() {
        return this.namePanel;
    }

    public Content namePanel(String namePanel) {
        this.setNamePanel(namePanel);
        return this;
    }

    public void setNamePanel(String namePanel) {
        this.namePanel = namePanel;
    }

    public String getTitleSubject() {
        return this.titleSubject;
    }

    public Content titleSubject(String titleSubject) {
        this.setTitleSubject(titleSubject);
        return this;
    }

    public void setTitleSubject(String titleSubject) {
        this.titleSubject = titleSubject;
    }

    public String getBiblicalPassage() {
        return this.biblicalPassage;
    }

    public Content biblicalPassage(String biblicalPassage) {
        this.setBiblicalPassage(biblicalPassage);
        return this;
    }

    public void setBiblicalPassage(String biblicalPassage) {
        this.biblicalPassage = biblicalPassage;
    }

    public String getSynopsisDescription() {
        return this.synopsisDescription;
    }

    public Content synopsisDescription(String synopsisDescription) {
        this.setSynopsisDescription(synopsisDescription);
        return this;
    }

    public void setSynopsisDescription(String synopsisDescription) {
        this.synopsisDescription = synopsisDescription;
    }

    public String getStoryline() {
        return this.storyline;
    }

    public Content storyline(String storyline) {
        this.setStoryline(storyline);
        return this;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String getMediaContent() {
        return this.mediaContent;
    }

    public Content mediaContent(String mediaContent) {
        this.setMediaContent(mediaContent);
        return this;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent;
    }

    public Duration getDurationOriginal() {
        return this.durationOriginal;
    }

    public Content durationOriginal(Duration durationOriginal) {
        this.setDurationOriginal(durationOriginal);
        return this;
    }

    public void setDurationOriginal(Duration durationOriginal) {
        this.durationOriginal = durationOriginal;
    }

    public Duration getDurationEdited() {
        return this.durationEdited;
    }

    public Content durationEdited(Duration durationEdited) {
        this.setDurationEdited(durationEdited);
        return this;
    }

    public void setDurationEdited(Duration durationEdited) {
        this.durationEdited = durationEdited;
    }

    public String getScenography() {
        return this.scenography;
    }

    public Content scenography(String scenography) {
        this.setScenography(scenography);
        return this;
    }

    public void setScenography(String scenography) {
        this.scenography = scenography;
    }

    public String getLocation() {
        return this.location;
    }

    public Content location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return this.city;
    }

    public Content city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartmentState() {
        return this.departmentState;
    }

    public Content departmentState(String departmentState) {
        this.setDepartmentState(departmentState);
        return this;
    }

    public void setDepartmentState(String departmentState) {
        this.departmentState = departmentState;
    }

    public String getProducer() {
        return this.producer;
    }

    public Content producer(String producer) {
        this.setProducer(producer);
        return this;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProductionAssistant() {
        return this.productionAssistant;
    }

    public Content productionAssistant(String productionAssistant) {
        this.setProductionAssistant(productionAssistant);
        return this;
    }

    public void setProductionAssistant(String productionAssistant) {
        this.productionAssistant = productionAssistant;
    }

    public String getOperatorVTRPlayOut() {
        return this.operatorVTRPlayOut;
    }

    public Content operatorVTRPlayOut(String operatorVTRPlayOut) {
        this.setOperatorVTRPlayOut(operatorVTRPlayOut);
        return this;
    }

    public void setOperatorVTRPlayOut(String operatorVTRPlayOut) {
        this.operatorVTRPlayOut = operatorVTRPlayOut;
    }

    public String getProductionCredits() {
        return this.productionCredits;
    }

    public Content productionCredits(String productionCredits) {
        this.setProductionCredits(productionCredits);
        return this;
    }

    public void setProductionCredits(String productionCredits) {
        this.productionCredits = productionCredits;
    }

    public String getCastActors() {
        return this.castActors;
    }

    public Content castActors(String castActors) {
        this.setCastActors(castActors);
        return this;
    }

    public void setCastActors(String castActors) {
        this.castActors = castActors;
    }

    public String getArchiveStatus() {
        return this.archiveStatus;
    }

    public Content archiveStatus(String archiveStatus) {
        this.setArchiveStatus(archiveStatus);
        return this;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public String getInterpreterTranslator() {
        return this.interpreterTranslator;
    }

    public Content interpreterTranslator(String interpreterTranslator) {
        this.setInterpreterTranslator(interpreterTranslator);
        return this;
    }

    public void setInterpreterTranslator(String interpreterTranslator) {
        this.interpreterTranslator = interpreterTranslator;
    }

    public String getDubbing() {
        return this.dubbing;
    }

    public Content dubbing(String dubbing) {
        this.setDubbing(dubbing);
        return this;
    }

    public void setDubbing(String dubbing) {
        this.dubbing = dubbing;
    }

    public String getSubtitleCC() {
        return this.subtitleCC;
    }

    public Content subtitleCC(String subtitleCC) {
        this.setSubtitleCC(subtitleCC);
        return this;
    }

    public void setSubtitleCC(String subtitleCC) {
        this.subtitleCC = subtitleCC;
    }

    public String getCataloguerOriginal() {
        return this.cataloguerOriginal;
    }

    public Content cataloguerOriginal(String cataloguerOriginal) {
        this.setCataloguerOriginal(cataloguerOriginal);
        return this;
    }

    public void setCataloguerOriginal(String cataloguerOriginal) {
        this.cataloguerOriginal = cataloguerOriginal;
    }

    public String getCataloguerEdited() {
        return this.cataloguerEdited;
    }

    public Content cataloguerEdited(String cataloguerEdited) {
        this.setCataloguerEdited(cataloguerEdited);
        return this;
    }

    public void setCataloguerEdited(String cataloguerEdited) {
        this.cataloguerEdited = cataloguerEdited;
    }

    public String getPostproductionEditor() {
        return this.postproductionEditor;
    }

    public Content postproductionEditor(String postproductionEditor) {
        this.setPostproductionEditor(postproductionEditor);
        return this;
    }

    public void setPostproductionEditor(String postproductionEditor) {
        this.postproductionEditor = postproductionEditor;
    }

    public String getOperatorIngestion() {
        return this.operatorIngestion;
    }

    public Content operatorIngestion(String operatorIngestion) {
        this.setOperatorIngestion(operatorIngestion);
        return this;
    }

    public void setOperatorIngestion(String operatorIngestion) {
        this.operatorIngestion = operatorIngestion;
    }

    public Boolean getServiceOTT() {
        return this.serviceOTT;
    }

    public Content serviceOTT(Boolean serviceOTT) {
        this.setServiceOTT(serviceOTT);
        return this;
    }

    public void setServiceOTT(Boolean serviceOTT) {
        this.serviceOTT = serviceOTT;
    }

    public Boolean getArchivadoLTOOriginal() {
        return this.archivadoLTOOriginal;
    }

    public Content archivadoLTOOriginal(Boolean archivadoLTOOriginal) {
        this.setArchivadoLTOOriginal(archivadoLTOOriginal);
        return this;
    }

    public void setArchivadoLTOOriginal(Boolean archivadoLTOOriginal) {
        this.archivadoLTOOriginal = archivadoLTOOriginal;
    }

    public Boolean getArchivadoLTOEdited() {
        return this.archivadoLTOEdited;
    }

    public Content archivadoLTOEdited(Boolean archivadoLTOEdited) {
        this.setArchivadoLTOEdited(archivadoLTOEdited);
        return this;
    }

    public void setArchivadoLTOEdited(Boolean archivadoLTOEdited) {
        this.archivadoLTOEdited = archivadoLTOEdited;
    }

    public String getObservations() {
        return this.observations;
    }

    public Content observations(String observations) {
        this.setObservations(observations);
        return this;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getRightsManagement() {
        return this.rightsManagement;
    }

    public Content rightsManagement(String rightsManagement) {
        this.setRightsManagement(rightsManagement);
        return this;
    }

    public void setRightsManagement(String rightsManagement) {
        this.rightsManagement = rightsManagement;
    }

    public String getCataloguerProduction() {
        return this.cataloguerProduction;
    }

    public Content cataloguerProduction(String cataloguerProduction) {
        this.setCataloguerProduction(cataloguerProduction);
        return this;
    }

    public void setCataloguerProduction(String cataloguerProduction) {
        this.cataloguerProduction = cataloguerProduction;
    }

    public String getCataloguerIngest() {
        return this.cataloguerIngest;
    }

    public Content cataloguerIngest(String cataloguerIngest) {
        this.setCataloguerIngest(cataloguerIngest);
        return this;
    }

    public void setCataloguerIngest(String cataloguerIngest) {
        this.cataloguerIngest = cataloguerIngest;
    }

    public String getCataloguerMaster() {
        return this.cataloguerMaster;
    }

    public Content cataloguerMaster(String cataloguerMaster) {
        this.setCataloguerMaster(cataloguerMaster);
        return this;
    }

    public void setCataloguerMaster(String cataloguerMaster) {
        this.cataloguerMaster = cataloguerMaster;
    }

    public String getUrlEdition() {
        return this.urlEdition;
    }

    public Content urlEdition(String urlEdition) {
        this.setUrlEdition(urlEdition);
        return this;
    }

    public void setUrlEdition(String urlEdition) {
        this.urlEdition = urlEdition;
    }

    public String getUrlOriginal() {
        return this.urlOriginal;
    }

    public Content urlOriginal(String urlOriginal) {
        this.setUrlOriginal(urlOriginal);
        return this;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public Integer getCreateById() {
        return this.createById;
    }

    public Content createById(Integer createById) {
        this.setCreateById(createById);
        return this;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }

    public ZonedDateTime getCreatedDate() {
        return this.createdDate;
    }

    public Content createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLastModifiedById() {
        return this.lastModifiedById;
    }

    public Content lastModifiedById(Integer lastModifiedById) {
        this.setLastModifiedById(lastModifiedById);
        return this;
    }

    public void setLastModifiedById(Integer lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public ZonedDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public Content lastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<ContentTag> getCatTags() {
        return this.catTags;
    }

    public void setCatTags(Set<ContentTag> contentTags) {
        if (this.catTags != null) {
            this.catTags.forEach(i -> i.setContentId(null));
        }
        if (contentTags != null) {
            contentTags.forEach(i -> i.setContentId(this));
        }
        this.catTags = contentTags;
    }

    public Content catTags(Set<ContentTag> contentTags) {
        this.setCatTags(contentTags);
        return this;
    }

    public Content addCatTags(ContentTag contentTag) {
        this.catTags.add(contentTag);
        contentTag.setContentId(this);
        return this;
    }

    public Content removeCatTags(ContentTag contentTag) {
        this.catTags.remove(contentTag);
        contentTag.setContentId(null);
        return this;
    }

    public Catalog getCatContentType() {
        return this.catContentType;
    }

    public void setCatContentType(Catalog catalog) {
        this.catContentType = catalog;
    }

    public Content catContentType(Catalog catalog) {
        this.setCatContentType(catalog);
        return this;
    }

    public Catalog getCatTypeConduction() {
        return this.catTypeConduction;
    }

    public void setCatTypeConduction(Catalog catalog) {
        this.catTypeConduction = catalog;
    }

    public Content catTypeConduction(Catalog catalog) {
        this.setCatTypeConduction(catalog);
        return this;
    }

    public Catalog getCatProgram() {
        return this.catProgram;
    }

    public void setCatProgram(Catalog catalog) {
        this.catProgram = catalog;
    }

    public Content catProgram(Catalog catalog) {
        this.setCatProgram(catalog);
        return this;
    }

    public Catalog getCatShiftRecording() {
        return this.catShiftRecording;
    }

    public void setCatShiftRecording(Catalog catalog) {
        this.catShiftRecording = catalog;
    }

    public Content catShiftRecording(Catalog catalog) {
        this.setCatShiftRecording(catalog);
        return this;
    }

    public Catalog getCatTargetAudience() {
        return this.catTargetAudience;
    }

    public void setCatTargetAudience(Catalog catalog) {
        this.catTargetAudience = catalog;
    }

    public Content catTargetAudience(Catalog catalog) {
        this.setCatTargetAudience(catalog);
        return this;
    }

    public Catalog getCatCountry() {
        return this.catCountry;
    }

    public void setCatCountry(Catalog catalog) {
        this.catCountry = catalog;
    }

    public Content catCountry(Catalog catalog) {
        this.setCatCountry(catalog);
        return this;
    }

    public Catalog getCatProductionCompany() {
        return this.catProductionCompany;
    }

    public void setCatProductionCompany(Catalog catalog) {
        this.catProductionCompany = catalog;
    }

    public Content catProductionCompany(Catalog catalog) {
        this.setCatProductionCompany(catalog);
        return this;
    }

    public Catalog getCatArchivalCollection() {
        return this.catArchivalCollection;
    }

    public void setCatArchivalCollection(Catalog catalog) {
        this.catArchivalCollection = catalog;
    }

    public Content catArchivalCollection(Catalog catalog) {
        this.setCatArchivalCollection(catalog);
        return this;
    }

    public Catalog getCatOriginalLanguage() {
        return this.catOriginalLanguage;
    }

    public void setCatOriginalLanguage(Catalog catalog) {
        this.catOriginalLanguage = catalog;
    }

    public Content catOriginalLanguage(Catalog catalog) {
        this.setCatOriginalLanguage(catalog);
        return this;
    }

    public Catalog getCatInterpreterLanguage() {
        return this.catInterpreterLanguage;
    }

    public void setCatInterpreterLanguage(Catalog catalog) {
        this.catInterpreterLanguage = catalog;
    }

    public Content catInterpreterLanguage(Catalog catalog) {
        this.setCatInterpreterLanguage(catalog);
        return this;
    }

    public Catalog getCatDubbingLanguage() {
        return this.catDubbingLanguage;
    }

    public void setCatDubbingLanguage(Catalog catalog) {
        this.catDubbingLanguage = catalog;
    }

    public Content catDubbingLanguage(Catalog catalog) {
        this.setCatDubbingLanguage(catalog);
        return this;
    }

    public Catalog getCatSubtitleLanguage() {
        return this.catSubtitleLanguage;
    }

    public void setCatSubtitleLanguage(Catalog catalog) {
        this.catSubtitleLanguage = catalog;
    }

    public Content catSubtitleLanguage(Catalog catalog) {
        this.setCatSubtitleLanguage(catalog);
        return this;
    }

    public Catalog getCatTvCensorship() {
        return this.catTvCensorship;
    }

    public void setCatTvCensorship(Catalog catalog) {
        this.catTvCensorship = catalog;
    }

    public Content catTvCensorship(Catalog catalog) {
        this.setCatTvCensorship(catalog);
        return this;
    }

    public Catalog getCatClassificationCategory() {
        return this.catClassificationCategory;
    }

    public void setCatClassificationCategory(Catalog catalog) {
        this.catClassificationCategory = catalog;
    }

    public Content catClassificationCategory(Catalog catalog) {
        this.setCatClassificationCategory(catalog);
        return this;
    }

    public Catalog getCatGenreProgram() {
        return this.catGenreProgram;
    }

    public void setCatGenreProgram(Catalog catalog) {
        this.catGenreProgram = catalog;
    }

    public Content catGenreProgram(Catalog catalog) {
        this.setCatGenreProgram(catalog);
        return this;
    }

    public Catalog getCatFormatProgram() {
        return this.catFormatProgram;
    }

    public void setCatFormatProgram(Catalog catalog) {
        this.catFormatProgram = catalog;
    }

    public Content catFormatProgram(Catalog catalog) {
        this.setCatFormatProgram(catalog);
        return this;
    }

    public Catalog getCatResolutionOriginal() {
        return this.catResolutionOriginal;
    }

    public void setCatResolutionOriginal(Catalog catalog) {
        this.catResolutionOriginal = catalog;
    }

    public Content catResolutionOriginal(Catalog catalog) {
        this.setCatResolutionOriginal(catalog);
        return this;
    }

    public Catalog getCatResolutionEdited() {
        return this.catResolutionEdited;
    }

    public void setCatResolutionEdited(Catalog catalog) {
        this.catResolutionEdited = catalog;
    }

    public Content catResolutionEdited(Catalog catalog) {
        this.setCatResolutionEdited(catalog);
        return this;
    }

    public Catalog getCatCatalogingLevelOriginal() {
        return this.catCatalogingLevelOriginal;
    }

    public void setCatCatalogingLevelOriginal(Catalog catalog) {
        this.catCatalogingLevelOriginal = catalog;
    }

    public Content catCatalogingLevelOriginal(Catalog catalog) {
        this.setCatCatalogingLevelOriginal(catalog);
        return this;
    }

    public Catalog getCatCatalogingLevelEdited() {
        return this.catCatalogingLevelEdited;
    }

    public void setCatCatalogingLevelEdited(Catalog catalog) {
        this.catCatalogingLevelEdited = catalog;
    }

    public Content catCatalogingLevelEdited(Catalog catalog) {
        this.setCatCatalogingLevelEdited(catalog);
        return this;
    }

    public Catalog getCatVideoQuality() {
        return this.catVideoQuality;
    }

    public void setCatVideoQuality(Catalog catalog) {
        this.catVideoQuality = catalog;
    }

    public Content catVideoQuality(Catalog catalog) {
        this.setCatVideoQuality(catalog);
        return this;
    }

    public Catalog getCatAudioQuality() {
        return this.catAudioQuality;
    }

    public void setCatAudioQuality(Catalog catalog) {
        this.catAudioQuality = catalog;
    }

    public Content catAudioQuality(Catalog catalog) {
        this.setCatAudioQuality(catalog);
        return this;
    }

    public Catalog getCatMusicalGroup() {
        return this.catMusicalGroup;
    }

    public void setCatMusicalGroup(Catalog catalog) {
        this.catMusicalGroup = catalog;
    }

    public Content catMusicalGroup(Catalog catalog) {
        this.setCatMusicalGroup(catalog);
        return this;
    }

    public Catalog getCatMusicalGenre() {
        return this.catMusicalGenre;
    }

    public void setCatMusicalGenre(Catalog catalog) {
        this.catMusicalGenre = catalog;
    }

    public Content catMusicalGenre(Catalog catalog) {
        this.setCatMusicalGenre(catalog);
        return this;
    }

    public Catalog getCatApproved() {
        return this.catApproved;
    }

    public void setCatApproved(Catalog catalog) {
        this.catApproved = catalog;
    }

    public Content catApproved(Catalog catalog) {
        this.setCatApproved(catalog);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Content)) {
            return false;
        }
        return id != null && id.equals(((Content) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Content{" +
            "id=" + getId() +
            ", recordingDate='" + getRecordingDate() + "'" +
            ", editionDate='" + getEditionDate() + "'" +
            ", signatureOriginal='" + getSignatureOriginal() + "'" +
            ", signatureEdited='" + getSignatureEdited() + "'" +
            ", eventReason='" + getEventReason() + "'" +
            ", eventSlogan='" + getEventSlogan() + "'" +
            ", nameConduction='" + getNameConduction() + "'" +
            ", positionConduction='" + getPositionConduction() + "'" +
            ", specialtiesPanel='" + getSpecialtiesPanel() + "'" +
            ", namePanel='" + getNamePanel() + "'" +
            ", titleSubject='" + getTitleSubject() + "'" +
            ", biblicalPassage='" + getBiblicalPassage() + "'" +
            ", synopsisDescription='" + getSynopsisDescription() + "'" +
            ", storyline='" + getStoryline() + "'" +
            ", mediaContent='" + getMediaContent() + "'" +
            ", durationOriginal='" + getDurationOriginal() + "'" +
            ", durationEdited='" + getDurationEdited() + "'" +
            ", scenography='" + getScenography() + "'" +
            ", location='" + getLocation() + "'" +
            ", city='" + getCity() + "'" +
            ", departmentState='" + getDepartmentState() + "'" +
            ", producer='" + getProducer() + "'" +
            ", productionAssistant='" + getProductionAssistant() + "'" +
            ", operatorVTRPlayOut='" + getOperatorVTRPlayOut() + "'" +
            ", productionCredits='" + getProductionCredits() + "'" +
            ", castActors='" + getCastActors() + "'" +
            ", archiveStatus='" + getArchiveStatus() + "'" +
            ", interpreterTranslator='" + getInterpreterTranslator() + "'" +
            ", dubbing='" + getDubbing() + "'" +
            ", subtitleCC='" + getSubtitleCC() + "'" +
            ", cataloguerOriginal='" + getCataloguerOriginal() + "'" +
            ", cataloguerEdited='" + getCataloguerEdited() + "'" +
            ", postproductionEditor='" + getPostproductionEditor() + "'" +
            ", operatorIngestion='" + getOperatorIngestion() + "'" +
            ", serviceOTT='" + getServiceOTT() + "'" +
            ", archivadoLTOOriginal='" + getArchivadoLTOOriginal() + "'" +
            ", archivadoLTOEdited='" + getArchivadoLTOEdited() + "'" +
            ", observations='" + getObservations() + "'" +
            ", rightsManagement='" + getRightsManagement() + "'" +
            ", cataloguerProduction='" + getCataloguerProduction() + "'" +
            ", cataloguerIngest='" + getCataloguerIngest() + "'" +
            ", cataloguerMaster='" + getCataloguerMaster() + "'" +
            ", urlEdition='" + getUrlEdition() + "'" +
            ", urlOriginal='" + getUrlOriginal() + "'" +
            ", createById=" + getCreateById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedById=" + getLastModifiedById() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
