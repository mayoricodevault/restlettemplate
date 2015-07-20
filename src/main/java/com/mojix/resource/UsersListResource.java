package com.mojix.resource;

/**
 * Created by carolasilvateran on 7/8/15.
 */
import com.mojix.representation.UsersListRepresentation;
import org.restlet.resource.Get;
public interface UsersListResource {

    @Get
    public UsersListRepresentation getUsers();
}
