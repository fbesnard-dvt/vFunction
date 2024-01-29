package com.oracle.physician.service.delegate;

import java.io.Serializable;

import com.oracle.medrec.common.core.MethodCache;
import com.oracle.medrec.facade.model.RecordDetail;
import com.oracle.medrec.facade.model.RecordSummary;
import com.oracle.medrec.facade.model.RecordToCreate;
import com.oracle.physician.JaxRsProperties;
import com.oracle.physician.PhysicianSystemException;
import com.oracle.physician.service.RecordService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class RecordServiceDelegate implements RecordService, Serializable {

    private static final long serialVersionUID = 1L;

    private WebResource recordFacade;

    @PostConstruct
    public void init() throws Exception {
        ClientConfig config = new DefaultClientConfig();
        recordFacade = Client.create(config).resource(
                UriBuilder.fromUri(JaxRsProperties.RECORD_URI).build());
    }

    @RecordCache
    public void createRecord(RecordToCreate recordToCreate) {
        ClientResponse response = null;
        response = recordFacade.type(MediaType.APPLICATION_XML).post(
                ClientResponse.class, recordToCreate);
        if (response.getClientResponseStatus() != ClientResponse.Status.CREATED) {
            String msg = response.getEntity(String.class);
            throw new PhysicianSystemException("Failed : HTTP error code : "
                    + response.getStatus() + ", " + msg);
        }

    }

    @MethodCache
    public RecordSummary getRecordSummaryByPatientId(Long patientId) {
        RecordSummary rs = recordFacade.queryParam("patientId",
                Long.toString(patientId)).get(RecordSummary.class);
        return rs;
    }

    @MethodCache
    public RecordDetail getRecordDetail(Long id) {
        RecordDetail rd = recordFacade.path(Long.toString(id)).get(
                RecordDetail.class);
        return rd;
    }
}
