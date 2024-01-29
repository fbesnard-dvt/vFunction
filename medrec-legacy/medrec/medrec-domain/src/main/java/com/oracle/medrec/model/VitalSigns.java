package com.oracle.medrec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Embeddable
@XmlRootElement
public class VitalSigns implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "systolic_blood_pressure")
    private Integer systolicBloodPressure;

    @Column(name = "diastolic_blood_pressure")
    private Integer diastolicBloodPressure;

    private Integer height;

    private Integer pulse;

    private Integer temperature;

    private Integer weight;

    public Integer getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(Integer systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public Integer getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(Integer diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VitalSigns vitalSigns = (VitalSigns) o;

        if (diastolicBloodPressure != null ? !diastolicBloodPressure
                .equals(vitalSigns.getDiastolicBloodPressure()) : vitalSigns
                .getDiastolicBloodPressure() != null) {
            return false;
        }
        if (height != null ? !height.equals(vitalSigns.getHeight())
                : vitalSigns.getHeight() != null) {
            return false;
        }
        if (pulse != null ? !pulse.equals(vitalSigns.getPulse()) : vitalSigns
                .getPulse() != null) {
            return false;
        }
        if (systolicBloodPressure != null ? !systolicBloodPressure
                .equals(vitalSigns.getSystolicBloodPressure()) : vitalSigns
                .getSystolicBloodPressure() != null) {
            return false;
        }
        if (temperature != null ? !temperature.equals(vitalSigns
                .getTemperature()) : vitalSigns.getTemperature() != null) {
            return false;
        }
        if (weight != null ? !weight.equals(vitalSigns.getWeight())
                : vitalSigns.getWeight() != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (systolicBloodPressure != null ? systolicBloodPressure : 0);
        result = 29 * result
                + (diastolicBloodPressure != null ? diastolicBloodPressure : 0);
        result = 29 * result + (height != null ? height : 0);
        result = 29 * result + (pulse != null ? pulse : 0);
        result = 29 * result + (temperature != null ? temperature : 0);
        result = 29 * result + (weight != null ? weight : 0);
        return result;
    }
}
