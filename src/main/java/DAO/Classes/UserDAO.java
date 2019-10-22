package DAO.Classes;

import DAO.DatabaseConnection.IDatabaseConnection;
import DAO.Interfaces.IUserDAO;
import DTO.UserLoginDTO;
import DTO.UserTokenDTO;
import Exceptions.InvalidUserOrPasswordException;
import Exceptions.TokenSaveFailedException;
import Exceptions.UserNotFoundByTokenException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
                PreparedStatement statement = conn.prepareStatement(query)
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

    public void saveToken(UserTokenDTO dto) throws TokenSaveFailedException {
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

    public String getUserByToken(String token) throws UserNotFoundByTokenException {
        String query = "select username from users where token = ?";
        try (
                Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query);
        ) {
            statement.setString(1, token);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                return rs.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw new UserNotFoundByTokenException();
        }

        throw new UserNotFoundByTokenException();
    }
}
