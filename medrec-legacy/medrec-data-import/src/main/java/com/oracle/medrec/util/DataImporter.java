package com.oracle.medrec.util;

import com.oracle.medrec.model.Address;
import com.oracle.medrec.model.Administrator;
import com.oracle.medrec.model.Patient;
import com.oracle.medrec.model.PersonName;
import com.oracle.medrec.model.Physician;
import com.oracle.medrec.model.Prescription;
import com.oracle.medrec.model.Record;
import com.oracle.medrec.model.VitalSigns;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Calendar;

/**
 * Now we simply hard code all the data inside Java code.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public final class DataImporter {

    public static void main(String... args) {
        new DataImporter().importData();
    }

    private Patient winner;

    private Patient parrot;

    private Patient spiker;

    private Patient trout;

    private Physician oblige;

    public void importData() {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("MedRecDataImport");
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            cleanData(entityManager);
            importPhysicians(entityManager);
            importPatients(entityManager);
            importRecords(entityManager);
            importAdministrators(entityManager);
            entityTransaction.commit();
        } catch (RuntimeException e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("The data import failed!");
            throw e;
        } finally {
            try {
                entityManager.close();
            } catch (Exception e) {
            }
        }
        System.out.println("All the data has been imported successfully!");
    }

    private void cleanData(EntityManager entityManager) {
        entityManager.createQuery("delete from Record r").executeUpdate();
        entityManager.createQuery("delete from Patient p").executeUpdate();
        entityManager.createQuery("delete from Physician p").executeUpdate();
        entityManager.createQuery("delete from Administrator a")
                .executeUpdate();
        entityManager.flush();
    }

    private void importAdministrators(EntityManager entityManager) {
        Administrator admin = new Administrator();

        admin.setPassword("weblogic");
        admin.setEmail("admin@avitek.com");
        entityManager.persist(admin);
    }

    private void importPhysicians(EntityManager entityManager) {
        Physician physician = new Physician();

        physician.setPassword("weblogic");
        physician.setEmail("mary@md.com");
        PersonName name = new PersonName();
        name.setFirstName("Mary");
        name.setMiddleName("J");
        name.setLastName("Oblige");
        physician.setName(name);
        physician.setPhone("1234567812");
        entityManager.persist(physician);
        oblige = physician;
    }

    private void importPatients(EntityManager entityManager) {
        Patient patient = new Patient();
        patient.setPassword("weblogic");
        Calendar dob = Calendar.getInstance();
        dob.set(1975, 3, 26);
        patient.setDob(dob.getTime());
        Address address = new Address();
        address.setCity("San Francisco");
        address.setCountry("United States");
        address.setState("California");
        address.setStreet1("1224 Post St");
        address.setStreet2("Suite 100");
        address.setZip("94115");
        patient.setAddress(address);
        patient.setSsn("123456789");
        patient.setGender(Patient.Gender.MALE);
        patient.setEmail("fred@golf.com");
        PersonName name = new PersonName();
        name.setFirstName("Fred");
        name.setMiddleName("I");
        name.setLastName("Winner");
        patient.setName(name);
        patient.setPhone("4151234564");
        patient.setStatus(Patient.Status.APPROVED);
        entityManager.persist(patient);
        winner = patient;

        patient = new Patient();
        patient.setPassword("weblogic");
        dob = Calendar.getInstance();
        dob.set(1969, 2, 13);
        patient.setDob(dob.getTime());
        address = new Address();
        address.setCity("San Francisco");
        address.setCountry("United States");
        address.setState("California");
        address.setStreet1("1224 Post St");
        address.setStreet2("Suite 100");
        address.setZip("94115");
        patient.setAddress(address);
        patient.setSsn("777777777");
        patient.setGender(Patient.Gender.MALE);
        patient.setEmail("larry@bball.com");
        name = new PersonName();
        name.setFirstName("Larry");
        name.setMiddleName("J");
        name.setLastName("Parrot");
        patient.setName(name);
        patient.setPhone("4151234564");
        patient.setStatus(Patient.Status.APPROVED);
        entityManager.persist(patient);
        parrot = patient;

        patient = new Patient();
        patient.setPassword("weblogic");
        dob = Calendar.getInstance();
        dob.set(1983, 10, 29);
        patient.setDob(dob.getTime());
        address = new Address();
        address.setCity("Ponte Verde");
        address.setCountry("United States");
        address.setState("Florida");
        address.setStreet1("235 Montgomery St");
        address.setStreet2("Suite 15");
        address.setZip("32301");
        patient.setAddress(address);
        patient.setSsn("444444444");
        patient.setGender(Patient.Gender.MALE);
        patient.setEmail("charlie@star.com");
        name = new PersonName();
        name.setFirstName("Charlie");
        name.setMiddleName("E");
        name.setLastName("Florida");
        patient.setName(name);
        patient.setPhone("4151234564");
        entityManager.persist(patient);
        patient = new Patient();
        patient.setPassword("weblogic");
        dob = Calendar.getInstance();
        dob.set(1981, 8, 17);
        patient.setDob(dob.getTime());
        address = new Address();
        address.setCity("San Francisco");
        address.setCountry("United States");
        address.setState("California");
        address.setStreet1("1224 Post St");
        address.setStreet2("Suite 100");
        address.setZip("94115");
        patient.setAddress(address);
        patient.setSsn("333333333");
        patient.setGender(Patient.Gender.MALE);
        patient.setEmail("volley@ball.com");
        name = new PersonName();
        name.setFirstName("Gabrielle");
        name.setMiddleName("H");
        name.setLastName("Spiker");
        patient.setName(name);
        patient.setPhone("4151234564");
        patient.setStatus(Patient.Status.APPROVED);
        entityManager.persist(patient);
        spiker = patient;

        patient = new Patient();
        patient.setPassword("weblogic");
        dob = Calendar.getInstance();
        dob.set(1982, 2, 18);
        patient.setDob(dob.getTime());
        address = new Address();
        address.setCity("Ponte Verde");
        address.setCountry("United States");
        address.setState("Florida");
        address.setStreet1("235 Montgomery St");
        address.setStreet2("Suite 15");
        address.setZip("32301");
        patient.setAddress(address);
        patient.setSsn("888888888");
        patient.setGender(Patient.Gender.MALE);
        patient.setEmail("page@fish.com");
        name = new PersonName();
        name.setFirstName("Page");
        name.setMiddleName("A");
        name.setLastName("Trout");
        patient.setName(name);
        patient.setPhone("4151234564");
        patient.setStatus(Patient.Status.APPROVED);
        entityManager.persist(patient);
        trout = patient;
    }

    private void importRecords(EntityManager entityManager) {
        Record record = new Record();
        Calendar date = Calendar.getInstance();
        date.set(2009, 6, 18);
        record.setDate(date.getTime());
        record.setDiagnosis("Mild stroke.  Aspirin advised.");
        record.setNotes("Patient needs to stop smoking.");
        record.setSymptoms("Complains about chest pain.");

        VitalSigns vitalSigns = new VitalSigns();
        vitalSigns.setDiastolicBloodPressure(85);
        vitalSigns.setHeight(70);
        vitalSigns.setPulse(75);
        vitalSigns.setSystolicBloodPressure(125);
        vitalSigns.setTemperature(98);
        vitalSigns.setWeight(180);
        record.setVitalSigns(vitalSigns);

        record.setPhysician(oblige);
        record.setPatient(winner);

        Prescription prescription = new Prescription();
        prescription.setDatePrescribed(date.getTime());
        prescription.setDosage("100 tbls");
        prescription.setDrug("Advil");
        prescription.setFrequency("1/4hrs");
        prescription.setInstructions("");
        prescription.setRefillsRemaining(0);
        record.addPrescription(prescription);

        prescription = new Prescription();
        prescription.setDatePrescribed(date.getTime());
        prescription.setDosage("16 oz");
        prescription.setDrug("Drixoral");
        prescription.setFrequency("1tspn/4hrs");
        prescription.setInstructions("");
        prescription.setRefillsRemaining(0);
        record.addPrescription(prescription);

        entityManager.persist(record);

        record = new Record();
        date = Calendar.getInstance();
        date.set(2008, 5, 30);
        record.setDate(date.getTime());
        record.setDiagnosis("Common cold. Prescribed codiene cough syrup.");
        record.setNotes("Call back if not better in 10 days.");
        record.setSymptoms("Sneezing, coughing, stuffy head.");

        vitalSigns = new VitalSigns();
        vitalSigns.setDiastolicBloodPressure(85);
        vitalSigns.setHeight(70);
        vitalSigns.setPulse(75);
        vitalSigns.setSystolicBloodPressure(125);
        vitalSigns.setTemperature(98);
        vitalSigns.setWeight(180);
        record.setVitalSigns(vitalSigns);

        record.setPhysician(oblige);
        record.setPatient(winner);

        prescription = new Prescription();
        prescription.setDatePrescribed(date.getTime());
        prescription.setDosage("10 oz");
        prescription.setDrug("Codeine");
        prescription.setFrequency("1/6hrs");
        prescription.setInstructions("");
        prescription.setRefillsRemaining(1);
        record.addPrescription(prescription);

        entityManager.persist(record);

        record = new Record();
        date = Calendar.getInstance();
        date.set(2010, 7, 5);
        record.setDate(date.getTime());
        record.setDiagnosis("Severely sprained interior ligament.  Surgery required.");
        record.setNotes("Cast will be necessary before and after.");
        record.setSymptoms("Twisted knee while playing soccer.");

        vitalSigns = new VitalSigns();
        vitalSigns.setDiastolicBloodPressure(85);
        vitalSigns.setHeight(70);
        vitalSigns.setPulse(75);
        vitalSigns.setSystolicBloodPressure(125);
        vitalSigns.setTemperature(98);
        vitalSigns.setWeight(180);
        record.setVitalSigns(vitalSigns);

        record.setPhysician(oblige);
        record.setPatient(winner);

        entityManager.persist(record);

        record = new Record();
        date = Calendar.getInstance();
        date.set(2011, 4, 1);
        record.setDate(date.getTime());
        record.setDiagnosis("Allergic to coffee.  Drink tea.");
        record.setNotes("");
        record.setSymptoms("Drowsy all day.");

        vitalSigns = new VitalSigns();
        vitalSigns.setDiastolicBloodPressure(85);
        vitalSigns.setHeight(70);
        vitalSigns.setPulse(75);
        vitalSigns.setSystolicBloodPressure(125);
        vitalSigns.setTemperature(98);
        vitalSigns.setWeight(180);
        record.setVitalSigns(vitalSigns);

        record.setPhysician(oblige);
        record.setPatient(trout);

        entityManager.persist(record);

        record = new Record();
        date = Calendar.getInstance();
        date.set(2010, 4, 1);
        record.setDate(date.getTime());
        record.setDiagnosis("Patient is crazy.  Recommend politics.");
        record.setNotes("");
        record.setSymptoms("Overjoyed with everything.");

        vitalSigns = new VitalSigns();
        vitalSigns.setDiastolicBloodPressure(85);
        vitalSigns.setHeight(70);
        vitalSigns.setPulse(75);
        vitalSigns.setSystolicBloodPressure(125);
        vitalSigns.setTemperature(98);
        vitalSigns.setWeight(180);
        record.setVitalSigns(vitalSigns);

        record.setPhysician(oblige);
        record.setPatient(parrot);

        entityManager.persist(record);

        record = new Record();
        date = Calendar.getInstance();
        date.set(2010, 4, 1);
        record.setDate(date.getTime());
        record.setDiagnosis("Lite cast needed.");
        record.setNotes("At least 20 sprained ankles since 15.");
        record.setSymptoms("Sprained ankle.");

        vitalSigns = new VitalSigns();
        vitalSigns.setDiastolicBloodPressure(85);
        vitalSigns.setHeight(70);
        vitalSigns.setPulse(75);
        vitalSigns.setSystolicBloodPressure(125);
        vitalSigns.setTemperature(98);
        vitalSigns.setWeight(180);
        record.setVitalSigns(vitalSigns);

        record.setPhysician(oblige);
        record.setPatient(spiker);

        entityManager.persist(record);
    }
}
