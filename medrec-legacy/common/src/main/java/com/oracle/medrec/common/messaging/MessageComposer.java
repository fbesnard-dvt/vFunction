package com.oracle.medrec.common.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface MessageComposer {

    Message composeMessage(Session session,
            Class<? extends Message> messageType, Object payload)
            throws JMSException;

    Object extractPayload(Message message) throws JMSException;
}
