package com.oracle.medrec.common.messaging;

import java.io.Serializable;

/**
 * Class acting as gateway to messaging system. It is decoupled from any
 * particular API and keeps as simple as possible.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface MessageClient {

    // Oneway -----------------------------------------------

    String send(String destinationName, Object payload);

    String sendObjectMessage(String destinationName, Serializable payload);

    String sendTextMessage(String destinationName, String payload);

    // Sync request-reply -----------------------------------------------

    Object request(String destinationName, Object payload);

    Serializable requestObjectMessage(String destinationName,
            Serializable payload);

    String requestTextMessage(String destinationName, String payload);

    // Async request-reply -----------------------------------------------

    void requestAsync(String destinationName, Object payload,
            PayloadHandler payloadHandler);

    void requestObjectMessageAsync(String destinationName,
            Serializable payload, PayloadHandler payloadHandler);

    void requestTextMessageAsync(String destinationName, Object payload,
            PayloadHandler payloadHandler);
}
