package com.oracle.medrec.facade;

import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;

import java.util.List;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface PatientFacade {

    Patient getPatient(Long patientId);

    FoundPatient findApprovedPatientBySsn(String ssn);

    List<FoundPatient> findApprovedPatientsByLastName(String lastName);

    List<FoundPatient> fuzzyFindApprovedPatientsByLastnameAndSsn(
            String lastName, String ssn);
}
