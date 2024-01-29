package com.oracle.physician.web.controller;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.oracle.medrec.facade.model.AuthenticatedPhysician;
import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.facade.model.RecordToCreate;
import com.oracle.medrec.model.Prescription;
import com.oracle.physician.web.Constants;

/**
 * CreatingRecordController is a JSF ManagedBean that is responsible for
 * creating diagnostic record of a patient by physician.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@SessionScoped
public class CreatingRecordController extends BasePhysicianPageController
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(CreatingRecordController.class.getName());

    private RecordToCreate record;

    private Prescription prescription;

    @PostConstruct
    public void initRecordAndPrescription() {
        if (record == null) {
            record = new RecordToCreate();
        }
        if (prescription == null) {
            initPrescription();
        }
    }

    private void initPrescription() {
        prescription = new Prescription();
        prescription.setDosage("16 oz");
        prescription.setDrug("Drixoral");
        prescription.setRefillsRemaining(0);
        prescription.setFrequency("1tspn/4hrs");
        prescription.setInstructions("no instructions");
    }

    public RecordToCreate getRecord() {
        return record;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public String saveRecord() {
        // get physician
        AuthenticatedPhysician physician = (AuthenticatedPhysician) getPageContext()
                .getSessionMap().get(Constants.AUTHENTICATED_USER_SESSION_KEY);
        // get current patient
        FoundPatient patient = (FoundPatient) getPageContext().getSessionMap()
                .get(Constants.PATIENT_SESSION_KEY);
        // set relevance
        record.setPhysicianId(physician.getId());
        record.setPatientId(patient.getId());

        LOGGER.info("Creating record...");
        LOGGER.log(Level.FINE, "Patient ID: " + patient.getId());
        LOGGER.log(Level.FINE, "Physician ID: " + physician.getId());
        LOGGER.log(Level.FINE, "Symptoms: " + record.getSymptoms());
        LOGGER.log(Level.FINE, "Number of prescriptions: "
                + record.getPrescriptions().size());

        // create
        getRecordService().createRecord(record);
        LOGGER.info("Creating record finished successfully.");

        return Constants.VIEW_RECORD_CREATION_RESULT;
    }

    public void addPrescription() {
        record.addPrescription(prescription);
        initPrescription();
    }

}
