package dao.databaseconnection;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@Default
public class DatabaseConnection implements IDatabaseConnection{

    public DatabaseConnection() {

    }

    public Connection getConnection() throws Exception {
        Connection connection;
        try{
            Properties prop = new Properties();
            prop.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(prop.getProperty("driver"));
            connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
            return connection;
        } catch (Exception e) {
            e.getMessage();
        }
        throw new Exception("asd");
    }
}
