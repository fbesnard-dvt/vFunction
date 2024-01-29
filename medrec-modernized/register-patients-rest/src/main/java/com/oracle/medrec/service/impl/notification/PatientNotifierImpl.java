package com.oracle.medrec.service.impl.notification;

import com.oracle.medrec.common.mail.Mail;
import com.oracle.medrec.common.mail.MailClient;
import com.oracle.medrec.model.Patient;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Uses external template to define mail.
 * 
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights
 *         reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PatientNotifierImpl implements PatientNotifier {

    @EJB
    private MailClient mailClient;

    public void setMailGateway(MailClient mailClient) {
        this.mailClient = mailClient;
    }

    public void notifyPatient(Patient patient) {
        // Currently no email will be actually sent out
        Mail mail = mailClient.createMail();
        mail.setTo(patient.getEmail());
        mail.setFrom("Jack@sample.com");
        if (patient.isApproved()) {
            mail.setSubject("");
            mail.setContent("");
        } else if (patient.isDenied()) {
            mail.setSubject("");
            mail.setContent("");
        } else {
            throw new IllegalArgumentException(
                    "Patient's status should be either approved or denied");
        }
        mailClient.sendMail(mail);
    }
}
