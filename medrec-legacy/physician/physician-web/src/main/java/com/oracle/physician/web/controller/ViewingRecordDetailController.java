package com.oracle.physician.web.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.oracle.medrec.facade.model.RecordDetail;
import com.oracle.physician.web.Constants;

/**
 * ViewingRecordDetailController is a JSF ManagedBean that is responsible for
 * showing details of record.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class ViewingRecordDetailController extends BasePhysicianPageController {

    private static final Logger LOGGER = Logger
            .getLogger(ViewingRecordDetailController.class.getName());

    private String recordId;

    private RecordDetail recordDetail;

    public void setRecordId(String recordId) {
        LOGGER.log(Level.FINEST, "Set record id: " + recordId);
        this.recordId = recordId;
    }

    public RecordDetail getRecordDetail() {
        return recordDetail;
    }

    public String viewRecordDetail() {
        setRecordId(getPageContext().getRequestParam("recordId"));
        
        LOGGER.log(Level.FINER, "Record ID: " + recordId);

        // find record detail by id
        recordDetail = getRecordService().getRecordDetail(
                Long.parseLong(recordId));

        LOGGER.log(Level.FINER, "Got record detail");
        LOGGER.log(Level.FINER, "Symptoms: " + recordDetail.getSymptoms());
        LOGGER.log(Level.FINER, "Temperature: "
                + recordDetail.getVitalSigns().getTemperature());
        LOGGER.log(Level.FINER, "Number of prescriptions: "
                + recordDetail.getPrescriptions().size());
        if (recordDetail.getPrescriptions().size() > 0) {
            LOGGER.log(Level.FINER, "Drug of the 1st prescription: "
                    + recordDetail.getPrescriptions().get(0).getDrug());
        }
        return Constants.VIEW_RECORD_DETAIL;
    }
}
