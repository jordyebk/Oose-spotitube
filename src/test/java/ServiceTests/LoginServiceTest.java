package ServiceTests;

import DAO.Classes.UserDAO;
import DAO.Interfaces.IUserDAO;
import DTO.UserLoginDTO;
import Exceptions.InvalidUserOrPasswordException;
import Service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginServiceTest {

    private static UserLoginDTO userLoginDTO;
    private static final int STATUSOK = 200;
    private static final int STATUSUNAUTHORIZED = 401;

    private static LoginService loginService;
    private static IUserDAO userDAO;

    @BeforeEach
    public void setupBeforeEach() {
        userDAO = Mockito.mock(UserDAO.class);
        loginService = new LoginService(userDAO);
        userLoginDTO = new UserLoginDTO("Username", "Password");
    }

    @Test
    public void loginUser() {
        Response response = loginService.loginUser(userLoginDTO);

        Assertions.assertEquals(STATUSOK, response.getStatus());
    }

    @Test
    public void loginUserUnauthorized() throws InvalidUserOrPasswordException {
        Mockito.doThrow(new InvalidUserOrPasswordException()).when(userDAO).userLogin(userLoginDTO);

        Response response = loginService.loginUser(userLoginDTO);

        Assertions.assertEquals(STATUSUNAUTHORIZED, response.getStatus());
    }
}
