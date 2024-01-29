package com.oracle.medrec.service.impl.notification;

import junit.framework.JUnit4TestAdapter;
import static org.easymock.EasyMock.createMock;
import org.junit.Test;

/**
 * {@link PatientNotifierImpl} test case.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 16, 2007
 */
public class PatientNotifierImplTestCase {

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PatientNotifierImplTestCase.class);
    }

    @Test
    public void notifyPatient() {
        com.oracle.medrec.common.mail.MailClient mg = createMock(com.oracle.medrec.common.mail.MailClient.class);

        PatientNotifierImpl impl = new PatientNotifierImpl();
        impl.setMailGateway(mg);

        // TODO : to be implement
    }
}
