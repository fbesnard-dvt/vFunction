package com.oracle.medrec.patientsearch.dto;

public class JaxWsPatientFacadeBrokerFuzzyFindApprovedPatientsByLastnameAndSsnInDTO {
    private String lastName;
    private String ssn;
    
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
}