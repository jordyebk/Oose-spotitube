package dao.databaseconnection;

import com.mongodb.client.MongoDatabase;

public interface IDatabaseConnection {

    MongoDatabase getDatabase() throws Exception;
}
