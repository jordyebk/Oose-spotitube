package dao.databaseconnection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.enterprise.inject.Default;
import java.util.Properties;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Default
public class DatabaseConnection implements IDatabaseConnection{

    public DatabaseConnection() {

    }

    @Override
    public MongoDatabase getDatabase() throws Exception {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getClassLoader().getResourceAsStream("database.properties"));

            ConnectionString connectionString = new ConnectionString(prop.getProperty("mongodburl"));
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
            MongoClientSettings clientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .codecRegistry(codecRegistry)
                    .build();

            MongoClient mongoClient = MongoClients.create(clientSettings);
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
