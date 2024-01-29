package com.oracle.medrec.service;

import com.oracle.medrec.model.Administrator;

/**
 * Administrator business service interface.
 * 
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface AdministratorService {

    /**
     * Authenticate administrator's username and password.
     * 
     * @param username
     * @param password
     * @return
     */
    boolean authenticateAdministrator(String username, String password);

    /**
     * Authenticate administrator's username and password. Then return
     * {@link Administrator} entity.
     * 
     * @param username
     * @param password
     * @return
     */
    Administrator authenticateAndReturnAdministrator(String username,
            String password);

}
