package com.oracle.medrec.facade.broker.rmi;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.oracle.medrec.facade.PatientFacade;
import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;

import java.util.List;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class RmiPatientFacadeBroker implements RmiPatientFacadeBrokerRemote {

    @EJB
    private PatientFacade patientFacade;

    public void setPatientFacade(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    public Patient getPatient(Long patientId) {
        return patientFacade.getPatient(patientId);
    }

    public FoundPatient findApprovedPatientBySsn(String ssn) {
        return patientFacade.findApprovedPatientBySsn(ssn);
    }

    public List<FoundPatient> findApprovedPatientsByLastName(String lastName) {
        return patientFacade.findApprovedPatientsByLastName(lastName);
    }

    public List<FoundPatient> fuzzyFindApprovedPatientsByLastnameAndSsn(
            String lastName, String ssn) {
        return patientFacade.fuzzyFindApprovedPatientsByLastnameAndSsn(
                lastName, ssn);
    }
}
