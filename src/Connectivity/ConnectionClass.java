package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    public Connection connection;

    public Connection getConnection() {
        String dbName = "club";
        String UserName = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/club",UserName,password);

        }catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }
}