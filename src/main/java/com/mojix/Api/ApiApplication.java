package com.mojix.Api;

import com.mojix.APIAuthenticator;
import com.mojix.resource.server.PingServerResource;
import com.mojix.resource.server.UserListServerResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * Created by @mmayorivera on 7/15/15.
 */
public class ApiApplication extends Application {

    public ApiApplication() {
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

        router.attach("/", PingServerResource.class);

        return router;
    }
}
