package com.oracle.physician.service.delegate.converter.impl;

import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Address;
import com.oracle.medrec.model.Patient;
import com.oracle.physician.service.delegate.converter.PatientConverter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PatientConverterImpl extends RegularUserConverterImpl implements
        PatientConverter {

    public Patient fromJaxbPatient(
            com.oracle.physician.service.delegate.stub.jaxws.Patient jaxbPatient) {
        Patient patient = new Patient();
        patient.setAddress(fromJaxbAddress(jaxbPatient.getAddress()));
        patient.setDob(jaxbPatient.getDob().toGregorianCalendar().getTime());
        patient.setEmail(jaxbPatient.getEmail());
        patient.setGender(fromJaxbGender(jaxbPatient.getGender()));
        patient.setName(fromJaxbPersonName(jaxbPatient.getName()));
        patient.setPassword(jaxbPatient.getPassword());
        patient.setPhone(jaxbPatient.getPhone());
        patient.setSsn(jaxbPatient.getSsn());
        patient.setStatus(fromJaxbStatus(jaxbPatient.getStatus()));
        patient.setUsername(jaxbPatient.getUsername());
        return patient;
    }

    public Address fromJaxbAddress(
            com.oracle.physician.service.delegate.stub.jaxws.Address jaxbAddress) {
        Address address = new Address();
        address.setCity(jaxbAddress.getCity());
        address.setCountry(jaxbAddress.getCountry());
        address.setState(jaxbAddress.getState());
        address.setStreet1(jaxbAddress.getStreet1());
        address.setStreet2(jaxbAddress.getStreet2());
        return address;
    }

    public Patient.Gender fromJaxbGender(
            com.oracle.physician.service.delegate.stub.jaxws.Gender jaxbGender) {
        if (com.oracle.physician.service.delegate.stub.jaxws.Gender.MALE
                .equals(jaxbGender)) {
            return Patient.Gender.MALE;
        } else {
            return Patient.Gender.FEMALE;
        }
    }

    public Patient.Status fromJaxbStatus(
            com.oracle.physician.service.delegate.stub.jaxws.Status jaxbStatus) {
        if (com.oracle.physician.service.delegate.stub.jaxws.Status.APPROVED
                .equals(jaxbStatus)) {
            return Patient.Status.APPROVED;
        } else if (com.oracle.physician.service.delegate.stub.jaxws.Status.DENIED
                .equals(jaxbStatus)) {
            return Patient.Status.DENIED;
        } else {
            return Patient.Status.REGISTERED;
        }
    }

    public FoundPatient fromJaxbFoundPatient(
            com.oracle.physician.service.delegate.stub.jaxws.FoundPatient jaxbFoundPatient) {
        FoundPatient foundPatient = new FoundPatient();
        foundPatient.setDob(jaxbFoundPatient.getDob().toGregorianCalendar()
                .getTime());
        foundPatient.setId(jaxbFoundPatient.getId());
        foundPatient.setName(fromJaxbPersonName(jaxbFoundPatient.getName()));
        foundPatient.setSsn(jaxbFoundPatient.getSsn());
        return foundPatient;
    }

    public List<FoundPatient> fromJaxbFoundPatients(
            List<com.oracle.physician.service.delegate.stub.jaxws.FoundPatient> jaxbFoundPatients) {
        List<FoundPatient> foundPatients = new ArrayList<FoundPatient>(
                jaxbFoundPatients.size());
        for (com.oracle.physician.service.delegate.stub.jaxws.FoundPatient jaxbFoundPatient : jaxbFoundPatients) {
            foundPatients.add(fromJaxbFoundPatient(jaxbFoundPatient));
        }
        return foundPatients;
    }
}
