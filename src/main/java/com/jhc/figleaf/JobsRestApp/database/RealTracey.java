package com.jhc.figleaf.JobsRestApp.database;

import com.ibm.as400.access.*;
import com.jhc.figleaf.JobsRestApp.models.Job;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
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
    //private static final String DB_CONNECTION = "jdbc:as400://tracey.servers.jhc.co.uk;naming=system;prompt=false";
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
        dataSource.setValidationQuery("SELECT * FROM JHCJUTIL/JOBS3 WHERE CODEX = 171524");
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
            String selectSQL = "SELECT CODEX, DESCRQ, WHODO, STATUS, CLIENT, IMPORT, WHOPAY, CONTAC, BCODEX, JTYPE FROM JOBS3 WHERE CODEX = " + jobNumber + " FETCH FIRST 1 ROWS ONLY";
            ResultSet resultSet = statement.executeQuery(selectSQL);

            Job job = null;

            while (resultSet.next()) {
                job = new Job(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10));
            }

            statement.close();
            resultSet.close();

            return job;
        } catch (SQLException e) {
            return new Job(jobNumber, "test", "test 2", "test 3", "test 4", 5, "test 6", "test 7", 8, "test 9");
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
            String selectSQL = "SELECT CODEX, DESCRQ, WHODO, STATUS, CLIENT, IMPORT, WHOPAY, CONTAC, BCODEX, JTYPE FROM JOBS3 WHERE WHODO = '" + user + "'" ;
            ResultSet resultSet = statement.executeQuery(selectSQL);

            List<Job> jobs = new ArrayList<Job>();

            while (resultSet.next()) {
                jobs.add(new Job(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10)));
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

    /**
     * This returns a job and a work order
     *
     * @param
     * @return
     * @throws InterruptedException
     * @throws IOException
     * @throws SQLException
     * @throws IllegalObjectTypeException
     * @throws ObjectDoesNotExistException
     * @throws ErrorCompletingRequestException
     * @throws AS400SecurityException
     */
    /*private static List<Job> addJob(Job job) throws InterruptedException, IOException, SQLException, IllegalObjectTypeException, ObjectDoesNotExistException, ErrorCompletingRequestException, AS400SecurityException {

        // first get the job number
        job.setJobNumber(getNextJobNumber());

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String insertSQL = "INSERT INTO JOBS3 "
                    + "(CODEX, STATUS, CLIENT, IMPORT, DESCRQ, WHODO, WHOPAY, CONTAC, BCODEX, JTYPE) VALUES"
                    + "(" + job.getJobNumber() + ", '" + job.getStatus() + "', 'JHC', 9, 'this is a test', 'HD', 'JHC', 'HAMISH', 0, 'J')";
            ResultSet resultSet = statement.executeQuery(insertSQL);

            List<Job> jobs = new ArrayList<Job>();

            while (resultSet.next()) {
                jobs.add(new Job(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10)));
            }

            statement.close();
            resultSet.close();

            return jobs;
        } catch (SQLException e) {
            return null;
        }
    }*/

    /*private static int getNextJobNumber() throws SQLException, InterruptedException, IOException, IllegalObjectTypeException, ObjectDoesNotExistException, ErrorCompletingRequestException, AS400SecurityException {
        AS400 system = new AS400("My400");
        QSYSObjectPathName path = new QSYSObjectPathName("MYLIB", "MYDATA", "DTAARA");
        DecimalDataArea dataArea = new DecimalDataArea(system, path.getPath());

        BigDecimal jobNumber = dataArea.read();

        dataArea.write(jobNumber.add(new BigDecimal(1)));

        return jobNumber.intValueExact();
    }*/

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
