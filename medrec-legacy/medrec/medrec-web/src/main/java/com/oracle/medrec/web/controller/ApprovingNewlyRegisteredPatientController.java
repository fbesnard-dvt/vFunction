package com.oracle.medrec.web.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.oracle.medrec.model.Patient;
import com.oracle.medrec.web.Constants;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class ApprovingNewlyRegisteredPatientController extends
        BaseMedRecPageController {

    private String patientId;

    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String viewNewlyRegisteredPatient() {
        patientId = this.getPageContext().getRequestParam("patientId");
        patient = getPatientService().getPatient(Long.parseLong(patientId));
        return Constants.VIEW_NEW_PATIENT;
    }

    public String approvePatient() {
        getPatientService().approvePatient(Long.parseLong(patientId));
        return Constants.VIEW_APPROVAL_RESULT;
    }

    public String denyPatient() {
        getPatientService().denyPatient(Long.parseLong(patientId));
        return Constants.VIEW_APPROVAL_RESULT;
    }
}
