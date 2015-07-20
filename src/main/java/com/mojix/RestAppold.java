//package com.mojix; /**
//* Created by mmv on 7/7/15.
//*/
//
//import java.util.logging.Logger;
//
//import com.mojix.resource.server.PingServerResource;
//import com.mojix.resource.server.UserListServerResource;
//import org.restlet.Application;
//import org.restlet.Restlet;
//import org.restlet.data.Protocol;
//import org.restlet.engine.Engine;
//import org.restlet.routing.Router;
//import org.restlet.Component;
//import org.restlet.security.Role;
//import org.restlet.security.User;
//import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
//import org.restlet.ext.swagger.SwaggerSpecificationRestlet;
//import org.restlet.security.ChallengeAuthenticator;
//import org.restlet.security.MemoryRealm;
//import org.restlet.data.ChallengeScheme;
//
//
//public class RestApp extends Application{
//    public static final String ROLE_ADMIN = "admin";
//    public static final String ROLE_OWNER = "owner";
//    public static final String ROLE_USER = "user";
//
//    public static final Logger LOGGER = Engine.getLogger(RestApp.class);
//    public static final String PING = "Version: 1.0.0 running";
//
//    public static void main(String[] args) {
//        LOGGER.info("Rest application starting...");
//        try {
//            Component component= new Component();
//            component.getServers().add(Protocol.HTTP, 9000);
//            component.getClients().add(Protocol.CLAP);
//            component.getLogService().setLogPropertiesRef("clap:///log4j.properties");
//
//            component.getDefaultHost().attach("/v1", new RestApp());
//            component.start();
//            LOGGER.info("Sample Web API started");
//            LOGGER.info("URL: http://localhost:9000/v1");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public RestApp() {
//        setName("Rest App");
//        setDescription("API's Application");
//        getRoles().add(new Role(this, ROLE_ADMIN));
//        getRoles().add(new Role(this, ROLE_OWNER));
//        getRoles().add(new Role(this, ROLE_USER));
//    }
//
//    private void attachSwaggerSpecification1(Router router) {
//        SwaggerSpecificationRestlet swaggerSpecificationRestlet = new SwaggerSpecificationRestlet(
//                this);
//        swaggerSpecificationRestlet.setBasePath("http://myapp.com/api/");
//        swaggerSpecificationRestlet.attach(router);
//    }
//
//    private void attachSwaggerSpecification2(Router router) {
//        Swagger2SpecificationRestlet swagger2SpecificationRestlet = new Swagger2SpecificationRestlet(
//                this);
//        swagger2SpecificationRestlet.setBasePath("http://myapp.com/api/");
//        swagger2SpecificationRestlet.attach(router);
//    }
//
//    private ChallengeAuthenticator createApiGuard() {
//
//        ChallengeAuthenticator apiGuard = new ChallengeAuthenticator(
//                getContext(), ChallengeScheme.HTTP_BASIC, "realm");
//
//        // Create in-memory users and roles.
//        MemoryRealm realm = new MemoryRealm();
//
//        User owner = new User("owner", "owner");
//        realm.getUsers().add(owner);
//        realm.map(owner, getRole(ROLE_OWNER));
//        realm.map(owner, getRole(ROLE_USER));
//
//        User admin = new User("admin", "admin");
//        realm.getUsers().add(admin);
//        realm.map(admin, getRole(ROLE_ADMIN));
//        realm.map(admin, getRole(ROLE_OWNER));
//        realm.map(admin, getRole(ROLE_USER));
//
//        User user = new User("user", "user");
//        realm.getUsers().add(user);
//        realm.map(user, getRole(ROLE_USER));
//
//        // - Verifier : checks authentication
//        // - Enroler : to check authorization (roles)
//        apiGuard.setVerifier(realm.getVerifier());
//        apiGuard.setEnroler(realm.getEnroler());
//
//        // Provide your own authentication checks by extending SecretVerifier or
//        // LocalVerifier classes
//        // Extend the Enroler class in order to assign roles for an
//        // authenticated user
//
//        return apiGuard;
//    }
//
//
//    private Router createApiRouter() {
//
//        // Attach server resources to the given URL template.
//        // For instance, CompanyListServerResource is attached
//        // to http://localhost:9000/v1/companies
//        // and to http://localhost:9000/v1/companies/
//        Router router = new Router(getContext());
//
//        router.attach("/users", UserListServerResource.class);
//
//        return router;
//    }
//    @Override
//
//    public Restlet createInboundRoot() {
//
//        Router publicRouter = publicResources();
//
//        // Create the api router, protected by a guard
//        ChallengeAuthenticator apiGuard = createApiGuard();
//        Router apiRouter = createApiRouter();
//        apiGuard.setNext(apiRouter);
//
//        publicRouter.attachDefault(apiGuard);
//
//        return publicRouter;
//    }
//
//    public Router publicResources() {
//        Router router = new Router();
//
//        router.attach("/ping", PingServerResource.class);
//
//        // Attach Swagger Specifications
//        attachSwaggerSpecification1(router);
//        attachSwaggerSpecification2(router);
//        return router;
//    }
//
//}
//
//
