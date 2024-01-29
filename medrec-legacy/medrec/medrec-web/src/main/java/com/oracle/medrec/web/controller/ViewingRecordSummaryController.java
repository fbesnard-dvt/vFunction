package com.oracle.medrec.web.controller;

import com.oracle.medrec.model.Patient;
import com.oracle.medrec.model.Prescription;
import com.oracle.medrec.model.Record;
import com.oracle.medrec.web.Constants;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class ViewingRecordSummaryController extends BaseMedRecPageController {

    private static final Logger LOGGER = Logger
            .getLogger(ViewingRecordSummaryController.class.getName());    

    @SuppressWarnings("unchecked")
    public List<Record> getRecords() {
        LOGGER.log(Level.FINEST, "getRecords() called");
        return (List<Record>) getPageContext().getSessionMap().get(
                Constants.RECORDS_SESSION_KEY);
    }

    @SuppressWarnings("unchecked")
    public List<Prescription> getPrescriptions() {
        LOGGER.log(Level.FINEST, "getPrescriptions() called");
        return (List<Prescription>) getPageContext().getSessionMap().get(
                Constants.PRESCRIPTIONS_SESSION_KEY);
    }

    public String viewRecordSummary() {
        Patient patient = (Patient) getPageContext().getSessionMap().get(
                Constants.AUTHENTICATED_USER_SESSION_KEY);
        List<Record> records = getRecordService().getRecordsByPatientId(
                patient.getId());
        List<Prescription> prescriptions = new LinkedList<Prescription>();
        for (Record record : records) {
            prescriptions.addAll(record.getPrescriptions());
        }
        LOGGER.log(Level.FINEST, "Number of prescriptions: " + prescriptions.size());
        LOGGER.log(Level.FINEST, "Number of records: " + records.size());       
        getPageContext().getSessionMap().put(Constants.RECORDS_SESSION_KEY, records);
        getPageContext().getSessionMap().put(Constants.PRESCRIPTIONS_SESSION_KEY,
                prescriptions);
        return Constants.VIEW_RECORDS_SUM;
    }
}
