package com.mojix; /**
 * Created by mmv on 7/7/15.
 */

import com.mojix.resource.server.PingServerResource;
import com.mojix.resource.server.UserListServerResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;

import java.util.logging.Logger;




public class RestApp extends Application{


    public static final Logger LOGGER = Engine.getLogger(RestApp.class);
    public static final String PING = "Version: 1.0.0 running";

    public static void main(String[] args) {
        LOGGER.info("Rest application starting...");
        try {
            Component component= new Component();
            component.getServers().add(Protocol.HTTP, 9000);
            component.getClients().add(Protocol.CLAP);
            component.getLogService().setLogPropertiesRef("clap:///logging.properties");
            component.getDefaultHost().attach("/v1", new RestApp());
            component.start();
            LOGGER.info("Web API started");
            LOGGER.info("URL: http://localhost:9000/v1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RestApp() {
        setName("Rest App");
        setDescription("API's Application");
    }


    @Override

    public Restlet createInboundRoot() {


        Router mainRouter = MainApiRouter();

        APIAuthenticator authenticator = new APIAuthenticator(this.getContext());

        Router securedRouter = SecuredApiRouter();

        authenticator.setNext(securedRouter);

        mainRouter.attach("/secured/{apikey}", authenticator);

        return mainRouter;
    }

    private Router SecuredApiRouter() {
        Router router = new Router(getContext());

        router.attach("/users", UserListServerResource.class);

        return router;
    }

    private Router MainApiRouter() {

        Router router = new Router(getContext());

        router.attach("/home", PingServerResource.class);

        return router;
    }

    public Router publicResources() {
        Router router = new Router();

        router.attach("/ping", PingServerResource.class);

        return router;
    }

}


