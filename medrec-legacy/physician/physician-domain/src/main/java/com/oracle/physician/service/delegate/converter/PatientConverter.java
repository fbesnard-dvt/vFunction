package com.oracle.physician.service.delegate.converter;

import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Address;
import com.oracle.medrec.model.Patient;
import com.oracle.medrec.model.PersonName;

import java.util.List;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface PatientConverter {

    Patient fromJaxbPatient(
            com.oracle.physician.service.delegate.stub.jaxws.Patient jaxbPatient);

    Address fromJaxbAddress(
            com.oracle.physician.service.delegate.stub.jaxws.Address jaxbAddress);

    Patient.Gender fromJaxbGender(
            com.oracle.physician.service.delegate.stub.jaxws.Gender jaxbGender);

    Patient.Status fromJaxbStatus(
            com.oracle.physician.service.delegate.stub.jaxws.Status jaxbStatus);

    PersonName fromJaxbPersonName(
            com.oracle.physician.service.delegate.stub.jaxws.PersonName jaxbPersonName);

    FoundPatient fromJaxbFoundPatient(
            com.oracle.physician.service.delegate.stub.jaxws.FoundPatient jaxbFoundPatient);

    List<FoundPatient> fromJaxbFoundPatients(
            List<com.oracle.physician.service.delegate.stub.jaxws.FoundPatient> jaxbFoundPatients);
}
