package com.oracle.medrec.common.messaging;

import javax.jms.Message;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface OutgoingMessage extends JmsMessage {

    void setPayload(Object payload);

    void setType(Class<? extends Message> type);

    void clearPayload();

}
