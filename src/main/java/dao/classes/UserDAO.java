package dao.classes;

import dao.databaseconnection.IDatabaseConnection;
import dao.interfaces.IUserDAO;
import dto.UserLoginDTO;
import dto.UserTokenDTO;
import exceptions.InvalidUserOrPasswordException;
import exceptions.TokenSaveFailedException;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

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

    }

    @Override
    public void saveToken(UserTokenDTO dto) throws TokenSaveFailedException {

    }

    @Override
    public String getUserByToken(String token) throws UserNotFoundByTokenException {
        return null;
    }

    @Override
    public boolean authorization(String token) throws UserNotAuthorizedException {
        return false;
    }
}
