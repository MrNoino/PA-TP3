package tp3.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

/**
 * A class to query and manipulate the database and to manipulate the database properties file
 */
public class DbWrapper {
    private Connection connection;
    private String host;
    private String port;
    private String database;
    private String user;
    private String password;

    /**
     * Class constructor that assigns the attributes from the parameters
     * @param host host of the database
     * @param port port of the database
     * @param database database name
     * @param user user to access the database
     * @param password password to access the database
     */
    public DbWrapper(String host, String port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
    }
    
    /**
     * Class constructor that assigns the attributes from file
     * @param fileName name of the file
     */
    public DbWrapper(String fileName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
        if(!this.loadProperties(fileName))
            this.setProperties("localhost", "3306", "pa_tp", "root", "root");
    }
    
    /**
     * Class constructor that assigns the attributes from a default file
     */
    public DbWrapper() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
        if(!this.loadProperties())
            this.setProperties("localhost", "3306", "pa_tp", "root", "root");
    }
    
    /**
     * Loads properties from a given file
     * @param fileName name of the file
     * @return Confirms if the properties were loaded 
     */
    public boolean loadProperties(String fileName){
        File file  = new File(fileName);

        if(!file.exists())
            return false;

        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        
        BufferedReader input = new BufferedReader( reader );
        try { 
            this.host = input.readLine();
            this.port = input.readLine();
            this.database = input.readLine();
            this.user = input.readLine();
            this.password = input.readLine();
        } catch (IOException e) {
            return false;
        } finally{
            try {
                input.close();
                reader.close();
            } catch (IOException e) {
            }
        }
        return true;
    }
    /**
     * Loads properties from a default file
     * @return Confirms if the properties were loaded
     */
    public boolean loadProperties(){
        return this.loadProperties("db_properties.txt");
    } 
    
    /**
     * Assigns the  attributes
     * @param host host of the database
     * @param port port of the database
     * @param database database name
     * @param user user to access the database
     * @param password password to access the database
     */
    public void setProperties(String host, String port, String database, String user, String password){
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }
    
    /**
     * Save the properties in the given file
     * @param fileName name of the file
     * @return Confirms if the properties were saved in the file
     */
    public boolean saveProperties(String fileName){
        File file = new File(fileName);
        FileWriter writer;
        try {
            writer = new FileWriter( file );
        } catch (IOException e) {
            return false;
        }
        BufferedWriter output = new BufferedWriter( writer );
        try {
            output.write(this.host + "\n" + this.port + "\n" + this.database + "\n" + this.user + "\n" + this.password);
        } catch (IOException e) {
            return false;
        } finally{
            try {
                output.close();
                writer.close();
            } catch (IOException e) {
            }
        }
            
        return true;
    }
    
    /**
     * Save the properties in the default file
     * @return Confirms if the properties were saved in the file
     */
    public boolean saveProperties(){
        return this.saveProperties("db_properties.txt");
    }
    
    /**
     * Connect to the database with the attributes assigned
     * @return Confirms if the connection was successfully 
     */
    public boolean connect(){
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
            return true;
        } catch (SQLException e) {
        }
        
        return false;
    }
    
    /**
     * Close the connection with the database
     * @return Confirms if the close occurs with no problems
     */
    public boolean disconnect(){
        try {
            if(this.connection != null)
                this.connection.close();
            this.connection = null;
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    /**
     * Execute a query
     * @param sqlQuery the sql query to be executed in the database
     * @return the data obtained by the query
     */
    public ResultSet query(String sqlQuery){
        if(this.connection == null)
            return null;
        
        Statement statement;
        try {
            statement = this.connection.createStatement();
            return statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            return null;
        }
    }
    
    /**
     * Execute a query with prepared statements
     * @param sqlQuery the sql query to be executed in the database
     * @param values the values to be added in prepared statements
     * @return the data obtained by the query
     */
    public ResultSet query(String sqlQuery, Object[] values){
        if(this.connection == null)
            return null;
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.connection.prepareStatement(sqlQuery);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }
    
    /**
     * Execute a DML query with prepared statements
     * @param sqlQuery the sql query to be executed in the database
     * @param values the values to be added in prepared statements
     * @return the number of rows affected by the query
     */
    public int manipulate(String sqlQuery, Object[] values){
        if(!this.connect())
            return -1;
        
        PreparedStatement preparedStatement = null;
        int rowCount = 0;
        try {
            preparedStatement = this.connection.prepareStatement(sqlQuery);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            rowCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            rowCount = 0;
        }finally{
            if(preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            this.disconnect();
        }
        return rowCount;
    } 
}
