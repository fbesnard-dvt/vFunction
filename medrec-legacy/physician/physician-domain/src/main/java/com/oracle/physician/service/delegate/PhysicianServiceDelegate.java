package com.oracle.physician.service.delegate;

import com.oracle.medrec.common.core.MethodCache;
import com.oracle.medrec.facade.model.AuthenticatedPhysician;
import com.oracle.physician.JaxRsProperties;
import com.oracle.physician.service.PhysicianService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.UriBuilder;

import java.io.Serializable;

/**
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class PhysicianServiceDelegate implements PhysicianService, Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final int NO_CONTENT = 204;
    
    private WebResource physicianFacade;

    @PostConstruct
    public void init() throws Exception {
        ClientConfig config = new DefaultClientConfig();
        physicianFacade = Client.create(config).resource(
                UriBuilder.fromUri(JaxRsProperties.PHYSICIAN_URI).build());
    }

    @MethodCache
    public AuthenticatedPhysician authenticateAndReturnPhysician(
            String username, String password) {
        try {
            return physicianFacade.queryParam("username",
                    username).queryParam("password", password).get(AuthenticatedPhysician.class);
        } catch (UniformInterfaceException e) {
            if (e.getResponse().getStatus() == NO_CONTENT) {
                return null;
            } else {
                throw e;
            }             
        }
    }
    
}
