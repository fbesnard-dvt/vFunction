package com.oracle.medrec.common.messaging;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class IncomingMessageImpl implements IncomingMessage {

    private Message message;

    private MessageComposer messageComposer;

    public IncomingMessageImpl(Message message, MessageComposer messageComposer) {
        this.message = message;
        this.messageComposer = messageComposer;
    }

    /**
     * The concrete implementation will be returned other than something like
     * "TextMessage", so that "instanceof" should be used to determine the
     * message type.
     */
    public Class<? extends Message> getType() {
        return message.getClass();
    }

    public Object getPayload() {
        try {
            return messageComposer.extractPayload(message);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public String getJmsMessageId() {
        try {
            return message.getJMSMessageID();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsMessageId(String s) {
        try {
            message.setJMSMessageID(s);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public long getJmsTimestamp() {
        try {
            return message.getJMSTimestamp();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsTimestamp(long l) {
        try {
            message.setJMSTimestamp(l);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public byte[] getJmsCorrelationIdAsBytes() {
        try {
            return message.getJMSCorrelationIDAsBytes();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsCorrelationIdAsBytes(byte[] bytes) {
        try {
            message.setJMSCorrelationIDAsBytes(bytes);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsCorrelationId(String s) {
        try {
            message.setJMSCorrelationID(s);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public String getJmsCorrelationId() {
        try {
            return message.getJMSCorrelationID();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public Destination getJmsReplyTo() {
        try {
            return message.getJMSReplyTo();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsReplyTo(Destination destination) {
        try {
            message.setJMSReplyTo(destination);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public Destination getJmsDestination() {
        try {
            return message.getJMSDestination();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsDestination(Destination destination) {
        try {
            message.setJMSDestination(destination);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public int getJmsDeliveryMode() {
        try {
            return message.getJMSDeliveryMode();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsDeliveryMode(int i) {
        try {
            message.setJMSDeliveryMode(i);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public boolean getJmsRedelivered() {
        try {
            return message.getJMSRedelivered();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsRedelivered(boolean b) {
        try {
            message.setJMSRedelivered(b);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public String getJmsType() {
        try {
            return message.getJMSType();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsType(String s) {
        try {
            message.setJMSType(s);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public long getJmsExpiration() {
        try {
            return message.getJMSExpiration();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsExpiration(long l) {
        try {
            message.setJMSExpiration(l);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public int getJmsPriority() {
        try {
            return message.getJMSPriority();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setJmsPriority(int i) {
        try {
            message.setJMSPriority(i);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void clearProperties() {
        try {
            message.clearProperties();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public boolean propertyExists(String s) {
        try {
            return message.propertyExists(s);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public Set<String> getPropertyNames() {
        Set<String> propertyNames = new HashSet<String>();
        try {
            Enumeration<?> enumeration = message.getPropertyNames();
            while (enumeration.hasMoreElements()) {
                propertyNames.add((String) enumeration.nextElement());
            }
        } catch (JMSException e) {
            throw new MessageException(e);
        }
        return propertyNames;
    }

    public Object getProperty(String s) {
        try {
            return message.getObjectProperty(s);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void setProperty(String s, Object o) {
        try {
            message.setObjectProperty(s, o);
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }

    public void acknowledge() {
        try {
            message.acknowledge();
        } catch (JMSException e) {
            throw new MessageException(e);
        }
    }
}
