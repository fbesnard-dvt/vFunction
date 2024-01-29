package com.oracle.medrec.common.messaging;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.Serializable;

/**
 * TODO incorporate common converter
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MessageComposerImpl implements MessageComposer {

    // TODO more message types

    public Object extractPayload(Message message) throws JMSException {
        if (message instanceof ObjectMessage) {
            return ((ObjectMessage) message).getObject();
        } else if (message instanceof TextMessage) {
            return ((TextMessage) message).getText();
        }
        throw new IllegalArgumentException("Unsupported message type: "
                + message.getClass().getName());
    }

    public Message composeMessage(Session session,
            Class<? extends Message> messageType, Object payload)
            throws JMSException {
        if (messageType == null) {
            if (payload instanceof String) {
                return composeMessage(session, TextMessage.class, payload);
            } else {
                return composeMessage(session, ObjectMessage.class, payload);
            }
        } else if (ObjectMessage.class.isAssignableFrom(messageType)) {
            if (payload instanceof Serializable) {
                return session.createObjectMessage((Serializable) payload);
            }
            throw new IllegalArgumentException(
                    "The instance to be wrapped in ObjectMessage isn't serializable: "
                            + payload.getClass());
        } else if (TextMessage.class.isAssignableFrom(messageType)) {
            return session.createTextMessage(payload.toString());
        }
        throw new IllegalArgumentException("Unsupported message type: "
                + messageType);
    }
}
