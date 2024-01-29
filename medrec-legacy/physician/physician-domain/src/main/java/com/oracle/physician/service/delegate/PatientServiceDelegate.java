package com.oracle.physician.service.delegate;

import com.oracle.medrec.common.core.MethodCache;
import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;
import com.oracle.physician.JaxWsProperties;
import com.oracle.physician.service.PatientService;
import com.oracle.physician.service.delegate.converter.PatientConverter;
import com.oracle.physician.service.delegate.stub.jaxws.PatientFacade;
import com.oracle.physician.service.delegate.stub.jaxws.PatientFacadeService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.namespace.QName;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class PatientServiceDelegate implements PatientService, Serializable {

    private static final long serialVersionUID = 1L;

    private PatientFacade patientFacade;

    // TODO Define the properties in external deployment descriptor

    private String patientFacadeServiceName = "PatientFacadeService";

    private String patientFacadeServiceNamespaceUri = JaxWsProperties.NAMESPACEURL;

    private String patientFacadeServiceWsdlLocation = JaxWsProperties.WSURL
            + "PatientFacadeService?WSDL";

    @Inject
    private PatientConverter patientConverter;

    @PostConstruct
    public void init() throws Throwable {
        if (patientFacade == null) {
            patientFacade = new PatientFacadeService(new URL(
                    patientFacadeServiceWsdlLocation), new QName(
                    patientFacadeServiceNamespaceUri, patientFacadeServiceName))
                    .getPatientFacadePort();
        }
    }

    @MethodCache
    public Patient getPatient(Long patientId) {
        return patientConverter.fromJaxbPatient(patientFacade
                .getPatient(patientId));
    }

    public FoundPatient findApprovedPatientBySsn(String ssn) {
        com.oracle.physician.service.delegate.stub.jaxws.FoundPatient patient = patientFacade
                .findApprovedPatientBySsn(ssn);
        if (patient == null) {
            return null;
        }
        return patientConverter.fromJaxbFoundPatient(patient);
    }

    public List<FoundPatient> findApprovedPatientsByLastName(String lastName) {
        return patientConverter.fromJaxbFoundPatients(patientFacade
                .findApprovedPatientsByLastName(lastName));
    }

    public List<FoundPatient> fuzzyFindApprovedPatientsByLastnameAndSsn(
            String lastName, String ssn) {
        return patientConverter.fromJaxbFoundPatients(patientFacade
                .fuzzyFindApprovedPatientsByLastnameAndSsn(lastName, ssn));
    }

}
