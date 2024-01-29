package com.oracle.medrec.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * Base class defining the stuff common to any types of users.
 * 
 * In current MedRec, each user only has one role identified by its class type.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@MappedSuperclass
public abstract class User extends VersionedEntity {

    private static final long serialVersionUID = -1847921044253901787L;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @Email
    @NotNull
    @Size(min = 3, max = 60)
    @Column(unique = true)
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        setUsername(email);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return super.equals(obj);
    }

}
