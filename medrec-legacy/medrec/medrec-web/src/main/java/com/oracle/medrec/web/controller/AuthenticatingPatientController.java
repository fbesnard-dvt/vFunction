package com.oracle.medrec.web.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.oracle.medrec.model.Patient;
import com.oracle.medrec.web.Constants;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class AuthenticatingPatientController extends
        BaseAuthenticationController<Patient> {

    protected Patient authenticateAndReturnUser(String username, String password) {
        return getPatientService().authenticateAndReturnPatient(username,
                password);
    }

    protected String getFailureMessage() {
        return getPageContext().getMessage("message.invalidPatientLogin");
    }

    @Override
    protected String getLoginSuccessNavigation() {
        return Constants.PATIENT_BASE_PATH + Constants.PATIENT_HOME;
    }
}
