package com.oracle.medrec.facade;

import com.oracle.medrec.facade.model.RecordDetail;
import com.oracle.medrec.facade.model.RecordSummary;
import com.oracle.medrec.facade.model.RecordToCreate;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface RecordFacade {

    void createRecord(RecordToCreate record);

    RecordSummary getRecordSummaryByPatientId(Long patientId);

    RecordDetail getRecordDetail(Long id);
}
