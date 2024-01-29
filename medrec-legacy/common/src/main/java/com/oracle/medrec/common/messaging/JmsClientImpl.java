package com.oracle.medrec.common.messaging;

import com.oracle.medrec.common.core.MethodParameterValidatingInterceptor;
import com.oracle.medrec.common.core.Nullable;
import com.oracle.medrec.common.naming.NamingClient;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Local({ JmsClient.class, MessageClient.class })
@Interceptors({ MethodParameterValidatingInterceptor.class })
public class JmsClientImpl implements JmsClient {

    // TODO resue threadlocal session when jms local transaction is used
    // TODO acknowledge mode, transaction...

    private static final Logger LOGGER = Logger.getLogger(JmsClientImpl.class
            .getName());

    private ResourceManager resourceManager;

    @EJB
    private MessageComposer messageComposer;

    @EJB(beanName = "NamingClientImpl")
    private NamingClient namingClient;

    // FIXME hard-coded
    // @Resource
    private String connectionFactoryName = "weblogic.jms.XAConnectionFactory";

    // @Resource

    private int connectionTestPeriod = 30;

    private int defaultDeliveryMode = Message.DEFAULT_DELIVERY_MODE;

    private int defaultPriority = Message.DEFAULT_PRIORITY;

    private long defaultTimeToLive = Message.DEFAULT_TIME_TO_LIVE;

    public void setMessageTransformer(MessageComposer messageComposer) {
        this.messageComposer = messageComposer;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void setConnectionFactoryName(String connectionFactoryName) {
        this.connectionFactoryName = connectionFactoryName;
    }

    public void setConnectionTestPeriod(int connectionTestPeriod) {
        this.connectionTestPeriod = connectionTestPeriod;
    }

    public void setNamingClient(NamingClient namingClient) {
        this.namingClient = namingClient;
    }

    // -----------------------------------------------------------------

    public boolean getDefaultPersistent() {
        return defaultDeliveryMode == DeliveryMode.PERSISTENT;
    }

    public void setDefaultPersistent(boolean persistent) {
        this.defaultDeliveryMode = persistent ? DeliveryMode.PERSISTENT
                : DeliveryMode.NON_PERSISTENT;
    }

    public int getDefaultPriority() {
        return defaultPriority;
    }

    public void setDefaultPriority(int priority) {
        this.defaultPriority = priority;
    }

    public long getDefaultTimeToLive() {
        return defaultTimeToLive;
    }

    public void setDefaultTimeToLive(long timeToLive) {
        this.defaultTimeToLive = timeToLive;
    }

    @PostConstruct
    public void init() {
        if (resourceManager == null) {
            resourceManager = ResourceManagerFactory.getResourceManager(
                    connectionTestPeriod, connectionFactoryName, namingClient);
        }
    }

    public String send(Destination destination,
            OutgoingMessage outgoingMessage, int deliveryMode, int priority,
            long timeToLive) {
        Connection connection = null;
        Session session = null;
        try {
            connection = resourceManager.getConnection();
            session = getSession();
            Message message = convertMessage(session, outgoingMessage);
            MessageProducer producer = session.createProducer(destination);
            producer.send(message, deliveryMode, priority, timeToLive);
            return message.getJMSMessageID();
        } catch (JMSException e) {
            throw new MessageException("Cannot send JMS message", e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    LOGGER.log(Level.WARNING, "Cannot close JMS session", e);
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

    public String send(String destinationName, Object payload) {
        return send(destinationName, payload, null);
    }

    public String sendObjectMessage(String destinationName, Serializable payload) {
        return send(destinationName, payload, ObjectMessage.class);
    }

    public String sendTextMessage(String destinationName, String payload) {
        return send(destinationName, payload, TextMessage.class);
    }

    public String send(String destinationName, Object payload,
            @Nullable Class<? extends Message> messageType) {
        return send(destinationName, payload, messageType, defaultDeliveryMode,
                defaultPriority, defaultTimeToLive);
    }

    public String send(String destinationName, Object payload,
            @Nullable Class<? extends Message> messageType, int deliveryMode,
            int priority, long timeToLive) {
        return send(getDestination(destinationName),
                createOutgoingMessage(payload, messageType), deliveryMode,
                priority, timeToLive);
    }

    public String send(Destination destination, OutgoingMessage outgoingMessage) {
        return send(destination, outgoingMessage, defaultDeliveryMode,
                defaultPriority, defaultTimeToLive);
    }

    public Object request(String destinationName, Object payload,
            @Nullable Class<? extends Message> messageType) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Object request(String destinationName, Object payload,
            @Nullable Class<? extends Message> messageType, int deliveryMode,
            int priority, long timeToLive) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public IncomingMessage request(Destination destination,
            OutgoingMessage outgoingMessage) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public IncomingMessage request(Destination destination,
            OutgoingMessage outgoingMessage, int deliveryMode, int priority,
            long timeToLive) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Object request(String destinationName, Object payload) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Serializable requestObjectMessage(String destinationName,
            Serializable payload) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String requestTextMessage(String destinationName, String payload) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void requestAsync(String destinationName, Object payload,
            @Nullable Class<? extends Message> messageType,
            PayloadHandler payloadHandler) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void requestAsync(String destinationName, Object payload,
            @Nullable Class<? extends Message> messageType,
            PayloadHandler payloadHandler, int deliveryMode, int priority,
            long timeToLive) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void requestAsync(Destination destination,
            OutgoingMessage outgoingMessage, PayloadHandler payloadHandler) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void requestAsync(Destination destination,
            OutgoingMessage outgoingMessage, PayloadHandler payloadHandler,
            int deliveryMode, int priority, long timeToLive) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void requestAsync(String destinationName, Object payload,
            PayloadHandler payloadHandler) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void requestObjectMessageAsync(String destinationName,
            Serializable payload, PayloadHandler payloadHandler) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void requestTextMessageAsync(String destinationName, Object payload,
            PayloadHandler payloadHandler) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Destination getDestination(String destinationName) {
        return resourceManager.getDestination(destinationName);
    }

    public OutgoingMessage createOutgoingMessage(Object payload) {
        OutgoingMessage outgoingMessage = new OutgoingMessageImpl();
        outgoingMessage.setPayload(payload);
        return outgoingMessage;
    }

    public OutgoingMessage createOutgoingMessage(Object payload,
            Class<? extends Message> messageType) {
        OutgoingMessage outgoingMessage = createOutgoingMessage(payload);
        outgoingMessage.setType(messageType);
        return outgoingMessage;
    }

    protected Message convertMessage(Session session,
            OutgoingMessage outgoingMessage) throws JMSException {
        Message message = messageComposer.composeMessage(session,
                outgoingMessage.getType(), outgoingMessage.getPayload());

        if (outgoingMessage.getJmsCorrelationId() != null) {
            message.setJMSCorrelationID(outgoingMessage.getJmsCorrelationId());
        }
        if (outgoingMessage.getJmsCorrelationIdAsBytes() != null) {
            message.setJMSCorrelationIDAsBytes(outgoingMessage
                    .getJmsCorrelationIdAsBytes());
        }
        if (outgoingMessage.getJmsReplyTo() != null) {
            message.setJMSReplyTo(outgoingMessage.getJmsReplyTo());
        }
        for (String propertyName : outgoingMessage.getPropertyNames()) {
            message.setObjectProperty(propertyName,
                    outgoingMessage.getProperty(propertyName));
        }
        return message;
    }

    public Connection getConnection() throws JMSException {
        return resourceManager.getConnection();
    }

    public Session getSession() throws JMSException {
        return getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

}
