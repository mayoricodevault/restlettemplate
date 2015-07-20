package com.mojix.core.validation;
import com.mojix.core.exception.BadEntityException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carolasilvateran on 7/8/15.
 */
public class ValidationErrors {

    private List<FieldError> fieldErrors = new ArrayList<>();

    private List<String> globalMessages = new ArrayList<>();

    public void addFieldError(FieldError fieldError) {
        fieldErrors.add(fieldError);
    }

    public void addFieldError(String field, String message) {
        addFieldError(new FieldError(field, message));
    }

    public void addGlobalMessage(String globalMessage) {
        globalMessages.add(globalMessage);
    }

    /**
     * Checks whether the list of registered messages or field errors are empty.
     *
     * @param message
     *            The error message in case an error has been listed.
     * @throws BadEntityException
     *             In case an error has been listed.
     */
    public void checkErrors(String message) throws BadEntityException {
        if (!globalMessages.isEmpty() || !fieldErrors.isEmpty()) {
            throw new BadEntityException(message, this);
        }
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public List<String> getGlobalMessages() {
        return globalMessages;
    }
}
