package com.oracle.medrec.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Entity
@Table(name = "patients")
public class Patient extends RegularUser {

    private static final long serialVersionUID = 313728838021028177L;

    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @NotNull()
    private Date dob;

    @Enumerated(EnumType.STRING)
    @NotNull()
    private Gender gender;

    @Column(unique = true)
    @NotNull()
    @Size(min = 9, max = 9)
    private String ssn;

    private Address address = new Address();

    @Enumerated(EnumType.STRING)
    private Patient.Status status = Patient.Status.REGISTERED;

    // No setter and getter... Now used to do cascading
    @OneToMany(cascade = CascadeType.ALL)
    private List<Record> records;

    @Transient
    private boolean isSsnChanged = false;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        if (this.ssn != null && !this.ssn.equals(ssn)) {
            setSsnChanged(true);
        }
        this.ssn = ssn;
    }

    public void approve() {
        setStatus(Patient.Status.APPROVED);
    }

    public void deny() {
        setStatus(Patient.Status.DENIED);
    }

    public boolean isApproved() {
        return Patient.Status.APPROVED.equals(getStatus());
    }

    public boolean isDenied() {
        return Patient.Status.DENIED.equals(getStatus());
    }

    public Patient.Status getStatus() {
        return status;
    }

    public void setStatus(Patient.Status status) {
        this.status = status;
    }

    public enum Status {
        REGISTERED, APPROVED, DENIED
    }

    public enum Gender {
        MALE, FEMALE
    }

    public boolean isSsnChanged() {
        return isSsnChanged;
    }

    public void setSsnChanged(boolean ssnChanged) {
        this.isSsnChanged = ssnChanged;
    }

}
