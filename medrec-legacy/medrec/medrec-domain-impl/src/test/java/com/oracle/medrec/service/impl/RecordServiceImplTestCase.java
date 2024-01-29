package com.oracle.medrec.service.impl;

import com.oracle.medrec.common.testing.EntityRepositoryTestCaseSupport;
import com.oracle.medrec.model.Address;
import com.oracle.medrec.model.Patient;
import com.oracle.medrec.model.PersonName;
import com.oracle.medrec.model.Physician;
import com.oracle.medrec.model.Prescription;
import com.oracle.medrec.model.Record;
import com.oracle.medrec.model.VitalSigns;

import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * {@link RecordServiceImpl} test case.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class RecordServiceImplTestCase extends
        EntityRepositoryTestCaseSupport<RecordServiceImpl> {

    private Record record;
    private Patient patient;
    private Physician physician;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(RecordServiceImplTestCase.class);
    }

    @Before
    public void before() {
        // patient
        patient = new Patient();
        patient.setEmail("a@oracle.com");
        patient.setPassword("password");
        patient.setGender(Patient.Gender.FEMALE);
        patient.setDob(new Date());
        patient.setSsn("111111111");
        patient.setPhone("11111111");
        Address address = new Address();
        address.setCity("city");
        address.setCountry("country");
        address.setState("state");
        address.setStreet1("street1");
        address.setStreet2("street2");
        address.setZip("11111");
        patient.setAddress(address);
        PersonName name = new PersonName();
        name.setFirstName("firstname");
        name.setMiddleName("middlename");
        name.setLastName("lastname");
        patient.setName(name);
        service.getEntityManager().persist(patient);

        // physician
        physician = new Physician();
        physician.setPassword("password");
        physician.setEmail("a@oracle.com");
        physician.setName(name);
        physician.setPhone("11111111");
        service.getEntityManager().persist(physician);

        // record
        record = createRecord();
        service.getEntityManager().persist(record);
    }

    @After
    public void after() {
        service.getEntityManager().remove(record);
        service.getEntityManager().remove(patient);
        service.getEntityManager().remove(physician);
    }

    @Test
    public void testGetRecord() {
        Record rd = service.getRecord(record.getId());
        assertEquals(rd.getId(), record.getId());
    }

    @Test
    public void testGetRecordsByPatientId() {
        List<Record> rd = service.getRecordsByPatientId(patient.getId());
        assertEquals(rd.get(0).getId(), record.getId());
    }

    @Test
    public void testCreateRecord() {
        Record rd = createRecord();
        service.createRecord(rd, physician.getId(), patient.getId());
        service.getEntityManager().remove(rd);
    }

    private Record createRecord() {
        Record rd = new Record();
        rd.setDate(new Date());
        rd.setDiagnosis("diagnosis");
        rd.setNotes("notes");
        rd.setSymptoms("symptoms");
        VitalSigns vitalSigns = new VitalSigns();
        vitalSigns.setDiastolicBloodPressure(100);
        vitalSigns.setHeight(100);
        vitalSigns.setPulse(80);
        vitalSigns.setSystolicBloodPressure(120);
        vitalSigns.setTemperature(100);
        vitalSigns.setWeight(100);
        rd.setPhysician(physician);
        rd.setPatient(patient);
        Prescription prescription = new Prescription();
        prescription.setDatePrescribed(new Date());
        prescription.setDosage("dosage");
        prescription.setDrug("drug");
        prescription.setFrequency("frequency");
        prescription.setInstructions("instructions");
        prescription.setRefillsRemaining(1);
        rd.addPrescription(prescription);
        return rd;
    }
}
