package com.oracle.medrec.common.messaging;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * JMS-specific message client.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface JmsClient extends MessageClient {

    // Methods for the default configurations of message delivery
    // ------------------------------------

    void setDefaultPersistent(boolean persistent);

    boolean getDefaultPersistent();

    void setDefaultPriority(int priority);

    int getDefaultPriority();

    void setDefaultTimeToLive(long timeToLive);

    long getDefaultTimeToLive();

    // Methods to produce message with different MEPs
    // ------------------------------------------------

    String send(String destinationName, Object payload,
            Class<? extends Message> messageType);

    String send(String destinationName, Object payload,
            Class<? extends Message> messageType, int deliveryMode,
            int priority, long timeToLive);

    String send(Destination destination, OutgoingMessage outgoingMessage);

    String send(Destination destination, OutgoingMessage outgoingMessage,
            int deliveryMode, int priority, long timeToLive);

    Object request(String destinationName, Object payload,
            Class<? extends Message> messageType);

    Object request(String destinationName, Object payload,
            Class<? extends Message> messageType, int deliveryMode,
            int priority, long timeToLive);

    IncomingMessage request(Destination destination,
            OutgoingMessage outgoingMessage);

    IncomingMessage request(Destination destination,
            OutgoingMessage outgoingMessage, int deliveryMode, int priority,
            long timeToLive);

    void requestAsync(String destinationName, Object payload,
            Class<? extends Message> messageType, PayloadHandler payloadHandler);

    void requestAsync(String destinationName, Object payload,
            Class<? extends Message> messageType,
            PayloadHandler payloadHandler, int deliveryMode, int priority,
            long timeToLive);

    void requestAsync(Destination destination, OutgoingMessage outgoingMessage,
            PayloadHandler payloadHandler);

    void requestAsync(Destination destination, OutgoingMessage outgoingMessage,
            PayloadHandler payloadHandler, int deliveryMode, int priority,
            long timeToLive);

    /**
     * Lookup a JMS destination and resulting destination will be managed by
     * <code>ResourceManager</code>
     */
    Destination getDestination(String destinationName);

    /**
     * Factory method to create an outgoing message with the default
     * implementation.
     */
    OutgoingMessage createOutgoingMessage(Object payload);

    OutgoingMessage createOutgoingMessage(Object payload,
            Class<? extends Message> messageType);

    // Methods directly exposing JMS APIs for those scenarioes that cannot be
    // covered by the gateway API --------------

    Session getSession() throws JMSException;

    Connection getConnection() throws JMSException;
}
