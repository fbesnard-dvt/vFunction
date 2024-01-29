package com.oracle.medrec.facade.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.oracle.medrec.model.PersonName;
import com.oracle.medrec.model.Physician;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@XmlRootElement
public class AuthenticatedPhysician extends TransferObject {

    private static final long serialVersionUID = 654351313L;

    private Long id;

    private PersonName name;

    private String username;

    public AuthenticatedPhysician() {
    }

    public AuthenticatedPhysician(Physician physician) {
        id = physician.getId();
        name = physician.getName();
        username = physician.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthenticatedPhysician authenticatedPhysician = (AuthenticatedPhysician) o;

        if (!id.equals(authenticatedPhysician.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
