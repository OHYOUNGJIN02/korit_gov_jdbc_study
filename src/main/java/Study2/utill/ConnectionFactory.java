package Study2.utill;

import Study1.config.DBconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }


    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBconfig.URL, DBconfig.USERNAME, DBconfig.PASSWORD);
}}
