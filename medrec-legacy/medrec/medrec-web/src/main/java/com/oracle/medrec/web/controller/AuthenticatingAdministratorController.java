package com.oracle.medrec.web.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.oracle.medrec.model.Administrator;
import com.oracle.medrec.web.Constants;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class AuthenticatingAdministratorController extends
        BaseAuthenticationController<Administrator> {

    protected Administrator authenticateAndReturnUser(String username,
            String password) {
        return getAdministratorService().authenticateAndReturnAdministrator(
                username, password);
    }

    protected String getFailureMessage() {
        return getPageContext().getMessage("message.invalidLogin");
    }

    @Override
    protected String getLoginSuccessNavigation() {
        return Constants.ADMIN_BASE_PATH + Constants.ADMIN_HOME;
    }

}
