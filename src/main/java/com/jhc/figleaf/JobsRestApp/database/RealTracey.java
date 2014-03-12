package com.jhc.figleaf.JobsRestApp.database;

import com.jhc.figleaf.JobsRestApp.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamish dickson on 12/03/2014.
 *
 * Actually go and get the data from Tracey
 */
public class RealTracey {

    private static final String DB_DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
    private static final String DB_CONNECTION = "jdbc:as400://tracey.servers.jhc.co.uk;naming=system;prompt=false";
    private static final String DB_USER = "HAMISHD";
    private static final String DB_PASSWORD = "JHCJHC";

    public static Job getJob(int jobNumber) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        Job job = null;

        String selectSQL = "SELECT CODEX, DESCRQ, WHODO, STATUS FROM JOBS3 WHERE CODEX = " + jobNumber + " FETCH FIRST 1 ROWS ONLY";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, 1001);

            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();

            job = new Job(jobNumber, rs.getString(2), rs.getString(3), rs.getString(4));

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        return job;
    }

    public static List<Job> getJobsForUser(String user) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        List<Job> jobs = new ArrayList<Job>();

        String selectSQL = "SELECT CODEX, DESCRQ, WHODO, STATUS FROM JOBS3 WHERE WHODO = " + user;

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, 1001);

            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                jobs.add(new Job(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        return jobs;
    }

    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }
}
