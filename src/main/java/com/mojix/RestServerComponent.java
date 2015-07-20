package com.mojix;

/**
 * Created by @mmayorivera on 7/15/15.
 */

import com.mojix.Api.ApiApplication;
import com.mojix.website.SiteApplication;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import java.util.logging.Logger;

public class RestServerComponent extends Component {
    public static final Logger LOGGER = Engine.getLogger(RestServerComponent.class);
    /**
     * Launches the mail server component.
     *
     * @param args
     *            The arguments.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new RestServerComponent().start();
    }

    /**
     * Constructor.
     *
     * @throws Exception
     */
    public RestServerComponent() throws Exception {
            LOGGER.info("Rest application starting...");


        setName("RESTful Mail Server component");
        setDescription("Template of REST");
        setOwner("Restlet S.A.S.");
        setAuthor("The Mojix Team");
        // Add client connectors
        getClients().add(Protocol.CLAP);
        getLogService().setLogPropertiesRef("clap:///logging.properties");
        // Adds server connectors
        getServers().add(Protocol.HTTP, 9000);

        // Attach the applications
        getDefaultHost().attach("/v1", new SiteApplication());
        getInternalRouter().attach("/api", new ApiApplication());
        LOGGER.info("Web API started");
        LOGGER.info("URL: http://localhost:9000/v1");
    }
}
