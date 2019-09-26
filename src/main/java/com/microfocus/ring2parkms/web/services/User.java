package com.microfocus.ring2parkms.web.services;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * User DTO - used to interact with the {@link WebUsersService}.
 *
 * @author Kevin A. Lee
 */
@JsonRootName("User")
public class User {

    protected Long id;
    protected String username;
    protected String password;
    protected String confirmPassword;
    protected String name;
    protected String email;
    protected String mobile;
    protected boolean enabled;
    protected boolean acceptTerms;
    protected String verifyCode;

    /**
     * Default constructor for JPA only.
     */
    protected User() {
    }

    public long getId() {
        return id;
    }

    /**
     * Set JPA id - for testing and JPA only. Not intended for normal use.
     *
     * @param id
     *            The new id.
     */
    protected void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(Boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public boolean isCurrentUser(String username) {
        if (this.username.equals(username))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "User(" + username + " - " + name + " - " + email + ")";
    }

}

