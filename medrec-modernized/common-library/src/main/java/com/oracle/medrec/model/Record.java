package com.oracle.medrec.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Entity
@Table(name = "records")
@NamedQuery(name = "Record.findRecordsByPatientId", query = "SELECT r FROM Record r WHERE r.patient.id = :patientId")
public class Record extends VersionedEntity {

    // TODO need to lazily load some stuff?

    private static final long serialVersionUID = -4395051789276646078L;

    private String diagnosis;

    private String notes;

    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date date;

    private String symptoms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Physician physician;

    private VitalSigns vitalSigns;

    /**
     * Prescriptions need to be acessed whenever record is accessed
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Prescription> prescriptions = new LinkedList<Prescription>();

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

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Record record = (Record) o;

        if (date != null ? !date.equals(record.getDate())
                : record.getDate() != null) {
            return false;
        }
        if (patient != null ? !patient.equals(record.getPatient()) : record
                .getPatient() != null) {
            return false;
        }
        if (physician != null ? !physician.equals(record.getPhysician())
                : record.getPhysician() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        // We don't use the associated entities here since they may be lazily
        // loaded
        int result;
        result = (date != null ? date.hashCode() : 0);
        result = 29 * result + (diagnosis != null ? diagnosis.hashCode() : 0);
        result = 29 * result + (notes != null ? notes.hashCode() : 0);
        result = 29 * result + (symptoms != null ? symptoms.hashCode() : 0);
        return result;
    }
}
