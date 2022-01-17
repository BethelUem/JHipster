package com.bethel.myapp.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.bethel.myapp.domain.ContentTag} entity.
 */
public class ContentTagDTO implements Serializable {

    private Long id;

    private Integer createById;

    private ZonedDateTime createdDate;

    private Integer lastModifiedById;

    private ZonedDateTime lastModifiedDate;

    private ContentDTO contentId;

    private CatalogDTO catalogId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ContentDTO getContentId() {
        return contentId;
    }

    public void setContentId(ContentDTO contentId) {
        this.contentId = contentId;
    }

    public CatalogDTO getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(CatalogDTO catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContentTagDTO)) {
            return false;
        }

        ContentTagDTO contentTagDTO = (ContentTagDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contentTagDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContentTagDTO{" +
            "id=" + getId() +
            ", createById=" + getCreateById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedById=" + getLastModifiedById() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", contentId=" + getContentId() +
            ", catalogId=" + getCatalogId() +
            "}";
    }
}
