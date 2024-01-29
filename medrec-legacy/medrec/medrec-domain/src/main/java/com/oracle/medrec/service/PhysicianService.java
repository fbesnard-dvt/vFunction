package com.oracle.medrec.service;

import com.oracle.medrec.model.Physician;

/**
 * Physician business service interface.
 * 
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface PhysicianService {

    /**
     * Authenticates physician by user name and password.
     * 
     * @param username
     * @param password
     * @return
     */
    boolean authenticatePhysician(String username, String password);

    /**
     * Authenticates physician by user name and password. Then return
     * {@link Physician} entity.
     * 
     * @param username
     * @param password
     * @return
     */
    Physician authenticateAndReturnPhysician(String username, String password);

}
