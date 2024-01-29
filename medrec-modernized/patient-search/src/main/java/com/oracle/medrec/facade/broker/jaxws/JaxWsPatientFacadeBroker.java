package com.oracle.medrec.facade.broker.jaxws;

import com.oracle.medrec.facade.PatientFacade;
import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;

import javax.annotation.PostConstruct;
//import javax.jws.WebMethod;
//import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
//@WebService(name = "PatientFacade", portName = "PatientFacadePort", serviceName = "PatientFacadeService", targetNamespace = "http://www.oracle.com/medrec")
public class JaxWsPatientFacadeBroker {
	
	private static final Logger LOGGER = Logger
            .getLogger(JaxWsPatientFacadeBroker.class.getName());

	private PatientFacade patientFacade;
    
    /**
     * Demonstrating just how to look up bean by portable JNDI.
     */
    @PostConstruct
    public void setRecordFacade() {
        try {
            Context context = new InitialContext();
            patientFacade = (PatientFacade) context
                    /*.lookup("java:app/medrec-facade-impl/PatientFacadeImpl");*/
            		.lookup("java:app/PatientSearch/PatientFacadeImpl");
            LOGGER.log(Level.FINER, "Looking up PatientFacade success.");
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, "Looking up PatientFacade failed.", e);
        }
    }

    // @WebMethod
    public Patient getPatient(Long patientId) {
        return patientFacade.getPatient(patientId);
    }

    // @WebMethod
    public FoundPatient findApprovedPatientBySsn(String ssn) {
        return patientFacade.findApprovedPatientBySsn(ssn);
    }

    // @WebMethod
    public List<FoundPatient> findApprovedPatientsByLastName(String lastName) {
        return patientFacade.findApprovedPatientsByLastName(lastName);
    }

    // @WebMethod
    public List<FoundPatient> fuzzyFindApprovedPatientsByLastnameAndSsn(
            String lastName, String ssn) {
        return patientFacade.fuzzyFindApprovedPatientsByLastnameAndSsn(
                lastName, ssn);
    }
}
