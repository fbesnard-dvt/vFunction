package com.oracle.medrec.service.impl.notification;

import com.oracle.medrec.model.Patient;
import com.oracle.medrec.model.PersonName;

import java.io.Serializable;

/**
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights
 *         reserved.
 */
public class PatientToNotify implements Serializable {

    private static final long serialVersionUID = -109865L;

    private String firstName;

    private String email;

    private Patient.Status status;

    public PatientToNotify() {
    }

    public PatientToNotify(Patient patient) {
        email = patient.getEmail();
        firstName = patient.getName().getFirstName();
        status = patient.getStatus();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public Patient.Status getStatus() {
        return status;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Patient.Status status) {
        this.status = status;
    }

    public Patient toPatient() {
        Patient patient = new Patient();
        PersonName name = new PersonName();
        name.setFirstName(firstName);
        patient.setName(name);
        patient.setEmail(email);
        patient.setStatus(status);
        return patient;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!email.equals(((PatientToNotify) object).getEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

}
