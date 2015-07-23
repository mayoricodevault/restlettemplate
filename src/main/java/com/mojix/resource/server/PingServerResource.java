package com.mojix.resource.server;

/**
 * Created by carolasilvateran on 7/8/15.
 */

import com.mojix.RestApp;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
public class PingServerResource extends ServerResource {

    @Get("txt")
    public String ping() {
        return RestApp.PING;
    }

}

