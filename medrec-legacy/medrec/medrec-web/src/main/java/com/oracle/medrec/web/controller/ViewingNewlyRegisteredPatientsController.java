package com.oracle.medrec.web.controller;

import com.oracle.medrec.model.Patient;
import com.oracle.medrec.web.Constants;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class ViewingNewlyRegisteredPatientsController extends
        BaseMedRecPageController {

    @SuppressWarnings("unchecked")
    public List<Patient> getPatients() {
        return (List<Patient>) getPageContext().getSessionMap().get(
                Constants.PATIENTS_SESSION_KEY);
    }

    public String viewNewlyRegisteredPatients() {
        List<Patient> patients = getPatientService()
                .getNewlyRegisteredPatients();
        getPageContext().getSessionMap().put(Constants.PATIENTS_SESSION_KEY, patients);
        return Constants.VIEW_NEW_PATIENTS;
    }

}
