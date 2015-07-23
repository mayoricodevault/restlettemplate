package com.mojix.resource;

/**
 * Created by carolasilvateran on 7/15/15.
 */


import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.velocity.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.service.StatusService;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
public class ServerStatusService extends StatusService {

    @Override
    public Representation toRepresentation(Status status, Request request, Response response) {
        TemplateRepresentation representation = null;
        // Create the data model
        Map<String, Object> dataModel = new TreeMap<String, Object>();
        dataModel.put("applicationName", Application.getCurrent().getName());
        dataModel.put("statusReasonPhrase", response.getStatus().getReasonPhrase());
        dataModel.put("statusDescription", response.getStatus().getDescription());
        Representation thyFtl = new ClientResource(
                LocalReference.createClapReference(getClass().getPackage()) + "/RestStatus.vtl").get();


        try {
            representation = new TemplateRepresentation(
                    thyFtl,
                    dataModel,
                    MediaType.TEXT_HTML
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return representation;
    }


}

