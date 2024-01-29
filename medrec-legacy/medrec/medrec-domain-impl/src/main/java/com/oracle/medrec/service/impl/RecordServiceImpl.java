package com.oracle.medrec.service.impl;

import com.oracle.medrec.common.persistence.BasePersistenceService;
import com.oracle.medrec.common.persistence.JPQLPersistenceSupport;
import com.oracle.medrec.model.Patient;
import com.oracle.medrec.model.Physician;
import com.oracle.medrec.model.Record;
import com.oracle.medrec.service.RecordService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import java.util.List;

/**
 * Diagnostic record buisness service implementation. which is responsible for
 * all business operations to record.
 * 
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
public class RecordServiceImpl extends BasePersistenceService implements
        RecordService {

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createRecord(Record record, Long physicianId, Long patientId) {
        Patient patient = entityManager.find(Patient.class, patientId);
        if (patient == null) {
            throw new RuntimeException("Invalid patient id [" + patientId + "]");
        }
        Physician physician = entityManager.find(Physician.class, physicianId);
        if (physician == null) {
            throw new RuntimeException("Invalid physician id [" + physicianId
                    + "]");
        }
        record.setPatient(patient);
        record.setPhysician(physician);
        entityManager.persist(record);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Record> getRecordsByPatientId(Long patientId) {
        return JPQLPersistenceSupport.findByProperty(entityManager,
                Record.class, "Record.findRecordsByPatientId", "patientId",
                patientId);
    }

    public Record getRecord(Long id) {
        return entityManager.find(Record.class, id);
    }
}
