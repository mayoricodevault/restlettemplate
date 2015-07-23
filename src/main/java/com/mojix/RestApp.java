package com.mojix; /**
 * Created by mmv on 7/7/15.
 */

import com.mojix.resource.ServerStatusService;
import com.mojix.resource.server.PingServerResource;
import com.mojix.resource.server.UserListServerResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.ext.apispark.ApiSparkService;
import org.restlet.ext.apispark.FirewallConfig;
import org.restlet.resource.Directory;
import org.restlet.routing.Extractor;
import org.restlet.routing.Redirector;
import org.restlet.routing.Router;
import org.restlet.service.CorsService;
import org.restlet.util.WrapperList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;




public class RestApp extends Application{


    public static final Logger LOGGER = Engine.getLogger(RestApp.class);
    public static final String PING = "Version: 1.0.0 running";
    private static WrapperList<String> blackList = null;

    public static void main(String[] args) {
        LOGGER.info("Rest application starting...");

        if (blackList == null) {
            blackList = new WrapperList(0);
            blackList.add("192.168.0.101");
        }
        try {
            ApiSparkService apiSparkService = new ApiSparkService();
            apiSparkService.setFirewallEnabled(true);

            FirewallConfig firewallConfig = apiSparkService.getFirewallConfig();

            firewallConfig.addIpAddressesPeriodicCounter(60, TimeUnit.SECONDS, 10);
            firewallConfig.addIpAddressesConcurrencyCounter(2);
            firewallConfig.addIpAddressesBlackList(blackList);

            CorsService corsService = new CorsService();
            corsService.setAllowedOrigins(new HashSet(Arrays.asList("*")));
            corsService.setAllowedCredentials(false);


            Component component= new Component();
            component.getServers().add(Protocol.HTTP, 9090);
            component.getClients().add(Protocol.CLAP);
            component.getClients().add(Protocol.FILE);
            component.getClients().add(Protocol.RIAP);
            component.getServices().add(apiSparkService);
            component.getServices().add(corsService);
            component.getLogService().setLogPropertiesRef("clap:///logging.properties");
            component.getDefaultHost().attach("/site", new RestApp());
            component.start();
            LOGGER.info("Web API started");
            LOGGER.info("URL: http://localhost:9090/api");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RestApp() {




        setName("Rest App");
        setDescription("API's Application");
        setStatusService(new ServerStatusService());

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

        // Create a Redirector to Google search service
        String target = "http://www.google.com/search?q=site:lifeandhealthtraining.com+{keywords}";
        Redirector redirector = new Redirector(getContext(), target,
                Redirector.MODE_CLIENT_TEMPORARY);
        // While routing requests to the redirector, extract the "kwd" query
        // parameter. For instance :
        // http://localhost:8111/search?kwd=myKeyword1+myKeyword2
        // will be routed to
        // http://www.google.com/search?q=site:mysite.org+myKeyword1%20myKeyword2
        Extractor extractor = new Extractor(getContext(), redirector);
        extractor.extractFromQuery("keywords", "kwd", true);
        router.attach("/about", extractor);
        // Serve static files (images, etc.)
        String rootUri = "file:///" + System.getProperty("java.io.tmpdir");
        Directory directory = new Directory(getContext(), rootUri);
        directory.setListingAllowed(true);

        router.attach("/dir", directory);



        return router;
    }

    public Router publicResources() {
        Router router = new Router();

        router.attach("/ping", PingServerResource.class);

        return router;
    }

}


