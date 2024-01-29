package com.oracle.medrec.registernewpatients;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.oracle.medrec.registernewpatients.dto.*;
import com.oracle.medrec.service.impl.notification.PatientNotifierBroker;

@Stateless
@Path("/patientNotifierBroker")
public class PatientNotifierBrokerController {
	//@Inject
    private PatientNotifierBroker patientNotifierBroker;

    @POST
    @Path("/handlePayload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PatientNotifierBrokerHandlePayloadOutDTO handlePayload(PatientNotifierBrokerHandlePayloadInDTO in) {
        PatientNotifierBrokerHandlePayloadOutDTO ret = new PatientNotifierBrokerHandlePayloadOutDTO();
        patientNotifierBroker.handlePayload(in.getX0());
        return ret;
    }

}