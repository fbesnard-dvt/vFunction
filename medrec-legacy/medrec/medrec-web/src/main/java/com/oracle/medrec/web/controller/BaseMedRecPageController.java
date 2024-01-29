package com.oracle.medrec.web.controller;

import com.oracle.medrec.common.web.PageControllerSupport;
import com.oracle.medrec.service.AdministratorService;
import com.oracle.medrec.service.PatientService;
import com.oracle.medrec.service.RecordService;

import javax.inject.Inject;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public abstract class BaseMedRecPageController extends PageControllerSupport {

    @Inject
    private PatientService patientService;

    @Inject
    private RecordService recordService;

    @Inject
    private AdministratorService administratorService;

    protected PatientService getPatientService() {
        return patientService;
    }

    protected RecordService getRecordService() {
        return recordService;
    }

    protected AdministratorService getAdministratorService() {
        return administratorService;
    }

}
