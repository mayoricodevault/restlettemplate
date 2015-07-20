package com.mojix.core.exception;

import org.restlet.resource.Status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Created by carolasilvateran on 7/8/15.
 */
/**
 * Parent class of all {@link Exception} written back to the client in case of
 * business errors. The {@link Status} annotation allows the framework to
 * serialize the thrown exception and set the status of the HTTP response.<br>
 * This sample code leverages the {@link JsonIgnoreProperties} annotation in
 * order to control the serialization: some properties are simply hidden. By
 * default, only the exception message and the status property are serialized.
 * any subclass is allowed to add some specific properties.
 *
 * @author Manuel Boillod
 */
@JsonIgnoreProperties({ "cause", "localizedMessage", "stackTrace", "suppressed" })
public abstract class BusinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int status;

    public BusinessException(int status, String message) {
        super(message);
        this.status = status;
    }

    public BusinessException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}