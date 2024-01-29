package com.oracle.medrec.service;

import com.oracle.medrec.model.Record;

import java.util.List;

/**
 * Diagnostic record business service interface.
 * 
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface RecordService {

    /**
     * Creates a diagnostic record by physician for one patient.
     * 
     * @param record
     * @param physicianId
     * @param patientId
     */
    void createRecord(Record record, Long physicianId, Long patientId);

    /**
     * Retrieves one patient's all own diagnostic records.
     * 
     * @param patientId
     * @return
     */
    List<Record> getRecordsByPatientId(Long patientId);

    /**
     * Retrieves diagnostic record by record id.
     * 
     * @param id
     * @return
     */
    Record getRecord(Long id);
}
