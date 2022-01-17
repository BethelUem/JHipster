package com.bethel.myapp.service.dto;

import java.io.Serializable;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.bethel.myapp.domain.Content} entity.
 */
public class ContentDTO implements Serializable {

    private Long id;

    private ZonedDateTime recordingDate;

    private ZonedDateTime editionDate;

    private String signatureOriginal;

    private String signatureEdited;

    private String eventReason;

    private String eventSlogan;

    private String nameConduction;

    private String positionConduction;

    private String specialtiesPanel;

    private String namePanel;

    private String titleSubject;

    private String biblicalPassage;

    private String synopsisDescription;

    private String storyline;

    private String mediaContent;

    private Duration durationOriginal;

    private Duration durationEdited;

    private String scenography;

    private String location;

    private String city;

    private String departmentState;

    private String producer;

    private String productionAssistant;

    private String operatorVTRPlayOut;

    private String productionCredits;

    private String castActors;

    private String archiveStatus;

    private String interpreterTranslator;

    private String dubbing;

    private String subtitleCC;

    private String cataloguerOriginal;

    private String cataloguerEdited;

    private String postproductionEditor;

    private String operatorIngestion;

    private Boolean serviceOTT;

    private Boolean archivadoLTOOriginal;

    private Boolean archivadoLTOEdited;

    private String observations;

    private String rightsManagement;

    private String cataloguerProduction;

    private String cataloguerIngest;

    private String cataloguerMaster;

    private String urlEdition;

    private String urlOriginal;

    private Integer createById;

    private ZonedDateTime createdDate;

    private Integer lastModifiedById;

    private ZonedDateTime lastModifiedDate;

    private CatalogDTO catContentType;

    private CatalogDTO catTypeConduction;

    private CatalogDTO catProgram;

    private CatalogDTO catShiftRecording;

    private CatalogDTO catTargetAudience;

    private CatalogDTO catCountry;

    private CatalogDTO catProductionCompany;

    private CatalogDTO catArchivalCollection;

    private CatalogDTO catOriginalLanguage;

    private CatalogDTO catInterpreterLanguage;

    private CatalogDTO catDubbingLanguage;

    private CatalogDTO catSubtitleLanguage;

    private CatalogDTO catTvCensorship;

    private CatalogDTO catClassificationCategory;

    private CatalogDTO catGenreProgram;

    private CatalogDTO catFormatProgram;

    private CatalogDTO catResolutionOriginal;

    private CatalogDTO catResolutionEdited;

    private CatalogDTO catCatalogingLevelOriginal;

    private CatalogDTO catCatalogingLevelEdited;

    private CatalogDTO catVideoQuality;

    private CatalogDTO catAudioQuality;

    private CatalogDTO catMusicalGroup;

    private CatalogDTO catMusicalGenre;

    private CatalogDTO catApproved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRecordingDate() {
        return recordingDate;
    }

    public void setRecordingDate(ZonedDateTime recordingDate) {
        this.recordingDate = recordingDate;
    }

    public ZonedDateTime getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(ZonedDateTime editionDate) {
        this.editionDate = editionDate;
    }

    public String getSignatureOriginal() {
        return signatureOriginal;
    }

    public void setSignatureOriginal(String signatureOriginal) {
        this.signatureOriginal = signatureOriginal;
    }

    public String getSignatureEdited() {
        return signatureEdited;
    }

    public void setSignatureEdited(String signatureEdited) {
        this.signatureEdited = signatureEdited;
    }

    public String getEventReason() {
        return eventReason;
    }

    public void setEventReason(String eventReason) {
        this.eventReason = eventReason;
    }

    public String getEventSlogan() {
        return eventSlogan;
    }

    public void setEventSlogan(String eventSlogan) {
        this.eventSlogan = eventSlogan;
    }

    public String getNameConduction() {
        return nameConduction;
    }

    public void setNameConduction(String nameConduction) {
        this.nameConduction = nameConduction;
    }

    public String getPositionConduction() {
        return positionConduction;
    }

    public void setPositionConduction(String positionConduction) {
        this.positionConduction = positionConduction;
    }

    public String getSpecialtiesPanel() {
        return specialtiesPanel;
    }

