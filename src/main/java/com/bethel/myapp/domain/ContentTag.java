package com.bethel.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ContentTag.
 */
@Entity
@Table(name = "content_tag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContentTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "create_by_id")
    private Integer createById;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "last_modified_by_id")
    private Integer lastModifiedById;

    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(
        value = {
            "catTags",
            "catContentType",
            "catTypeConduction",
            "catProgram",
            "catShiftRecording",
            "catTargetAudience",
            "catCountry",
            "catProductionCompany",
            "catArchivalCollection",
            "catOriginalLanguage",
            "catInterpreterLanguage",
            "catDubbingLanguage",
            "catSubtitleLanguage",
            "catTvCensorship",
            "catClassificationCategory",
            "catGenreProgram",
            "catFormatProgram",
            "catResolutionOriginal",
            "catResolutionEdited",
            "catCatalogingLevelOriginal",
            "catCatalogingLevelEdited",
            "catVideoQuality",
            "catAudioQuality",
            "catMusicalGroup",
            "catMusicalGenre",
            "catApproved",
        },
        allowSetters = true
    )
    private Content contentId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catalogId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ContentTag id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreateById() {
        return this.createById;
    }

    public ContentTag createById(Integer createById) {
        this.setCreateById(createById);
        return this;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }

    public ZonedDateTime getCreatedDate() {
        return this.createdDate;
    }

    public ContentTag createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLastModifiedById() {
        return this.lastModifiedById;
    }

    public ContentTag lastModifiedById(Integer lastModifiedById) {
        this.setLastModifiedById(lastModifiedById);
        return this;
    }

    public void setLastModifiedById(Integer lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public ZonedDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public ContentTag lastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Content getContentId() {
        return this.contentId;
    }

    public void setContentId(Content content) {
        this.contentId = content;
    }

    public ContentTag contentId(Content content) {
        this.setContentId(content);
        return this;
    }

    public Catalog getCatalogId() {
        return this.catalogId;
    }

    public void setCatalogId(Catalog catalog) {
        this.catalogId = catalog;
    }

    public ContentTag catalogId(Catalog catalog) {
        this.setCatalogId(catalog);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContentTag)) {
            return false;
        }
        return id != null && id.equals(((ContentTag) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContentTag{" +
            "id=" + getId() +
            ", createById=" + getCreateById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedById=" + getLastModifiedById() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
