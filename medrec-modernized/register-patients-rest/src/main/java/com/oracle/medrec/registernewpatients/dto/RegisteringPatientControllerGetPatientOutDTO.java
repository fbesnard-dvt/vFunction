package com.oracle.medrec.registernewpatients.dto;

import com.oracle.medrec.model.Patient;

public class RegisteringPatientControllerGetPatientOutDTO {
    private Patient retVal;
    
    
    public Patient getRetVal() {
        return retVal;
    }

    public void setRetVal(Patient retVal) {
        this.retVal = retVal;
    }
    
}