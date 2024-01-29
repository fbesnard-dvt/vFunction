package com.oracle.medrec.common.messaging;

import javax.jms.Destination;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface IncomingMessage extends JmsMessage {

    String getJmsMessageId();

    void setJmsMessageId(String jmsMessageId);

    long getJmsTimestamp();

    void setJmsTimestamp(long jmsTimestamp);

    Destination getJmsDestination();

    void setJmsDestination(Destination jmsDestination);

    boolean getJmsRedelivered();

    void setJmsRedelivered(boolean jmsRedelivered);

    String getJmsType();

    void setJmsType(String jmsType);

    long getJmsExpiration();

    void setJmsExpiration(long jmsExpiration);

    int getJmsDeliveryMode();

    void setJmsDeliveryMode(int jmsDeliveryMode);

    int getJmsPriority();

    void setJmsPriority(int jmsPriority);

    void acknowledge();

}
