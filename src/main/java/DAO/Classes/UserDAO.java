package DAO.Classes;

import DAO.DatabaseConnection.IDatabaseConnection;
import DAO.Interfaces.IUserDAO;
import DTO.UserLoginDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
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

    public String userLogin(UserLoginDTO dto) {
        try (Connection conn = databaseConnection.getConnection()) {
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "UserLogin from DAO";
    }
}
