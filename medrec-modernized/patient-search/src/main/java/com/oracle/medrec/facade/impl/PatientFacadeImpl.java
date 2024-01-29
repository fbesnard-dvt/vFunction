package com.oracle.medrec.facade.impl;

import com.oracle.medrec.common.core.MethodParameterValidatingInterceptor;
import com.oracle.medrec.common.core.ThrowableLoggingInterceptor;
import com.oracle.medrec.facade.PatientFacade;
import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;
import com.oracle.medrec.service.PatientService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Interceptors({ MethodParameterValidatingInterceptor.class,
        ThrowableLoggingInterceptor.class })
public class PatientFacadeImpl implements PatientFacade {

    @EJB
    private PatientService patientService;

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    public Patient getPatient(Long patientId) {
        return patientService.getPatient(patientId);
    }

    public FoundPatient findApprovedPatientBySsn(String ssn) {
        Patient patient = patientService.findApprovedPatientBySsn(ssn);
        if (patient == null) {
            return null;
        }
        return new FoundPatient(patient);
    }

    public List<FoundPatient> findApprovedPatientsByLastName(String lastName) {
        List<Patient> patients = patientService
                .findApprovedPatientsByLastName(lastName);
        List<FoundPatient> foundPatients = new LinkedList<FoundPatient>();
        for (Patient patient : patients) {
            foundPatients.add(new FoundPatient(patient));
        }
        return foundPatients;
    }

    public List<FoundPatient> fuzzyFindApprovedPatientsByLastnameAndSsn(
            String lastName, String ssn) {
        List<Patient> patients = patientService
                .fuzzyFindApprovedPatientsByLastnameAndSsn(lastName, ssn);
        List<FoundPatient> foundPatients = new LinkedList<FoundPatient>();
        for (Patient patient : patients) {
            foundPatients.add(new FoundPatient(patient));
        }
        return foundPatients;
    }
}
