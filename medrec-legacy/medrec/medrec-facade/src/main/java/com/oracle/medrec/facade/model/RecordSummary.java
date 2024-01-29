package com.oracle.medrec.facade.model;

import com.oracle.medrec.model.Prescription;
import com.oracle.medrec.model.Record;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@XmlRootElement
public class RecordSummary extends TransferObject {

    private static final long serialVersionUID = 298734535L;

    private Long patientId;

    private List<SummarizedRecord> summarizedRecords;

    private List<Prescription> prescriptions;

    public RecordSummary() {
        summarizedRecords = new LinkedList<SummarizedRecord>();
        prescriptions = new LinkedList<Prescription>();
    }

    public RecordSummary(Long patientId, List<Record> records) {
        this.summarizedRecords = new ArrayList<SummarizedRecord>(records.size());
        prescriptions = new LinkedList<Prescription>();
        this.patientId = patientId;
        for (Record record : records) {
            SummarizedRecord summarizedRecord = new SummarizedRecord(record);
            summarizedRecords.add(summarizedRecord);
            prescriptions.addAll(record.getPrescriptions());
        }
    }

    public List<SummarizedRecord> getSummarizedRecords() {
        return summarizedRecords;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setSummarizedRecords(List<SummarizedRecord> summarizedRecords) {
        this.summarizedRecords = summarizedRecords;
    }

    public void addSummarizedRecord(SummarizedRecord summarizedRecord) {
        summarizedRecords.add(summarizedRecord);
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!patientId.equals(((RecordSummary) object).getPatientId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return patientId.hashCode();
    }
}
