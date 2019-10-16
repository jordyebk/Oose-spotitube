package DAO.Classes;

import DAO.DatabaseConnection.IDatabaseConnection;
import DAO.Interfaces.IUserDAO;
import DTO.UserLoginDTO;
import DTO.UserLoginResponseDTO;
import Exceptions.InvalidUserOrPasswordException;
import Exceptions.TokenSaveFailedException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserDAO implements IUserDAO {

    private IDatabaseConnection databaseConnection;

    @Inject
    public UserDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public UserDAO() {

    }

    public void userLogin(UserLoginDTO dto) throws InvalidUserOrPasswordException {
        String query = "SELECT 1 as 'Selected' FROM `users` WHERE username = ? AND password = ?;";
        try (
                Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query);
        ) {
            statement.setString(1, dto.getUser());
            statement.setString(2, dto.getPassword());
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst()) {
                throw new InvalidUserOrPasswordException();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidUserOrPasswordException();
        }
    }

    public void saveToken(UserLoginResponseDTO dto) throws TokenSaveFailedException {
        String query = "UPDATE users SET token = ? WHERE username = ?";
        try (
                Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query);
        ) {
            statement.setString(1, dto.getToken());
            statement.setString(2, dto.getUser());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenSaveFailedException();
        }
    }
}
