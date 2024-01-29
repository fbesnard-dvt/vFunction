package com.oracle.medrec.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Embeddable
public class PersonName implements Serializable {

    private static final long serialVersionUID = 5610658213331393425L;

    @NotNull
    @Size(min = 1, max = 60)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 60)
    private String lastName;

    @Size(min = 1, max = 60)
    private String middleName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonName name = (PersonName) o;

        if (firstName != null ? !firstName.equals(name.getFirstName()) : name
                .getFirstName() != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(name.getLastName()) : name
                .getLastName() != null) {
            return false;
        }
        if (middleName != null ? !middleName.equals(name.getMiddleName())
                : name.getMiddleName() != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (firstName != null ? firstName.hashCode() : 0);
        result = 29 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 29 * result + (middleName != null ? middleName.hashCode() : 0);
        return result;
    }
}
