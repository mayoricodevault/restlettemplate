package com.mojix.core.exception;

/**
 * Created by carolasilvateran on 7/8/15.
 */
import org.restlet.resource.Status;

/**
 * Sent back to client when an incoming entity is invalid. It defines a
 * "message" property and leads to set the HTTP response status to 400
 * ("bad request") thanks to the {@link Status} annotation.
 *
 */
@Status(400)
public class BadParameterException extends BusinessException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BadParameterException(String message) {
        super(400, message);
    }
}
