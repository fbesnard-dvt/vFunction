package com.oracle.medrec.common.util;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test suite of com.oracle.medrec.common.util package.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 13, 2007
 */
public class AllTestCases extends TestCase {
    public AllTestCases(String name) {
        super(name);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new JUnit4TestAdapter(ClassUtilsTestCase.class));
        suite.addTest(new JUnit4TestAdapter(StringUtilsTestCase.class));
        suite.addTest(new JUnit4TestAdapter(ThrowableUtilsTestCase.class));
        return suite;
    }
}
