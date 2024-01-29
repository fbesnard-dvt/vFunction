package com.oracle.medrec.patientsearch;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.oracle.medrec.patientsearch.dto.*;
import com.oracle.medrec.facade.broker.jaxws.JaxWsPatientFacadeBroker;

@Stateless
@Path("/jaxWsPatientFacadeBroker")
public class JaxWsPatientFacadeBrokerController {
    @Inject
    private JaxWsPatientFacadeBroker jaxWsPatientFacadeBroker;

    @POST
    @Path("/fuzzyFindApprovedPatientsByLastnameAndSsn")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JaxWsPatientFacadeBrokerFuzzyFindApprovedPatientsByLastnameAndSsnOutDTO fuzzyFindApprovedPatientsByLastnameAndSsn(JaxWsPatientFacadeBrokerFuzzyFindApprovedPatientsByLastnameAndSsnInDTO in) {
        JaxWsPatientFacadeBrokerFuzzyFindApprovedPatientsByLastnameAndSsnOutDTO ret = new JaxWsPatientFacadeBrokerFuzzyFindApprovedPatientsByLastnameAndSsnOutDTO();
        ret.setRetVal(jaxWsPatientFacadeBroker.fuzzyFindApprovedPatientsByLastnameAndSsn(in.getLastName(), in.getSsn()));
        return ret;
    }

}