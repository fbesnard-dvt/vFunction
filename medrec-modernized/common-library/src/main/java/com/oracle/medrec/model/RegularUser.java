package com.oracle.medrec.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * Base class defining properties common to any regular users (not including
 * administrators).
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@MappedSuperclass
public abstract class RegularUser extends User {

    private static final long serialVersionUID = 1L;

    private PersonName name = new PersonName();

    @Size(min = 1, max = 12)
    private String phone;

    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