    public void setSpecialtiesPanel(String specialtiesPanel) {
        this.specialtiesPanel = specialtiesPanel;
    }

    public String getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(String namePanel) {
        this.namePanel = namePanel;
    }

    public String getTitleSubject() {
        return titleSubject;
    }

    public void setTitleSubject(String titleSubject) {
        this.titleSubject = titleSubject;
    }

    public String getBiblicalPassage() {
        return biblicalPassage;
    }

    public void setBiblicalPassage(String biblicalPassage) {
        this.biblicalPassage = biblicalPassage;
    }

    public String getSynopsisDescription() {
        return synopsisDescription;
    }

    public void setSynopsisDescription(String synopsisDescription) {
        this.synopsisDescription = synopsisDescription;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent;
    }

    public Duration getDurationOriginal() {
        return durationOriginal;
    }

    public void setDurationOriginal(Duration durationOriginal) {
        this.durationOriginal = durationOriginal;
    }

    public Duration getDurationEdited() {
        return durationEdited;
    }

    public void setDurationEdited(Duration durationEdited) {
        this.durationEdited = durationEdited;
    }

    public String getScenography() {
        return scenography;
    }

    public void setScenography(String scenography) {
        this.scenography = scenography;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartmentState() {
        return departmentState;
    }

    public void setDepartmentState(String departmentState) {
        this.departmentState = departmentState;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProductionAssistant() {
        return productionAssistant;
    }

    public void setProductionAssistant(String productionAssistant) {
        this.productionAssistant = productionAssistant;
    }

    public String getOperatorVTRPlayOut() {
        return operatorVTRPlayOut;
    }

    public void setOperatorVTRPlayOut(String operatorVTRPlayOut) {
        this.operatorVTRPlayOut = operatorVTRPlayOut;
    }

    public String getProductionCredits() {
        return productionCredits;
    }

    public void setProductionCredits(String productionCredits) {
        this.productionCredits = productionCredits;
    }

    public String getCastActors() {
        return castActors;
    }

    public void setCastActors(String castActors) {
        this.castActors = castActors;
    }

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public String getInterpreterTranslator() {
        return interpreterTranslator;
    }

    public void setInterpreterTranslator(String interpreterTranslator) {
        this.interpreterTranslator = interpreterTranslator;
    }

    public String getDubbing() {
        return dubbing;
    }

    public void setDubbing(String dubbing) {
        this.dubbing = dubbing;
    }

    public String getSubtitleCC() {
        return subtitleCC;
    }

    public void setSubtitleCC(String subtitleCC) {
        this.subtitleCC = subtitleCC;
    }

    public String getCataloguerOriginal() {
        return cataloguerOriginal;
    }

    public void setCataloguerOriginal(String cataloguerOriginal) {
        this.cataloguerOriginal = cataloguerOriginal;
    }

    public String getCataloguerEdited() {
        return cataloguerEdited;
    }

    public void setCataloguerEdited(String cataloguerEdited) {
        this.cataloguerEdited = cataloguerEdited;
    }

    public String getPostproductionEditor() {
        return postproductionEditor;
    }

    public void setPostproductionEditor(String postproductionEditor) {
        this.postproductionEditor = postproductionEditor;
    }

    public String getOperatorIngestion() {
        return operatorIngestion;
    }

    public void setOperatorIngestion(String operatorIngestion) {
        this.operatorIngestion = operatorIngestion;
    }

    public Boolean getServiceOTT() {
        return serviceOTT;
    }

    public void setServiceOTT(Boolean serviceOTT) {
        this.serviceOTT = serviceOTT;
    }

    public Boolean getArchivadoLTOOriginal() {
        return archivadoLTOOriginal;
    }

    public void setArchivadoLTOOriginal(Boolean archivadoLTOOriginal) {
        this.archivadoLTOOriginal = archivadoLTOOriginal;
    }

    public Boolean getArchivadoLTOEdited() {
        return archivadoLTOEdited;
    }

    public void setArchivadoLTOEdited(Boolean archivadoLTOEdited) {
        this.archivadoLTOEdited = archivadoLTOEdited;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getRightsManagement() {
        return rightsManagement;
    }

    public void setRightsManagement(String rightsManagement) {
        this.rightsManagement = rightsManagement;
    }

    public String getCataloguerProduction() {
        return cataloguerProduction;
    }

    public void setCataloguerProduction(String cataloguerProduction) {
        this.cataloguerProduction = cataloguerProduction;
    }

    public String getCataloguerIngest() {
        return cataloguerIngest;
    }

    public void setCataloguerIngest(String cataloguerIngest) {
        this.cataloguerIngest = cataloguerIngest;
    }

    public String getCataloguerMaster() {
        return cataloguerMaster;
    }

    public void setCataloguerMaster(String cataloguerMaster) {
        this.cataloguerMaster = cataloguerMaster;
    }

    public String getUrlEdition() {
        return urlEdition;
    }

    public void setUrlEdition(String urlEdition) {
        this.urlEdition = urlEdition;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public Integer getCreateById() {
        return createById;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(Integer lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public CatalogDTO getCatContentType() {
        return catContentType;
    }

    public void setCatContentType(CatalogDTO catContentType) {
        this.catContentType = catContentType;
    }

    public CatalogDTO getCatTypeConduction() {
        return catTypeConduction;
    }

    public void setCatTypeConduction(CatalogDTO catTypeConduction) {
        this.catTypeConduction = catTypeConduction;
    }

    public CatalogDTO getCatProgram() {
        return catProgram;
    }

    public void setCatProgram(CatalogDTO catProgram) {
        this.catProgram = catProgram;
    }

    public CatalogDTO getCatShiftRecording() {
        return catShiftRecording;
    }

    public void setCatShiftRecording(CatalogDTO catShiftRecording) {
        this.catShiftRecording = catShiftRecording;
    }

    public CatalogDTO getCatTargetAudience() {
        return catTargetAudience;
    }

    public void setCatTargetAudience(CatalogDTO catTargetAudience) {
        this.catTargetAudience = catTargetAudience;
    }

    public CatalogDTO getCatCountry() {
        return catCountry;
    }

    public void setCatCountry(CatalogDTO catCountry) {
        this.catCountry = catCountry;
    }

    public CatalogDTO getCatProductionCompany() {
        return catProductionCompany;
    }

    public void setCatProductionCompany(CatalogDTO catProductionCompany) {
        this.catProductionCompany = catProductionCompany;
    }

    public CatalogDTO getCatArchivalCollection() {
        return catArchivalCollection;
    }

    public void setCatArchivalCollection(CatalogDTO catArchivalCollection) {
        this.catArchivalCollection = catArchivalCollection;
    }

    public CatalogDTO getCatOriginalLanguage() {
        return catOriginalLanguage;
    }

    public void setCatOriginalLanguage(CatalogDTO catOriginalLanguage) {
        this.catOriginalLanguage = catOriginalLanguage;
    }

    public CatalogDTO getCatInterpreterLanguage() {
        return catInterpreterLanguage;
    }

    public void setCatInterpreterLanguage(CatalogDTO catInterpreterLanguage) {
        this.catInterpreterLanguage = catInterpreterLanguage;
    }

    public CatalogDTO getCatDubbingLanguage() {
        return catDubbingLanguage;
    }

    public void setCatDubbingLanguage(CatalogDTO catDubbingLanguage) {
        this.catDubbingLanguage = catDubbingLanguage;
    }

    public CatalogDTO getCatSubtitleLanguage() {
        return catSubtitleLanguage;
    }

    public void setCatSubtitleLanguage(CatalogDTO catSubtitleLanguage) {
        this.catSubtitleLanguage = catSubtitleLanguage;
    }

    public CatalogDTO getCatTvCensorship() {
        return catTvCensorship;
    }

    public void setCatTvCensorship(CatalogDTO catTvCensorship) {
        this.catTvCensorship = catTvCensorship;
    }

    public CatalogDTO getCatClassificationCategory() {
        return catClassificationCategory;
    }

    public void setCatClassificationCategory(CatalogDTO catClassificationCategory) {
        this.catClassificationCategory = catClassificationCategory;
    }

    public CatalogDTO getCatGenreProgram() {
        return catGenreProgram;
    }

    public void setCatGenreProgram(CatalogDTO catGenreProgram) {
        this.catGenreProgram = catGenreProgram;
    }

    public CatalogDTO getCatFormatProgram() {
        return catFormatProgram;
    }

    public void setCatFormatProgram(CatalogDTO catFormatProgram) {
        this.catFormatProgram = catFormatProgram;
    }

    public CatalogDTO getCatResolutionOriginal() {
        return catResolutionOriginal;
    }

    public void setCatResolutionOriginal(CatalogDTO catResolutionOriginal) {
        this.catResolutionOriginal = catResolutionOriginal;
    }

    public CatalogDTO getCatResolutionEdited() {
        return catResolutionEdited;
    }

    public void setCatResolutionEdited(CatalogDTO catResolutionEdited) {
        this.catResolutionEdited = catResolutionEdited;
    }

    public CatalogDTO getCatCatalogingLevelOriginal() {
        return catCatalogingLevelOriginal;
    }

    public void setCatCatalogingLevelOriginal(CatalogDTO catCatalogingLevelOriginal) {
        this.catCatalogingLevelOriginal = catCatalogingLevelOriginal;
    }

    public CatalogDTO getCatCatalogingLevelEdited() {
        return catCatalogingLevelEdited;
    }

    public void setCatCatalogingLevelEdited(CatalogDTO catCatalogingLevelEdited) {
        this.catCatalogingLevelEdited = catCatalogingLevelEdited;
    }

    public CatalogDTO getCatVideoQuality() {
        return catVideoQuality;
    }

    public void setCatVideoQuality(CatalogDTO catVideoQuality) {
        this.catVideoQuality = catVideoQuality;
    }

    public CatalogDTO getCatAudioQuality() {
        return catAudioQuality;
    }

    public void setCatAudioQuality(CatalogDTO catAudioQuality) {
        this.catAudioQuality = catAudioQuality;
    }

    public CatalogDTO getCatMusicalGroup() {
        return catMusicalGroup;
    }

    public void setCatMusicalGroup(CatalogDTO catMusicalGroup) {
        this.catMusicalGroup = catMusicalGroup;
    }

    public CatalogDTO getCatMusicalGenre() {
        return catMusicalGenre;
    }

    public void setCatMusicalGenre(CatalogDTO catMusicalGenre) {
        this.catMusicalGenre = catMusicalGenre;
    }

    public CatalogDTO getCatApproved() {
        return catApproved;
    }

    public void setCatApproved(CatalogDTO catApproved) {
        this.catApproved = catApproved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContentDTO)) {
            return false;
        }

        ContentDTO contentDTO = (ContentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContentDTO{" +
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
            ", catContentType=" + getCatContentType() +
            ", catTypeConduction=" + getCatTypeConduction() +
            ", catProgram=" + getCatProgram() +
            ", catShiftRecording=" + getCatShiftRecording() +
            ", catTargetAudience=" + getCatTargetAudience() +
            ", catCountry=" + getCatCountry() +
            ", catProductionCompany=" + getCatProductionCompany() +
            ", catArchivalCollection=" + getCatArchivalCollection() +
            ", catOriginalLanguage=" + getCatOriginalLanguage() +
            ", catInterpreterLanguage=" + getCatInterpreterLanguage() +
            ", catDubbingLanguage=" + getCatDubbingLanguage() +
            ", catSubtitleLanguage=" + getCatSubtitleLanguage() +
            ", catTvCensorship=" + getCatTvCensorship() +
            ", catClassificationCategory=" + getCatClassificationCategory() +
            ", catGenreProgram=" + getCatGenreProgram() +
            ", catFormatProgram=" + getCatFormatProgram() +
            ", catResolutionOriginal=" + getCatResolutionOriginal() +
            ", catResolutionEdited=" + getCatResolutionEdited() +
            ", catCatalogingLevelOriginal=" + getCatCatalogingLevelOriginal() +
            ", catCatalogingLevelEdited=" + getCatCatalogingLevelEdited() +
            ", catVideoQuality=" + getCatVideoQuality() +
            ", catAudioQuality=" + getCatAudioQuality() +
            ", catMusicalGroup=" + getCatMusicalGroup() +
            ", catMusicalGenre=" + getCatMusicalGenre() +
            ", catApproved=" + getCatApproved() +
            "}";
    }
}
