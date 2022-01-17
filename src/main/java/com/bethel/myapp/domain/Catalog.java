package com.bethel.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Catalog.
 */
@Entity
@Table(name = "catalog")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Catalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "create_by_id")
    private Integer createById;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "last_modified_by_id")
    private Integer lastModifiedById;

    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;

    @OneToMany(mappedBy = "catalogId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentId", "catalogId" }, allowSetters = true)
    private Set<ContentTag> contentTags = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog parentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Catalog id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Catalog code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Catalog name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Catalog description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Catalog active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getCreateById() {
        return this.createById;
    }

    public Catalog createById(Integer createById) {
        this.setCreateById(createById);
        return this;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }

    public ZonedDateTime getCreatedDate() {
        return this.createdDate;
    }

    public Catalog createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLastModifiedById() {
        return this.lastModifiedById;
    }

    public Catalog lastModifiedById(Integer lastModifiedById) {
        this.setLastModifiedById(lastModifiedById);
        return this;
    }

    public void setLastModifiedById(Integer lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public ZonedDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public Catalog lastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<ContentTag> getContentTags() {
        return this.contentTags;
    }

    public void setContentTags(Set<ContentTag> contentTags) {
        if (this.contentTags != null) {
            this.contentTags.forEach(i -> i.setCatalogId(null));
        }
        if (contentTags != null) {
            contentTags.forEach(i -> i.setCatalogId(this));
        }
        this.contentTags = contentTags;
    }

    public Catalog contentTags(Set<ContentTag> contentTags) {
        this.setContentTags(contentTags);
        return this;
    }

    public Catalog addContentTag(ContentTag contentTag) {
        this.contentTags.add(contentTag);
        contentTag.setCatalogId(this);
        return this;
    }

    public Catalog removeContentTag(ContentTag contentTag) {
        this.contentTags.remove(contentTag);
        contentTag.setCatalogId(null);
        return this;
    }

    public Catalog getParentId() {
        return this.parentId;
    }

    public void setParentId(Catalog catalog) {
        this.parentId = catalog;
    }

    public Catalog parentId(Catalog catalog) {
        this.setParentId(catalog);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Catalog)) {
            return false;
        }
        return id != null && id.equals(((Catalog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Catalog{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", active='" + getActive() + "'" +
            ", createById=" + getCreateById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedById=" + getLastModifiedById() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
