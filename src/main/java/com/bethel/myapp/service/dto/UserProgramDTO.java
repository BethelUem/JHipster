package com.bethel.myapp.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.bethel.myapp.domain.UserProgram} entity.
 */
public class UserProgramDTO implements Serializable {

    private Long id;

    private Integer user;

    private Boolean active;

    private Integer createById;

    private ZonedDateTime createdDate;

    private Integer lastModifiedById;

    private ZonedDateTime lastModifiedDate;

    private CatalogDTO catProgram;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public CatalogDTO getCatProgram() {
        return catProgram;
    }

    public void setCatProgram(CatalogDTO catProgram) {
        this.catProgram = catProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProgramDTO)) {
            return false;
        }

        UserProgramDTO userProgramDTO = (UserProgramDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userProgramDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserProgramDTO{" +
            "id=" + getId() +
            ", user=" + getUser() +
            ", active='" + getActive() + "'" +
            ", createById=" + getCreateById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedById=" + getLastModifiedById() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", catProgram=" + getCatProgram() +
            "}";
    }
}
