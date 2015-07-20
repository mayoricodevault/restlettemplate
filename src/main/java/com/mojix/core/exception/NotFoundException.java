package com.mojix.core.exception;

/**
 * Created by carolasilvateran on 7/8/15.
 */
import org.restlet.resource.Status;

/**
 * Sent back to client in order to customize the 404 status response
 * ("Not found") thanks to the {@link Status} annotation. The representation
 * written out contains the status (cf constructor), and the exception message.
 *
 */
@Status(404)
public class NotFoundException extends BusinessException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(404, message);
    }
}
