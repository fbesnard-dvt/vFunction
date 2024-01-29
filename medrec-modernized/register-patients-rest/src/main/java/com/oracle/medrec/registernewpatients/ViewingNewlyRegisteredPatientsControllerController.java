package com.oracle.medrec.registernewpatients;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.oracle.medrec.registernewpatients.dto.*;
import com.oracle.medrec.web.controller.ViewingNewlyRegisteredPatientsController;

@Stateless
@Path("/viewingNewlyRegisteredPatientsController")
public class ViewingNewlyRegisteredPatientsControllerController {
    @Inject
    private ViewingNewlyRegisteredPatientsController viewingNewlyRegisteredPatientsController;

    @POST
    @Path("/viewNewlyRegisteredPatients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ViewingNewlyRegisteredPatientsControllerViewNewlyRegisteredPatientsOutDTO viewNewlyRegisteredPatients(ViewingNewlyRegisteredPatientsControllerViewNewlyRegisteredPatientsInDTO in) {
        ViewingNewlyRegisteredPatientsControllerViewNewlyRegisteredPatientsOutDTO ret = new ViewingNewlyRegisteredPatientsControllerViewNewlyRegisteredPatientsOutDTO();
        ret.setRetVal(viewingNewlyRegisteredPatientsController.viewNewlyRegisteredPatients());
        return ret;
    }

}