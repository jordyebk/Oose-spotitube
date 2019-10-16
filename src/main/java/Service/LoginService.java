package Service;

import DAO.Interfaces.IUserDAO;
import DTO.UserLoginDTO;
import DTO.UserLoginResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginService {

    private IUserDAO userDAO;

    @Inject
    public void LoginService(IUserDAO userDAO) {this.userDAO = userDAO;}

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginUser(UserLoginDTO dto){
        System.out.println("From front: " + dto.getUser() + " " + dto.getPassword());

        System.out.println(userDAO.userLogin(dto));

        return Response.ok().entity(new UserLoginResponseDTO("Jordy Veldhuizen", "1234-1234-1234")).build();
    }
}
