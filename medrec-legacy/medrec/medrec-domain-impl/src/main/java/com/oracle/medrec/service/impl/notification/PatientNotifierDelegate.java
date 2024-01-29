package com.oracle.medrec.service.impl.notification;

import com.oracle.medrec.common.messaging.MessageClient;
import com.oracle.medrec.model.Patient;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * This Stateless Session Bean is regarded as a Message Client. The program of
 * noticing patient follows 3 step below: <br>
 * 1. Using JMS tools of medrec-common to send message to a certain queue. <br>
 * 2. On the other side, a MDB receives this message from the same queue. <br>
 * 3. The MDB's onMessge() method invokes mailClient to send a mail telling the
 * result of the registration. Of course, it can't really send the mails with
 * these non-existent addresses.
 * 
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights
 *         reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PatientNotifierDelegate implements PatientNotifier {

    @EJB
    private MessageClient messageClient;

    // TODO hard-coded value
    private String queueName = "com.oracle.medrec.jms.PatientNotificationQueue";

    public void setMessageGateway(MessageClient messageClient) {
        this.messageClient = messageClient;
    }

    public void notifyPatient(Patient patient) {
        messageClient.send(queueName, new PatientToNotify(patient));
    }
}
