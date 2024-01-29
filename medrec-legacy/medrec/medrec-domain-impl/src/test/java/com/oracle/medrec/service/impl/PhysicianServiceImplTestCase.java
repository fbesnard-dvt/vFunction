package com.oracle.medrec.service.impl;

import com.oracle.medrec.common.persistence.CriteriaPersistenceService;
import com.oracle.medrec.common.testing.EntityRepositoryTestCaseSupport;
import com.oracle.medrec.model.PersonName;
import com.oracle.medrec.model.Physician;
import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link PhysicianServiceImpl} test case.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class PhysicianServiceImplTestCase extends
        EntityRepositoryTestCaseSupport<PhysicianServiceImpl> {

    private Physician physician;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PhysicianServiceImplTestCase.class);
    }

    @Before
    public void before() {
        physician = new Physician();
        physician.setPassword("password");
        physician.setEmail("a@oracle.com");
        PersonName name = new PersonName();
        name.setFirstName("firstname");
        name.setMiddleName("middlename");
        name.setLastName("lastname");
        physician.setName(name);
        physician.setPhone("11111111");
        service.getEntityManager().persist(physician);
        ((CriteriaPersistenceService) service).initBuilder();
    }

    @After
    public void after() {
        service.getEntityManager().remove(physician);
    }

    @Test
    public void testAuthenticateAdministrator() {
        boolean result = service.authenticatePhysician(physician.getUsername(),
                physician.getPassword());
        assertTrue(result);

        result = service
                .authenticatePhysician("bogus", physician.getPassword());
        assertFalse(result);

        result = service
                .authenticatePhysician(physician.getUsername(), "bogus");
        assertFalse(result);
    }

    @Test
    public void testAuthenticateAndReturnAdministrator() {
        Physician user = service.authenticateAndReturnPhysician(
                physician.getUsername(), physician.getPassword());
        assertNotNull(user);

        user = service.authenticateAndReturnPhysician("bogus",
                physician.getPassword());
        assertNull(user);

        user = service.authenticateAndReturnPhysician(physician.getUsername(),
                "bogus");
        assertNull(user);
    }
}
