package com.bethel.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UserProgram.
 */
@Entity
@Table(name = "user_program")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "jhi_user")
    private Integer user;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentTags", "parentId" }, allowSetters = true)
    private Catalog catProgram;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserProgram id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUser() {
        return this.user;
    }

    public UserProgram user(Integer user) {
        this.setUser(user);
        return this;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Boolean getActive() {
        return this.active;
    }

    public UserProgram active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getCreateById() {
        return this.createById;
    }

    public UserProgram createById(Integer createById) {
        this.setCreateById(createById);
        return this;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }

    public ZonedDateTime getCreatedDate() {
        return this.createdDate;
    }

    public UserProgram createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLastModifiedById() {
        return this.lastModifiedById;
    }

    public UserProgram lastModifiedById(Integer lastModifiedById) {
        this.setLastModifiedById(lastModifiedById);
        return this;
    }

    public void setLastModifiedById(Integer lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public ZonedDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public UserProgram lastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Catalog getCatProgram() {
        return this.catProgram;
    }

    public void setCatProgram(Catalog catalog) {
        this.catProgram = catalog;
    }

    public UserProgram catProgram(Catalog catalog) {
        this.setCatProgram(catalog);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProgram)) {
            return false;
        }
        return id != null && id.equals(((UserProgram) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserProgram{" +
            "id=" + getId() +
            ", user=" + getUser() +
            ", active='" + getActive() + "'" +
            ", createById=" + getCreateById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedById=" + getLastModifiedById() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
