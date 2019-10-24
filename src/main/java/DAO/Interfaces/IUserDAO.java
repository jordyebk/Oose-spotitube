package DAO.Interfaces;

import DTO.UserLoginDTO;
import DTO.UserTokenDTO;
import Exceptions.InvalidUserOrPasswordException;
import Exceptions.TokenSaveFailedException;
import Exceptions.UserNotAuthorizedException;
import Exceptions.UserNotFoundByTokenException;

public interface IUserDAO {

    void userLogin(UserLoginDTO dto) throws InvalidUserOrPasswordException;

    void saveToken(UserTokenDTO dto) throws TokenSaveFailedException;

    String getUserByToken(String token) throws UserNotFoundByTokenException;

    boolean authorization(String token) throws UserNotAuthorizedException;
}
