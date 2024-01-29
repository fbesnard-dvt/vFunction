package com.oracle.medrec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.oracle.medrec.common.util.DateAdapter;

import java.util.Date;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Entity
@XmlRootElement
@Table(name = "prescriptions")
public class Prescription extends VersionedEntity {

    private static final long serialVersionUID = 4993043341973318975L;

    @Column(name = "date_prescribed")
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date datePrescribed = new Date();

    private String dosage;

    private String drug;

    private String frequency;

    private String instructions;

    // private Record record;

    @Column(name = "refills_remaining")
    private Integer refillsRemaining;

    public Date getDatePrescribed() {
        return datePrescribed;
    }

    public void setDatePrescribed(Date datePrescribed) {
        this.datePrescribed = datePrescribed;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getRefillsRemaining() {
        return refillsRemaining;
    }

    public void setRefillsRemaining(Integer refillsRemaining) {
        this.refillsRemaining = refillsRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Prescription prescription = (Prescription) o;

        if (refillsRemaining != null ? !refillsRemaining.equals(prescription
                .getRefillsRemaining())
                : prescription.getRefillsRemaining() != null) {
            return false;
        }
        if (datePrescribed != null ? !datePrescribed.equals(prescription
                .getDatePrescribed())
                : prescription.getDatePrescribed() != null) {
            return false;
        }
        if (dosage != null ? !dosage.equals(prescription.getDosage())
                : prescription.getDosage() != null) {
            return false;
        }
        if (drug != null ? !drug.equals(prescription.getDrug()) : prescription
                .getDrug() != null) {
            return false;
        }
        if (frequency != null ? !frequency.equals(prescription.getFrequency())
                : prescription.getFrequency() != null) {
            return false;
        }
        if (instructions != null ? !instructions.equals(prescription
                .getInstructions()) : prescription.getInstructions() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (datePrescribed != null ? datePrescribed.hashCode() : 0);
        result = 29 * result + (dosage != null ? dosage.hashCode() : 0);
        result = 29 * result + (drug != null ? drug.hashCode() : 0);
        result = 29 * result + (frequency != null ? frequency.hashCode() : 0);
        result = 29 * result
                + (instructions != null ? instructions.hashCode() : 0);
        result = 29 * result + refillsRemaining;
        return result;
    }
}
