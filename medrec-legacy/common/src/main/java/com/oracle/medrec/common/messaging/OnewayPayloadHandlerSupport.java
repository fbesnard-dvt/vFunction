package com.oracle.medrec.common.messaging;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public abstract class OnewayPayloadHandlerSupport<T> extends
        BasePayloadHandlerSupport<T> implements PayloadHandler<T> {

    protected void doOnMessage(Message message) throws JMSException {
        handlePayload(extractIncommingPayload(message));
    }
}
