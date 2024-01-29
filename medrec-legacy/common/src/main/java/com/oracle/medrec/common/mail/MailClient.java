package com.oracle.medrec.common.mail;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface MailClient {

    void sendMail(Mail mail);

    Mail createMail();
}
