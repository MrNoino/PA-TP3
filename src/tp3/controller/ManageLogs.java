package tp3.controller;

import java.util.ArrayList;
import tp3.model.Log;
import tp3.model.DbWrapper;
import java.sql.*;

/**
 * A class to manage logs on the database
 */
public class ManageLogs {

    private ArrayList<Log> logs;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageLogs() {
        logs = new ArrayList<Log>();
    }

    /**
     * Gets the logs from the database
     * @return A list of logs
     */
    public ArrayList<Log> getLogs() {

        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("SELECT * FROM get_logs;");

        try {
            if (resultSet == null) {
                return null;
            }

            while (resultSet.next()) {
                this.logs.add(new Log(resultSet.getLong("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("datetime"),
                        resultSet.getString("action")));
            }
            return this.logs;
        } catch (SQLException e) {
            System.out.println("\nErro ao obter os logs\n");
        }

        return null;
    }

    /**
     * Gets the user logs from the database given the id
     * @param userId id of the user
     * @return A list of logs
     */
    public ArrayList<Log> getLogsByUser(long userId) {

        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_logs_by_user(?)", new Object[]{userId});

        try {
            if (resultSet == null) {
                return null;
            }

            while (resultSet.next()) {
                this.logs.add(new Log(resultSet.getLong("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("datetime"),
                        resultSet.getString("action")));
            }
            return this.logs;
        } catch (SQLException e) {
            System.out.println("\nErro ao obter os logs\n");
        }

        return null;
    }

    /**
     * Inserts a log in the database
     *
     * @param log The log to insert to the database
     * @return Confirms if a log was inserted successfully
     */
    public boolean insertLog(Log log) {

        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();

        return dbWrapper.manipulate("CALL insert_log(?,?);", new Object[]{log.getUserId(), log.getAction()}) > 0;
    }
    
    /**
     * Converts the arraylist of logs into an array of objects
     *
     * @return the array of logs
     */
    public Object[][] toArray() {
        Object[][] l = new Object[this.logs.size()][4];
        
        for(int i = 0; i < this.logs.size(); i++)
            l[i] = this.logs.get(i).toArray();
        
        return l;
    }
    
}
