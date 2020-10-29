package dao.classes;

import com.mongodb.client.MongoCollection;
import dao.databaseconnection.IDatabaseConnection;
import dao.interfaces.IUserDAO;
import dto.UserDTO;
import dto.UserLoginDTO;
import dto.UserTokenDTO;
import exceptions.InvalidUserOrPasswordException;
import exceptions.TokenSaveFailedException;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;
import org.bson.Document;

import javax.enterprise.inject.Default;
import javax.inject.Inject;


import static com.mongodb.client.model.Filters.*;

@Default
public class UserDAO implements IUserDAO {

    private IDatabaseConnection database;

    @Inject
    public UserDAO(IDatabaseConnection database) {
        this.database = database;
    }

    public UserDAO() {

    }

    @Override
    public void userLogin(UserLoginDTO dto) throws InvalidUserOrPasswordException {
        try {
            MongoCollection<Document> userCollection = database.getDatabase().getCollection("user");
            Document user = userCollection.find(and(eq("username", dto.getUser()), eq("password", dto.getPassword()))).first();
            if (user == null){
                throw new InvalidUserOrPasswordException();
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new InvalidUserOrPasswordException();
        }
    }

    @Override
    public void saveToken(UserTokenDTO dto) throws TokenSaveFailedException {
        try{
            MongoCollection<UserDTO> userCollection = database.getDatabase().getCollection("user", UserDTO.class);
            UserDTO user = userCollection.find(eq("username", dto.getUser())).first();
            if(user != null){
                user.setToken(dto.getToken());
                Document filterByUserId = new Document("_id", user.getId());
                userCollection.findOneAndReplace(filterByUserId, user);
            } else throw new TokenSaveFailedException();


        }catch (Exception e) {
            e.printStackTrace();
            throw new TokenSaveFailedException();
        }
    }

    @Override
    public String getUserByToken(String token) throws UserNotFoundByTokenException {
        try {
            MongoCollection<UserDTO> userCollection = database.getDatabase().getCollection("user", UserDTO.class);
            UserDTO user = userCollection.find(eq("token", token)).first();
            if(user != null){
                return user.getUsername();
            } else throw new UserNotFoundByTokenException();
        }catch (Exception e) {
            e.printStackTrace();
            throw new UserNotFoundByTokenException();
        }
    }

    @Override
    public boolean authorization(String token) throws UserNotAuthorizedException {
        try {
            return !getUserByToken(token).equals("");
        }catch (Exception e) {
            e.printStackTrace();
            throw new UserNotAuthorizedException();
        }
    }
}
