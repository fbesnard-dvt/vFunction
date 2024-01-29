package com.oracle.medrec.patientsearch;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.oracle.medrec.patientsearch.dto.*;
import com.oracle.physician.web.controller.SearchingPatientController;

@Stateless
@Path("/searchingPatientController")
public class SearchingPatientControllerController {
    @Inject
    private SearchingPatientController searchingPatientController;

    @POST
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SearchingPatientControllerSearchOutDTO search(SearchingPatientControllerSearchInDTO in) {
        SearchingPatientControllerSearchOutDTO ret = new SearchingPatientControllerSearchOutDTO();
        searchingPatientController.search();
        return ret;
    }

    @POST
    @Path("/getLastName")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SearchingPatientControllerGetLastNameOutDTO getLastName(SearchingPatientControllerGetLastNameInDTO in) {
        SearchingPatientControllerGetLastNameOutDTO ret = new SearchingPatientControllerGetLastNameOutDTO();
        ret.setRetVal(searchingPatientController.getLastName());
        return ret;
    }

}