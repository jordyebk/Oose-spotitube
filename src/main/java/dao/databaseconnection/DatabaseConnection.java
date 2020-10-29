package dao.databaseconnection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import javax.enterprise.inject.Default;
import java.util.Properties;

@Default
public class DatabaseConnection implements IDatabaseConnection{

    public DatabaseConnection() {

    }

    @Override
    public MongoDatabase getDatabase() throws Exception {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            MongoClient mongoClient = MongoClients.create(prop.getProperty("mongodburl"));
            return mongoClient.getDatabase("spotitube");
        } catch (Exception e) {
            e.getMessage();
        }
        throw new Exception("asd");
    }

//    public Connection getConnection() throws Exception {
//        Connection connection;
//        try{
//            Properties prop = new Properties();
//            prop.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
//            Class.forName(prop.getProperty("driver"));
//            connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
//            return connection;
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        throw new Exception("asd");
//    }
}
