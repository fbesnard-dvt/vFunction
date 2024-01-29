package com.oracle.medrec.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.oracle.medrec.common.testing.EntityRepositoryTestCaseSupport;
import com.oracle.medrec.common.persistence.CriteriaPersistenceService;
import com.oracle.medrec.model.Administrator;

import junit.framework.JUnit4TestAdapter;

/**
 * {@link AdministratorServiceImpl} test case.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 16, 2007
 */
public class AdministratorServiceImplTestCase extends
        EntityRepositoryTestCaseSupport<AdministratorServiceImpl> {

    private Administrator administrator;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AdministratorServiceImplTestCase.class);
    }

    @Before
    public void before() {
        administrator = new Administrator();
        administrator.setPassword("password");
        administrator.setEmail("a@oracle.com");
        service.getEntityManager().persist(administrator);
        ((CriteriaPersistenceService) service).initBuilder();
    }

    @After
    public void after() {
        service.getEntityManager().remove(administrator);
    }

    @Test
    public void testAuthenticateAdministrator() {
        boolean result = service.authenticateAdministrator(
                administrator.getUsername(), administrator.getPassword());
        assertTrue(result);

        result = service.authenticateAdministrator("bogus",
                administrator.getPassword());
        assertFalse(result);

        result = service.authenticateAdministrator(administrator.getUsername(),
                "bogus");
        assertFalse(result);
    }

    @Test
    public void testAuthenticateAndReturnAdministrator() {
        Administrator user = service.authenticateAndReturnAdministrator(
                administrator.getUsername(), administrator.getPassword());
        assertNotNull(user);

        user = service.authenticateAndReturnAdministrator("bogus",
                administrator.getPassword());
        assertNull(user);

        user = service.authenticateAndReturnAdministrator(
                administrator.getUsername(), "bogus");
        assertNull(user);
    }

}
