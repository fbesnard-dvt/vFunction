package com.oracle.medrec.service.impl.notification;

import java.util.logging.Logger;

import com.oracle.medrec.common.messaging.OnewayPayloadHandlerSupport;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Now we won't start transaction here.
 * 
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights
 *         reserved.
 */
@MessageDriven(mappedName = "com.oracle.medrec.jms.PatientNotificationQueue")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PatientNotifierBroker extends
        OnewayPayloadHandlerSupport<PatientToNotify> {

    private static final Logger LOGGER = Logger
            .getLogger(PatientNotifierBroker.class.getName());
    
    @EJB(beanName = "PatientNotifierImpl")
    private PatientNotifier patientNotifierImpl;

    public void handlePayload(PatientToNotify patientToNotify) {
        LOGGER.info("Received JMS message of patient notification");
        patientNotifierImpl.notifyPatient(patientToNotify.toPatient());
    }
}
