package com.jhc.figleaf.JobsRestApp.database;

import com.ibm.as400.access.AS400;
import com.jhc.figleaf.JobsRestApp.models.Job;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hamish dickson on 12/03/2014.
 *
 * Actually go and get the data from Tracey
 */
public class RealTracey {

    private static final String DB_DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
    private static final String DB_CONNECTION = "jdbc:as400://tracey.servers.jhc.co.uk;naming=system;prompt=false";
    private static final String DB_USER = "HDDEV";
    private static final String DB_PASSWORD = "HDDEV";

    private static final AtomicInteger uniqueInvocationNumber = new AtomicInteger();
    private static final BasicDataSource dataSource = new BasicDataSource();
    private static final AS400 as400;


    static {
        dataSource.setDriverClassName("com.ibm.as400.access.AS400JDBCDriver");
        dataSource.setMaxActive(Integer.valueOf(5).intValue());
        dataSource.setMaxIdle(Integer.valueOf(2).intValue());
        dataSource.setValidationQuery("SELECT * FROM F63HOLDDTA/CLIENT WHERE CLINO = '0000001'");
        dataSource.setTestOnBorrow(true);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_CONNECTION);
        as400 = new AS400("TRACEY", DB_USER, DB_PASSWORD);
    }


    public static Job getJob(int jobNumber) throws SQLException {

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String selectSQL = "SELECT CODEX, DESCRQ, WHODO, STATUS FROM JOBS3 WHERE CODEX = " + jobNumber + " FETCH FIRST 1 ROWS ONLY";
            ResultSet resultSet = statement.executeQuery(selectSQL);

            Job job = null;

            while (resultSet.next()) {
                job = new Job(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }

            statement.close();
            resultSet.close();

            return job;
        } catch (SQLException e) {
            return new Job(jobNumber, "test", "test 2", "test 3");
        }



        /*Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        Job job = null;

        String selectSQL = "SELECT CODEX, DESCRQ, WHODO, STATUS FROM JOBS3 WHERE CODEX = 212411" ;//+ jobNumber + " FETCH FIRST 1 ROWS ONLY";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, 1001);

            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();

            job = new Job(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            //job = new Job(jobNumber, "test", "test 2", "test 3");
*//*
            if (!rs.next()) {
                job = new Job(jobNumber, "test", "test 2", "test 3");
            }*//*

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
        return job;*/
    }

    /*public static void addJob(Job job) throws SQLException {
        // first get the next job number


        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String insertSQL = "INSERT INTO JOBS3 "
                + "(CODEX, STATUS, CLIENT, IMPORT, DESCRQ, WHODO, WHOPAY, CONTAC, BCODEX, JTYPE) VALUES"
                + "(1, 'A', 'JHC', 9, 'this is a test', 'HD', 'JHC', 'HAMISH', 0, 'J')";
            ResultSet resultSet = statement.executeQuery(insertSQL);

            List<Job> jobs = new ArrayList<Job>();

            while (resultSet.next()) {
                jobs.add(new Job(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }

            statement.close();
            resultSet.close();

            return jobs;
        } catch (SQLException e) {
            return null;
        }
    }*/

    public static List<Job> getJobsForUser(String user) throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String selectSQL = "SELECT CODEX, DESCRQ, WHODO, STATUS FROM JOBS3 WHERE WHODO = '" + user + "'" ;//+ jobNumber + " FETCH FIRST 1 ROWS ONLY";
            ResultSet resultSet = statement.executeQuery(selectSQL);

            List<Job> jobs = new ArrayList<Job>();

            while (resultSet.next()) {
                jobs.add(new Job(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }

            statement.close();
            resultSet.close();

            return jobs;
        } catch (SQLException e) {
            return null;
        }

        /*Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        List<Job> jobs = new ArrayList<Job>();

        String selectSQL = "SELECT CODEX, DESCRQ, WHODO, STATUS FROM JOBS3 WHERE WHODO = '" + user + "'";

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
        return jobs;*/
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
