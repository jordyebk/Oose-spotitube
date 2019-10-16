package DAO.Interfaces;

import DTO.UserLoginDTO;
import DTO.UserLoginResponseDTO;
import Exceptions.InvalidUserOrPasswordException;
import Exceptions.TokenSaveFailedException;

public interface IUserDAO {

    /**
     * TODO: Add JAVADOC
     * @param dto
     */
    void userLogin(UserLoginDTO dto) throws InvalidUserOrPasswordException;

    void saveToken(UserLoginResponseDTO dto) throws TokenSaveFailedException;
}
