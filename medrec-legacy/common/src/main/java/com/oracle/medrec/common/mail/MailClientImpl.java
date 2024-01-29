package com.oracle.medrec.common.mail;

import java.util.logging.Logger;

import com.oracle.medrec.common.core.MethodParameterValidatingInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.mail.Session;

/**
 * Currently this is just a dummy stub.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Interceptors({ MethodParameterValidatingInterceptor.class })
public class MailClientImpl implements MailClient {

    private static final Logger LOGGER = Logger.getLogger(MailClientImpl.class
            .getName());

    // TODO allow for customization
    // @Resource(name = "mail/Session")
    @SuppressWarnings("unused")
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public void sendMail(Mail mail) {
        // try {
        // Message message = new MimeMessage(session);
        // message.setFrom(new InternetAddress(mail.getFrom()));
        // message.setRecipient(Message.RecipientType.TO, new
        // InternetAddress(mail.getTo()));
        // message.setSubject(mail.getSubject());
        // message.setSentDate(new Date());
        // message.setContent(mail.getContent(), "text/plain");
        // Transport.send(message);
        // } catch (MessagingException e) {
        // throw new MailException("Cannot send mail", e);
        // }
        LOGGER.info("The mail sent successfully");
    }

    public Mail createMail() {
        return new MailImpl();
    }
}
