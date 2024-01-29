package com.oracle.medrec.registernewpatients;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.oracle.medrec.registernewpatients.dto.*;
import com.oracle.medrec.web.controller.ApprovingNewlyRegisteredPatientController;

@Stateless
@Path("/approvingNewlyRegisteredPatientController")
public class ApprovingNewlyRegisteredPatientControllerController {
    @Inject
    private ApprovingNewlyRegisteredPatientController approvingNewlyRegisteredPatientController;

    @POST
    @Path("/approvePatient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApprovingNewlyRegisteredPatientControllerApprovePatientOutDTO approvePatient(ApprovingNewlyRegisteredPatientControllerApprovePatientInDTO in) {
        ApprovingNewlyRegisteredPatientControllerApprovePatientOutDTO ret = new ApprovingNewlyRegisteredPatientControllerApprovePatientOutDTO();
        ret.setRetVal(approvingNewlyRegisteredPatientController.approvePatient());
        return ret;
    }

    @POST
    @Path("/denyPatient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApprovingNewlyRegisteredPatientControllerDenyPatientOutDTO denyPatient(ApprovingNewlyRegisteredPatientControllerDenyPatientInDTO in) {
        ApprovingNewlyRegisteredPatientControllerDenyPatientOutDTO ret = new ApprovingNewlyRegisteredPatientControllerDenyPatientOutDTO();
        ret.setRetVal(approvingNewlyRegisteredPatientController.denyPatient());
        return ret;
    }

    @POST
    @Path("/viewNewlyRegisteredPatient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApprovingNewlyRegisteredPatientControllerViewNewlyRegisteredPatientOutDTO viewNewlyRegisteredPatient(ApprovingNewlyRegisteredPatientControllerViewNewlyRegisteredPatientInDTO in) {
        ApprovingNewlyRegisteredPatientControllerViewNewlyRegisteredPatientOutDTO ret = new ApprovingNewlyRegisteredPatientControllerViewNewlyRegisteredPatientOutDTO();
        ret.setRetVal(approvingNewlyRegisteredPatientController.viewNewlyRegisteredPatient());
        return ret;
    }

}