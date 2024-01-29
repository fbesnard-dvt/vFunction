package com.oracle.medrec.common.messaging;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface RequestReplyPayloadHandler<I, O> {

    O handlePayload(I incomingMessagePayload);
}
