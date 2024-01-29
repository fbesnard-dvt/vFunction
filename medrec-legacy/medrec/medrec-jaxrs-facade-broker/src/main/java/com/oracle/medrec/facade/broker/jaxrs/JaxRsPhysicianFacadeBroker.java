package com.oracle.medrec.facade.broker.jaxrs;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.oracle.medrec.facade.PhysicianFacade;
import com.oracle.medrec.facade.model.AuthenticatedPhysician;

/**
 * Physician Facade's jax-rs broker.
 * 
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Path("/physician")
@Stateless
public class JaxRsPhysicianFacadeBroker {

    private static final Logger LOGGER = Logger
            .getLogger(JaxRsPhysicianFacadeBroker.class.getName());

    @EJB
    private PhysicianFacade physicianFacade;

    /**
     * Authenticate physician and return entity.
     * 
     * @param username
     * @param password
     * @return {@link AuthenticatedPhysician}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticatedPhysician authenticateAndReturnPhysician(
            @QueryParam("username") String username,
            @QueryParam("password") String password) {
        LOGGER.info("Authenticate use " + username);
        return physicianFacade.authenticateAndReturnPhysician(username,
                password);
    }

}