package com.oracle.medrec.common.messaging;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;

/**
 * Class that manages the JMS administered objects.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface ResourceManager {

    Destination getDestination(String destinationName);

    Connection getConnection() throws JMSException;
}
