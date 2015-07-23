package com.mojix.resource.server;

import com.mojix.persistence.PersistenceService;
import com.mojix.persistence.UsersPersistence;
import com.mojix.persistence.entity.Users;
import com.mojix.representation.UsersListRepresentation;
import com.mojix.representation.UsersRepresentation;
import com.mojix.resource.UsersListResource;
import com.mojix.utils.UserUtils;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by carolasilvateran on 7/8/15.
 */
public class UserListServerResource extends ServerResource implements UsersListResource{
    private UsersPersistence usersPersistence;



    @Override
    protected void doInit() {
        getLogger().finer("Initialization of UserListServerResource");
        usersPersistence = PersistenceService.getUsersPersistence();
        getLogger().finer("Initialization of UserListServerResource ended.");
    }

    public UsersListRepresentation getUsers() {
        getLogger().finer("Retrieve the list of Users.");

        try {
            // Retrieve the users from persistence layer
            List<Users> users = usersPersistence.findAll();
            List<UsersRepresentation> userReprs = new ArrayList<UsersRepresentation>();
            for (Users user : users) {
                UsersRepresentation userRepr = UserUtils.toUserRepresentation(user);
                userReprs.add(userRepr);
            }
            UsersListRepresentation result = new UsersListRepresentation();
            result.setList(userReprs);

            getLogger().finer("List of users successfully retrieved.");

            return result;
        } catch (SQLException ex) {
            getLogger().log(Level.WARNING, "Error when listing users", ex);
            throw new ResourceException(ex);
        }
    }
}
