package dao.interfaces;

import dto.UserLoginDTO;
import dto.UserTokenDTO;
import exceptions.InvalidUserOrPasswordException;
import exceptions.TokenSaveFailedException;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;

public interface IUserDAO {

    void userLogin(UserLoginDTO dto) throws InvalidUserOrPasswordException;

    void saveToken(UserTokenDTO dto) throws TokenSaveFailedException;

    String getUserByToken(String token) throws UserNotFoundByTokenException;

    boolean authorization(String token) throws UserNotAuthorizedException;
}
