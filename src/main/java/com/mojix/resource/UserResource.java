package com.mojix.resource;

import com.mojix.core.exception.NotFoundException;
import com.mojix.representation.UsersRepresentation;
import org.restlet.resource.Get;
/**
 * Created by carolasilvateran on 7/8/15.
 */
public interface UserResource {
    @Get
    UsersRepresentation getUser() throws NotFoundException;
}
