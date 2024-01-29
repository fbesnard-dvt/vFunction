package com.oracle.medrec.web.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.oracle.medrec.model.Address;
import com.oracle.medrec.model.Patient;
import com.oracle.medrec.service.DuplicateSsnException;
import com.oracle.medrec.web.Constants;

/**
 * JSF managed bean for updating patient profile.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class ViewingPatientController extends BaseMedRecPageController {

    private Patient patient;

    @PostConstruct
    public void initialFields() {
        // get patient entity from JSF session
        patient = (Patient) getPageContext().getSessionMap().get(
                Constants.AUTHENTICATED_USER_SESSION_KEY);
        if (patient.getAddress() == null) {
            patient.setAddress(new Address());
        }
    }

    public Patient getPatient() {
        return patient;
    }

    public String updatePatient() {
        try {
            patient = getPatientService().updatePatient(patient);
        } catch (DuplicateSsnException e) {
            getPageContext().addGlobalOnlyErrorMessageWithKey(
                    "message.duplicateSsn");
            return Constants.VIEW_PATIENT;
        }       
        // put the updated patient into session
        getPageContext().getSessionMap().put(
                Constants.AUTHENTICATED_USER_SESSION_KEY, patient);       
        getPageContext().addGlobalOnlyInfoMessageWithKey(
                "message.updateProfileSuccessfully");
        return Constants.VIEW_PATIENT;
    }
}
