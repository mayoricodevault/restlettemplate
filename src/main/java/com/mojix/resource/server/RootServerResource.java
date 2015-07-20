package com.mojix.resource.server;

import com.mojix.website.SiteApplication;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Created by @mmayorivera on 7/15/15.
 */
public class RootServerResource extends ServerResource {

    @Get("txt")
    public String ping() {
        return SiteApplication.PING;
    }

}