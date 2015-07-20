package com.mojix.persistence;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.restlet.Context;

/**
 * Created by mmv on 7/8/15.
 */
public abstract class PersistenceService<T> {

    private static final String PASSWORD = "root";

    private static final String URL = "jdbc:mysql://localhost:3306/lms?zeroDateTimeBehavior=convertToNull";

    private static final String USER = "root";

    /**
     * Returns a persistence layer for managing Companies.
     *
     * @return A persistence layer for managing Companies.
     */
    public static UsersPersistence getUsersPersistence() {
        Context.getCurrentLogger().finer(
                "Get the persistence layer for Companies.");
        return UsersPersistence.getUsersPersistence();
    }

    /**
     * Generate a random ID.
     *
     * @return A random ID.
     */
    protected String generateStringId() {
        UUID id = UUID.randomUUID();
        return id.toString();
    }

    protected Connection getConnection() throws SQLException {
        Context.getCurrentLogger().finer("Get a fresh connection to database");
        Connection result = DriverManager.getConnection(URL, USER, PASSWORD);
        Context.getCurrentLogger().finer("Got a fresh connection to database");
        return result;
    }

    protected void releaseConnection(Connection connection) throws SQLException {
        Context.getCurrentLogger().finer(
                "Release connection: " + Objects.toString(connection));
        if (connection != null) {
            connection.close();
            Context.getCurrentLogger().finer(
                    "Connection released: " + Objects.toString(connection));
        }

    }

    /**
     * Adds a new entity to the database.
     *
     * @param toAdd
     *            The entity to add.
     * @return The newly added entity, especially with its technical identifier,
     *         in case it is computed.
     * @throws SQLException
     */
//    public abstract T add(T toAdd) throws SQLException;

    /**
     * Removes an entity from the database.
     *
     * @return True if the entity has been removed, false if the entity was
     *         already removed.
     * @throws SQLException
     */
//    public abstract Boolean delete(String id) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract T findById(String id) throws SQLException;

    /**
     * Updates an existing entity.
     *
     * @param toUpdate
     *            The new state of the entity.
     * @param id
     *            the identifier of the ientity to update.
     * @return The updated entity.
     * @throws SQLException
     */
//    public abstract T update(T toUpdate, String id) throws SQLException;

}

