package com.oracle.physician.web.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;
import com.oracle.physician.web.Constants;

/**
 * ViewingPatientController is a JSF ManagedBean that is responsible for showing
 * patient's profile.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class ViewingPatientController extends BasePhysicianPageController {

    private static final Logger LOGGER = Logger
            .getLogger(ViewingPatientController.class.getName());

    private Patient patient;

    @PostConstruct
    public void initPatient() {
        Long patientId = ((FoundPatient) getPageContext().getSessionMap().get(
                Constants.PATIENT_SESSION_KEY)).getId();
        LOGGER.log(Level.FINEST, "Patient ID: " + patientId);
        patient = getPatientService().getPatient(patientId);
        LOGGER.log(Level.FINEST, "Patient gender: " + patient.getGender());
    }

    public Patient getPatient() {
        return patient;
    }

}
