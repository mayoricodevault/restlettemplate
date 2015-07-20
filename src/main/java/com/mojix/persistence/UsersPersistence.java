package com.mojix.persistence;

import com.mojix.persistence.entity.Users;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.restlet.Context;
/**
 * Created by carolasilvateran on 7/8/15.
 */
public class UsersPersistence extends PersistenceService<Users> {
    // Singleton pattern.
    private static UsersPersistence usersPersistence = new UsersPersistence();

    public static synchronized UsersPersistence getUsersPersistence() {
        return usersPersistence;
    }

    private UsersPersistence() {
    }

    @Override
    public List<Users> findAll() throws SQLException {

        Context.getCurrentLogger().finer(
                "Method findAll() of UsersPersistence called.");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users");
            ResultSet rs = preparedStatement.executeQuery();

            List<Users> users = new ArrayList<Users>();
            while (rs.next()) {
                Users user = new Users();
                users.add(user);
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setFbid(rs.getLong("fbid"));
                user.setGender(rs.getString("gender"));
                user.setPassword(rs.getString("password"));
            }

            return users;
        } finally {
            releaseConnection(connection);
            Context.getCurrentLogger().finer(
                    "Method findAll() of UsersPersistence finished.");
        }

    }

    @Override
    public Users findById(String id) throws SQLException {

        Context.getCurrentLogger().finer(
                "Method findById() of UsersPersistence called.");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users where id=?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setFbid(rs.getLong("fbid"));
                user.setGender(rs.getString("gender"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null;
        } finally {
            releaseConnection(connection);
            Context.getCurrentLogger().finer(
                    "Method findById() of UserPersistence finished.");
        }
    }

}
