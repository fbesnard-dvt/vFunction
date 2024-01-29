package com.oracle.medrec.service.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test suite of com.oracle.medrec.service.impl package.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class AllTestCases extends TestCase {
    public AllTestCases(String name) {
        super(name);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(com.oracle.medrec.service.impl.notification.AllTestCases
                .suite());
        suite.addTest(AdministratorServiceImplTestCase.suite());
        suite.addTest(PatientServiceImplTestCase.suite());
        suite.addTest(PhysicianServiceImplTestCase.suite());
        suite.addTest(RecordServiceImplTestCase.suite());
        return suite;
    }
}
