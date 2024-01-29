package com.oracle.medrec.registernewpatients.dto;

import java.util.List;
import com.oracle.medrec.model.Patient;

public class ViewingNewlyRegisteredPatientsControllerViewNewlyRegisteredPatientsOutDTO {
	private List<Patient> retVal;
	
	public List<Patient> getRetVal() {
		return retVal;
	}
	
	public void setRetVal(List<Patient> retVal) {
		this.retVal = retVal;
	}
    
}