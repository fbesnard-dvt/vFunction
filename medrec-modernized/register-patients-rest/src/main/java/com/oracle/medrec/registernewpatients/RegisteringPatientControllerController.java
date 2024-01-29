package com.oracle.medrec.registernewpatients;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.oracle.medrec.registernewpatients.dto.*;
import com.oracle.medrec.web.controller.RegisteringPatientController;

@Stateless
@Path("/registeringPatientController")
public class RegisteringPatientControllerController {
    @Inject
    private RegisteringPatientController registeringPatientController;

    @POST
    @Path("/registerPatient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RegisteringPatientControllerRegisterPatientOutDTO registerPatient(RegisteringPatientControllerRegisterPatientInDTO in) {
        RegisteringPatientControllerRegisterPatientOutDTO ret = new RegisteringPatientControllerRegisterPatientOutDTO();
        ret.setRetVal(registeringPatientController.registerPatient());
        return ret;
    }

    @POST
    @Path("/getPatient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RegisteringPatientControllerGetPatientOutDTO getPatient(RegisteringPatientControllerGetPatientInDTO in) {
        RegisteringPatientControllerGetPatientOutDTO ret = new RegisteringPatientControllerGetPatientOutDTO();
        ret.setRetVal(registeringPatientController.getPatient());
        return ret;
    }

}