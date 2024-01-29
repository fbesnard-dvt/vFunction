package com.oracle.medrec.web.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.oracle.medrec.model.User;
import com.oracle.medrec.web.Constants;
import com.oracle.medrec.web.login.Password;
import com.oracle.medrec.web.login.Username;


/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public abstract class BaseAuthenticationController<T extends User> extends
        BaseMedRecPageController {

    private static final Logger LOGGER = Logger
            .getLogger(BaseAuthenticationController.class.getName());

    @Username
    @Inject
    private String username;

    @Password
    @Inject
    private String password;

    public String login() {
        LOGGER.info("Username: " + username);
        T user = authenticateAndReturnUser(username, password);

        if (user != null) {
            getPageContext().getSessionMap().put(
                    Constants.AUTHENTICATED_USER_SESSION_KEY, user);
            LOGGER.info("User " + username + " logged in");
            return getLoginSuccessNavigation();
        }
        LOGGER.log(Level.FINER, "Username: " + username);
        getPageContext().addGlobalOnlyErrorMessage(getFailureMessage());

        return Constants.INDEX_PATH;
    }

    @SuppressWarnings("unchecked")
    public String logout() {
        T user = (T) getPageContext().getSessionMap().remove(
                Constants.AUTHENTICATED_USER_SESSION_KEY);
        getPageContext().invalidateSession();
        if (user != null) {
            LOGGER.info("User " + user.getUsername() + " logged out");
        }
        return Constants.INDEX_PATH;
    }

    protected abstract T authenticateAndReturnUser(String username,
            String password);

    protected abstract String getLoginSuccessNavigation();

    protected abstract String getFailureMessage();

}
