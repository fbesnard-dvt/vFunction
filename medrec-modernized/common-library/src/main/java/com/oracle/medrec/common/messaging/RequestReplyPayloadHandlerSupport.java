package com.oracle.medrec.common.messaging;

import com.oracle.medrec.common.util.ClassUtils;

import javax.ejb.EJB;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * TODO
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public abstract class RequestReplyPayloadHandlerSupport<I, O> extends
        BasePayloadHandlerSupport<I> implements
        RequestReplyPayloadHandler<I, O> {

    // the generic type for outgoing message isn't necessarily needed

    // TODO the config not consistent with incoming destination
    private String outgoingDestinationName;

    private Class<? extends Message> outgoingMessageType;

    @EJB
    private JmsClient jmsClient;

    public void setJmsClient(JmsClient jmsClient) {
        this.jmsClient = jmsClient;
    }

    public void setOutgoingDestinationName(String outgoingDestinationName) {
        this.outgoingDestinationName = outgoingDestinationName;
    }

    public void setOutgoingMessageTypeName(String outgoingMessageTypeName) {
        Class clazz = ClassUtils.forName(outgoingMessageTypeName);
        if (Message.class.isAssignableFrom(clazz)) {
            outgoingMessageType = clazz;
        }
        throw new IllegalArgumentException("Class '" + outgoingMessageTypeName
                + "' isn't subclass of '" + Message.class.getName() + "'");
    }

    protected final void doOnMessage(Message incomingMessage)
            throws JMSException {
        Object outgoingPayload = handlePayload(extractIncommingPayload(incomingMessage));
        Destination outgoingDestination = null;
        if (getOutgoingDestinationName() != null) {
            outgoingDestination = jmsClient
                    .getDestination(getOutgoingDestinationName());
        } else {
            outgoingDestination = incomingMessage.getJMSReplyTo();
        }
        if (outgoingDestination == null) {
            throw new IllegalArgumentException(
                    "Cannot find outgoing destination");
        }
        OutgoingMessage outgoingMessage = jmsClient.createOutgoingMessage(
                outgoingPayload, getOutgoingMessageType());
        outgoingMessage.setJmsCorrelationId(incomingMessage.getJMSMessageID());
        jmsClient.send(outgoingDestination, outgoingMessage);
    }

    protected String getOutgoingDestinationName() {
        return outgoingDestinationName;
    }

    protected Class<? extends Message> getOutgoingMessageType() {
        return outgoingMessageType;
    }
}
