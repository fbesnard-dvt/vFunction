package com.oracle.medrec.common.messaging;

import com.oracle.medrec.common.naming.NamingClient;
import com.oracle.medrec.common.util.ThrowableUtils;

import javax.annotation.PostConstruct;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.TemporaryQueue;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Non-EJB.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class ResourceManagerImpl implements ResourceManager {

    // TODO In WLS EJB apps, connection, session, etc should be transparently
    // cached?
    // TODO Although it's supposed to be used by EJB, we ignore some EJB
    // restrictions.
    // TODO check concurrency
    // TODO for now heart beat is useless since we can only support local
    // resource lookup.
    // also, we should auto disable it when message server is local

    private static final Logger LOGGER = Logger
            .getLogger(ResourceManagerImpl.class.getName());

    private NamingClient namingClient;

    /**
     * If set to a positive value, connection will be tested in the period
     * specified by it, and the ConnectionFactory must be configured to support
     * temporaray destination; otherwise, connection test won't happen.
     */
    private int connectionTestPeriod;

    private final Map<String, Destination> destinations = new ConcurrentHashMap<String, Destination>();

    // TODO for now JSE timer gets used to spawn thread... would be illegal
    // inside container...
    private final Timer timer = new Timer(true);

    private final ExceptionListener exceptionListener = new ExceptionListener() {
        public void onException(JMSException jmsException) {
            handleFailure(jmsException);
        }
    };

    private String connectionFactoryName;

    private ConnectionFactory connectionFactory;

    private boolean isValidConnectionFactory = true;

    public void setNamingClient(NamingClient namingClient) {
        this.namingClient = namingClient;
    }

    public void setConnectionTestPeriod(int connectionTestPeriod) {
        this.connectionTestPeriod = connectionTestPeriod;
    }

    public void setConntectionFactoryName(String connectionFactoryName) {
        this.connectionFactoryName = connectionFactoryName;
    }

    @PostConstruct
    public void init() {
        // TODO hard-coded JNDI
        connectionFactory = namingClient.lookup(ConnectionFactory.class,
                connectionFactoryName);
        if (connectionTestPeriod > 0) {
            timer.schedule(new TimerTask() {
                public void run() {
                    testConnection();
                }
            }, connectionTestPeriod * 1000, connectionTestPeriod * 1000);
            LOGGER.info("JMS connection monitor started, and it will test connection every "
                    + connectionTestPeriod + "s");
        }
    }

    public Destination getDestination(String destinationName) {
        Destination destination = destinations.get(destinationName);
        if (destination == null) {
            destination = namingClient.lookup(Destination.class,
                    destinationName);
            destinations.put(destinationName, destination);
        }
        return destination;
    }

    public Connection getConnection() throws JMSException {
        if (!isValidConnectionFactory) {
            connectionFactory = namingClient.lookup(ConnectionFactory.class,
                    connectionFactoryName);
        }
        isValidConnectionFactory = true;
        Connection connection = connectionFactory.createConnection();
        connection.setExceptionListener(exceptionListener);
        return connection;
    }

    private void handleFailure(JMSException jmsException) {
        if (!isValidConnectionFactory) {
            return;
        }
        LOGGER.log(Level.SEVERE, "JMS connection test failed", jmsException);
        LOGGER.log(Level.SEVERE, ThrowableUtils.getStackTrace(jmsException));

        isValidConnectionFactory = false;
        destinations.clear();
        LOGGER.info("Invalidated JMS resource references");
    }

    private void testConnection() {
        LOGGER.log(Level.FINER, "Testing JMS connection...");
        Connection connection = null;
        TemporaryQueue temporaryQueue = null;
        try {
            connection = connectionFactory.createConnection();
            temporaryQueue = connection.createSession(false, 0)
                    .createTemporaryQueue();
        } catch (JMSException e) {
            handleFailure(e);
        } finally {
            // if connection is cached, the temporary queue may not be deleted
            // automatically...
            if (temporaryQueue != null) {
                try {
                    temporaryQueue.delete();
                } catch (JMSException e) {
                    LOGGER.log(Level.WARNING,
                            "Cannot delete JMS temporary queue", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    LOGGER.log(Level.WARNING, "Cannot close JMS connection", e);
                }
            }
        }
    }
}
