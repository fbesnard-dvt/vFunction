package com.oracle.medrec.service.impl;

import com.oracle.medrec.model.Administrator;
import com.oracle.medrec.service.AdministratorService;

import javax.ejb.Stateless;

/**
 * Administrator buisness service implementation. which is responsible for all
 * business operations to administrator.
 * 
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
public class AdministratorServiceImpl extends
        BaseUserServiceImpl<Administrator> implements AdministratorService {

    public boolean authenticateAdministrator(String username, String password) {
        return super.authenticateUser(username, password);
    }

    public Administrator authenticateAndReturnAdministrator(String username,
            String password) {
        return super.authenticateAndReturnUser(username, password);
    }

}
