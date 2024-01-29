package com.oracle.physician.web.controller;

import com.oracle.physician.common.web.PageControllerSupport;
import com.oracle.physician.service.PatientService;
import com.oracle.physician.service.PhysicianService;
import com.oracle.physician.service.RecordService;

import javax.inject.Inject;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public abstract class BasePhysicianPageController extends PageControllerSupport {

    @Inject
    private PhysicianService physicianService;

    @Inject
    private PatientService patientService;

    @Inject
    private RecordService recordService;

    protected PhysicianService getPhysicianService() {
        return physicianService;
    }

    protected PatientService getPatientService() {
        return patientService;
    }

    protected RecordService getRecordService() {
        return recordService;
    }
}
