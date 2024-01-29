package com.oracle.physician.web.controller;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.oracle.medrec.facade.model.AuthenticatedPhysician;
import com.oracle.physician.web.Constants;

/**
 * AuthenticationController is a JSF ManagedBean that is responsible for
 * authentication of physician.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Named
@RequestScoped
public class AuthenticationController extends BasePhysicianPageController {

    private static final Logger LOGGER = Logger
            .getLogger(AuthenticationController.class.getName());

    private String username;

    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String login() {
        LOGGER.info("Physician " + username + " is loging in.");
        AuthenticatedPhysician physician = getPhysicianService()
                .authenticateAndReturnPhysician(username, password);
        LOGGER.info("Physician " + username + " logged in.");

        if (physician != null) {
            getPageContext().getSessionMap().put(
                    Constants.AUTHENTICATED_USER_SESSION_KEY, physician);
            return Constants.PHYSICIAN_BASE_PATH + Constants.PHYSICIAN_HOME;
        }
        getPageContext().addGlobalOnlyErrorMessageWithKey(
                "message.invalidLogin");
        return Constants.LOGIN_PATH;
    }

    public String logout() {
        getPageContext().getSessionMap().remove(
                Constants.AUTHENTICATED_USER_SESSION_KEY);
        getPageContext().invalidateSession();
        return Constants.LOGIN_PATH;
    }
}
