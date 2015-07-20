package com.mojix.utils;

import com.mojix.persistence.entity.Users;
import com.mojix.representation.UsersRepresentation;

/**
 * Created by carolasilvateran on 7/8/15.
 */
public class UserUtils {

    public static Users toUser(UsersRepresentation userRepr) {
        if (userRepr != null) {
            Users users = new Users();
            users.setPassword(userRepr.getPassword());
            users.setGender(userRepr.getGender());
            users.setFbid(userRepr.getFbid());
            users.setEmail(userRepr.getEmail());
            users.setFullname(userRepr.getFullname());
            users.setUsername(userRepr.getUsername());
            users.setId(userRepr.getId());

            return users;
        }
        return null;
    }
    public static UsersRepresentation toUserRepresentation(Users users) {
        if (users != null) {
            UsersRepresentation userRepr = new UsersRepresentation();
            userRepr.setPassword(users.getPassword());
            userRepr.setGender(users.getGender());
            userRepr.setFbid(users.getFbid());
            userRepr.setEmail(users.getEmail());
            userRepr.setFullname(users.getFullname());
            userRepr.setUsername(users.getUsername());
            userRepr.setId(users.getId());
            return userRepr;
        }
        return null;
    }
}
