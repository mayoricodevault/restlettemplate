package com.mojix;

/**
 * Created by mmv on 7/13/15.
 */

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.security.Authenticator;
import org.restlet.util.WrapperList;

import java.util.logging.Logger;

public class APIAuthenticator  extends Authenticator {
    private static WrapperList<String> allowedKeys = null;
    public static final Logger LOGGER = Engine.getLogger(APIAuthenticator.class);
    public APIAuthenticator(Context context) {
        super(context);
        if (this.allowedKeys == null) {
            this.allowedKeys = new WrapperList(0);
            allowedKeys.add("123");
            allowedKeys.add("456");
        }
    }

    @Override
    protected boolean authenticate(Request request, Response response) {
        boolean isAllowed = false;

        String apiKey = (String)request.getAttributes().get("apikey");

        if (apiKey == null) {
            //
            // Notice that we can set standard HTTP/REST error codes very easily
            // using the Restlet API. Whenever the response is sent to the user, this
            // error code will be set to the unauthorized error code (unless a filter
            // or restlet further down the chain changes it).
            //
            response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED, "Missing API key.");
        } else {
            if (allowedKeys.contains(apiKey)) {
                isAllowed = true;
            } else {
                response.setStatus(
                        Status.CLIENT_ERROR_UNAUTHORIZED,
                        String.format("%s is not an allowed API key.", apiKey));
            }
        }

        return isAllowed;
    }
}
