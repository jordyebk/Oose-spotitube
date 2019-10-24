package DAOTests;

import dao.classes.UserDAO;
import dao.databaseconnection.IDatabaseConnection;
import dao.interfaces.IUserDAO;
import dto.UserLoginDTO;
import dto.UserTokenDTO;
import exceptions.InvalidUserOrPasswordException;
import exceptions.TokenSaveFailedException;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOTest {

    private static IDatabaseConnection databaseConnection;
    private static IUserDAO userDAO;
    private static UserLoginDTO userLoginDTO;
    private static UserTokenDTO userTokenDTO;

    private PreparedStatement statement;
    private ResultSet rs;

    @BeforeAll
    public static void setupBeforeAll() {
        databaseConnection = Mockito.mock(IDatabaseConnection.class);
        userDAO = new UserDAO(databaseConnection);
        userLoginDTO = new UserLoginDTO("Totally legit username", "Don't look at my password please");
        userTokenDTO = new UserTokenDTO("Totally legit username", "7285cc1b-a50a-47ab-8697-fa301235dff5");
    }

    @BeforeEach
    public void setupBeforeEach() throws Exception {
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(databaseConnection.getConnection()).thenReturn(conn);

        statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement);

        rs = Mockito.mock(ResultSet.class);
    }

    @Test
    public void userLoginThrowsException() throws SQLException {
        Mockito.when(statement.executeQuery()).thenThrow(new SQLException());
        Assertions.assertThrows(InvalidUserOrPasswordException.class, () -> userDAO.userLogin(userLoginDTO));
    }

    @Test
    public void saveTokenThrowsException() throws SQLException {
        Mockito.when(statement.execute()).thenThrow(new SQLException());
        Assertions.assertThrows(TokenSaveFailedException.class, () -> userDAO.saveToken(userTokenDTO));
    }

    @Test
    public void getUserByToken() throws SQLException, UserNotFoundByTokenException {
        Mockito.when(rs.getString("username")).thenReturn(userTokenDTO.getUser());
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(statement.executeQuery()).thenReturn(rs);

        Assertions.assertEquals(userDAO.getUserByToken(userTokenDTO.getToken()), userTokenDTO.getUser());
    }

    @Test
    public void getUserByTokenThrowsException() throws SQLException {
        Mockito.when(statement.executeQuery()).thenThrow(new SQLException());
        Assertions.assertThrows(UserNotFoundByTokenException.class, () -> userDAO.getUserByToken(userTokenDTO.getToken()));
    }

    @Test
    public void authorization() throws SQLException, UserNotAuthorizedException {
        Mockito.when(statement.executeQuery()).thenReturn(rs);
        Mockito.when(rs.isBeforeFirst()).thenReturn(true);

        Assertions.assertEquals(userDAO.authorization(userTokenDTO.getToken()), true);
    }

    @Test
    public void authorizationThrowsException() throws SQLException {
        Mockito.when(statement.executeQuery()).thenThrow(new SQLException());
        Assertions.assertThrows(UserNotAuthorizedException.class, () -> userDAO.authorization(userTokenDTO.getToken()));
    }
}
