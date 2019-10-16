package Service;

import DAO.Interfaces.IUserDAO;
import DTO.UserLoginDTO;
import DTO.UserLoginResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/login")
public class LoginService {

    private IUserDAO userDAO;

    @Inject
    public void LoginService(IUserDAO userDAO) {this.userDAO = userDAO;}

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginUser(UserLoginDTO dto){
        try {
            userDAO.userLogin(dto);
            UserLoginResponseDTO responseDTO = new UserLoginResponseDTO(dto.getUser(), generateToken());
            userDAO.saveToken(responseDTO);
            return Response.ok().entity(responseDTO).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(401).build();
        }
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
