package com.oracle.medrec.registernewpatients.dto;

import com.oracle.medrec.service.impl.notification.PatientToNotify;

public class PatientNotifierBrokerHandlePayloadInDTO {
    // private Object x0;
	private PatientToNotify x0;
    
    // public Object getX0() {
	public PatientToNotify getX0() {
        return x0;
    }

    //public void setX0(Object x0) {
	public void setX0(PatientToNotify x0) {
        this.x0 = x0;
    }
    
}