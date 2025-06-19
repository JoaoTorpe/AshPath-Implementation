package com.pdsc.ashpath.domain.dto.response.user;

import java.time.LocalDateTime;
import java.util.Set;

import com.pdsc.ashpath.domain.entity.AppRole;

public abstract class AbstractUserReponse {
    protected Long id;
    protected String email;
    protected String fullname;
    protected LocalDateTime registrationDate;
    protected LocalDateTime lastActivityDate;
    protected Set<AppRole> appRoleSet;
    protected boolean approved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(LocalDateTime lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Set<AppRole> getAppRoleSet() {
        return appRoleSet;
    }

    public void setAppRoleSet(Set<AppRole> appRoleSet) {
        this.appRoleSet = appRoleSet;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
