package com.mojix.website;

/**
 * Created by @mmayorivera on 7/15/15.
 */

import com.mojix.APIAuthenticator;
import com.mojix.resource.ServerStatusService;
import com.mojix.resource.server.PingServerResource;
import com.mojix.resource.server.RootServerResource;
import com.mojix.resource.server.UserListServerResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Extractor;
import org.restlet.routing.Redirector;
import org.restlet.routing.Router;

public class SiteApplication extends Application{
    public static final String PING = "Version: 1.0.0 running";
    public SiteApplication() {

        setName("RESTful Site Application");
        setDescription("Template Site Application");
        setOwner("Restlet S.A.S.");
        setAuthor("The Mojix Team");
        setStatusService(new ServerStatusService());

    }

    /**
     * Creates a root Router to dispatch call to server resources.
     */
    @Override
    public Restlet createInboundRoot() {

        Router mainRouter = MainApiRouter();
        mainRouter.attach("/users", UserListServerResource.class);

        // Serve static files (images, etc.)
        String rootUri = "file:///" + System.getProperty("java.io.tmpdir");
        Directory directory = new Directory(getContext(), rootUri);
        directory.setListingAllowed(true);
        mainRouter.attach("/dir", directory);

        // Create a Redirector to Google search service
        String target = "http://www.google.com/search?q=site:mysite.org+{keywords}";
        Redirector redirector = new Redirector(getContext(), target,
                Redirector.MODE_CLIENT_TEMPORARY);

        // While routing requests to the redirector, extract the "kwd" query
        // parameter. For instance :
        // http://localhost:8111/search?kwd=myKeyword1+myKeyword2
        // will be routed to
        // http://www.google.com/search?q=site:mysite.org+myKeyword1%20myKeyword2
        Extractor extractor = new Extractor(getContext(), redirector);
        extractor.extractFromQuery("keywords", "kwd", true);

        // Attach the extractor to the router
        mainRouter.attach("/search", extractor);


        APIAuthenticator authenticator = new APIAuthenticator(this.getContext());

        Router securedRouter = SecuredApiRouter();

        authenticator.setNext(securedRouter);

        mainRouter.attach("/secured/{apikey}", authenticator);

        return authenticator;

    }

    private Router SecuredApiRouter() {
        Router router = new Router(getContext());

        router.attach("/list", UserListServerResource.class);

        return router;
    }

    private Router MainApiRouter() {

        Router router = new Router(getContext());

        router.attach("/", RootServerResource.class);

        return router;
    }

    public Router publicResources() {
        Router router = new Router();

        router.attach("/ping", PingServerResource.class);

        return router;
    }


}
