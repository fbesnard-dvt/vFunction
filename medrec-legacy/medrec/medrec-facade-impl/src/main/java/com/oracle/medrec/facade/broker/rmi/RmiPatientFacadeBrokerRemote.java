package com.oracle.medrec.facade.broker.rmi;

import javax.ejb.Remote;

import com.oracle.medrec.facade.PatientFacade;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Remote
public interface RmiPatientFacadeBrokerRemote extends PatientFacade {

}
