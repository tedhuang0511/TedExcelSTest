package main.java.IIImidProject;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    public static Connection connectDB(){
        Connection conn = null;
        try {
            var prop = new Properties();
            var in = new FileInputStream("src/main/resources/properties");
            prop.load(in);
            in.close();
            String connectionURL = prop.getProperty("jdbc.url");
            String username = prop.getProperty("jdbc.username");
            String password = prop.getProperty("jdbc.password");

            conn = DriverManager.getConnection(
                    connectionURL, username, password);
        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
}


