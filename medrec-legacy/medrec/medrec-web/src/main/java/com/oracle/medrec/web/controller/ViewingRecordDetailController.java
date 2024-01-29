package com.oracle.medrec.web.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.oracle.medrec.model.Record;
import com.oracle.medrec.web.Constants;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class ViewingRecordDetailController extends BaseMedRecPageController {

    private static final Logger LOGGER = Logger
            .getLogger(ViewingRecordDetailController.class.getName());

    private String recordId;

    private Record record;

    public void setRecordId(String recordId) {
        LOGGER.log(Level.FINEST, "setRecordId() called");
        this.recordId = recordId;
    }

    public Record getRecord() {
        LOGGER.log(Level.FINEST, "getRecord() called: " + record);
        return record;
    }

    public String viewRecordDetail() {
        recordId = this.getPageContext().getRequestParam("recordId");
        LOGGER.log(Level.FINER, "Record ID: " + recordId);
        record = getRecordService().getRecord(Long.parseLong(recordId));
        LOGGER.log(Level.FINER, "Got record detail");
        LOGGER.log(Level.FINEST, "Symptoms: " + record.getSymptoms());
        LOGGER.log(Level.FINEST, "Temperature: "
                + record.getVitalSigns().getTemperature());
        LOGGER.log(Level.FINEST, "Number of prescriptions: "
                + record.getPrescriptions().size());
        if (record.getPrescriptions().size() > 0) {
            LOGGER.log(Level.FINEST, "Drug of the 1st prescription: "
                    + record.getPrescriptions().get(0).getDrug());
        }
        return Constants.VIEW_RECORD_DETAIL;
    }
    
}
