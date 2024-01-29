package com.oracle.medrec.patientsearch.dto;

import java.util.List;
import com.oracle.medrec.facade.model.FoundPatient;

public class JaxWsPatientFacadeBrokerFuzzyFindApprovedPatientsByLastnameAndSsnOutDTO {
    private List<FoundPatient> retVal;
    
    
    public List<FoundPatient> getRetVal() {
        return retVal;
    }

    public void setRetVal(List<FoundPatient> retVal) {
        this.retVal = retVal;
    }
    
}