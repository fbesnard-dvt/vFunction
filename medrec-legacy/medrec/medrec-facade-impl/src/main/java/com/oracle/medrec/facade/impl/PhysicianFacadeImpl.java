package com.oracle.medrec.facade.impl;

import com.oracle.medrec.common.core.MethodParameterValidatingInterceptor;
import com.oracle.medrec.common.core.ThrowableLoggingInterceptor;
import com.oracle.medrec.facade.PhysicianFacade;
import com.oracle.medrec.facade.model.AuthenticatedPhysician;
import com.oracle.medrec.model.Physician;
import com.oracle.medrec.service.PhysicianService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Interceptors({ MethodParameterValidatingInterceptor.class,
        ThrowableLoggingInterceptor.class })
public class PhysicianFacadeImpl implements PhysicianFacade {

    @EJB
    private PhysicianService physicianService;

    public void setPhysicianService(PhysicianService physicianService) {
        this.physicianService = physicianService;
    }

    public boolean authenticatePhysician(String username, String password) {
        return physicianService.authenticatePhysician(username, password);
    }

    public AuthenticatedPhysician authenticateAndReturnPhysician(
            String username, String password) {
        Physician physician = physicianService.authenticateAndReturnPhysician(
                username, password);
        if (physician == null) {
            return null;
        }
        return new AuthenticatedPhysician(physician);
    }
}
