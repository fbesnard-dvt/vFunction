package com.oracle.medrec.facade.model;

import com.oracle.medrec.model.Prescription;
import com.oracle.medrec.model.Record;
import com.oracle.medrec.model.VitalSigns;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@XmlRootElement
public class RecordToCreate extends TransferObject {

    private static final long serialVersionUID = -7485494494L;

    private String diagnosis;

    private String notes;

    private Date date = new Date();

    private String symptoms;

    private VitalSigns vitalSigns = new VitalSigns();

    private List<Prescription> prescriptions = new LinkedList<Prescription>();

    private Long patientId;

    private Long physicianId;

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(VitalSigns vitalSigns) {
        this.vitalSigns = vitalSigns;
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

    public Long getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(Long physicianId) {
        this.physicianId = physicianId;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Record toRecord() {
        Record record = new Record();
        record.setDate(date);
        record.setDiagnosis(diagnosis);
        record.setNotes(notes);
        record.setPrescriptions(prescriptions);
        if (prescriptions != null) {
            for (Prescription prescription : prescriptions) {
                prescription.setId(null);
                prescription.setVersion(null);
            }
        }
        record.setSymptoms(symptoms);
        record.setVitalSigns(vitalSigns);
        return record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordToCreate recordToCreate = (RecordToCreate) o;
        if (!patientId.equals(recordToCreate.getPatientId())) {
            return false;
        }
        if (!physicianId.equals(recordToCreate.getPhysicianId())) {
            return false;
        }
        if (!date.equals(recordToCreate.getDate())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = date.hashCode();
        result = 29 * result + (int) (patientId ^ (patientId >>> 32));
        result = 29 * result + (int) (physicianId ^ (physicianId >>> 32));
        return result;
    }
}
