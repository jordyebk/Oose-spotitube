//package dao.classes.OldDAOS;
//
//import dao.databaseconnection.IDatabaseConnection;
//import dao.interfaces.IUserDAO;
//import dto.UserLoginDTO;
//import dto.UserPOJO;
//import exceptions.InvalidUserOrPasswordException;
//import exceptions.TokenSaveFailedException;
//import exceptions.UserNotAuthorizedException;
//import exceptions.UserNotFoundByTokenException;
//
//import javax.enterprise.inject.Default;
//import javax.inject.Inject;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//@Default
//@Deprecated
//public class MySQLUserDAO implements IUserDAO {
//
//    private IDatabaseConnection databaseConnection;
//
//    @Inject
//    public MySQLUserDAO(IDatabaseConnection databaseConnection) {
//        this.databaseConnection = databaseConnection;
//    }
//
//    public MySQLUserDAO() {
//
//    }
//
//    public void userLogin(UserLoginDTO dto) throws InvalidUserOrPasswordException {
//        String query = "SELECT 1 as 'Selected' FROM `users` WHERE username = ? AND password = ?;";
//        try (
//                Connection conn = databaseConnection.getConnection();
//                PreparedStatement statement = conn.prepareStatement(query)
//        ) {
//            statement.setString(1, dto.getUsername());
//            statement.setString(2, dto.getPassword());
//            ResultSet rs = statement.executeQuery();
//            if (!rs.isBeforeFirst()) {
//                throw new InvalidUserOrPasswordException();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new InvalidUserOrPasswordException();
//        }
//    }
//
//    public void saveToken(UserPOJO dto) throws TokenSaveFailedException {
//        String query = "UPDATE users SET token = ? WHERE username = ?";
//        try (
//                Connection conn = databaseConnection.getConnection();
//                PreparedStatement statement = conn.prepareStatement(query);
//        ) {
//            statement.setString(1, dto.getToken());
//            statement.setString(2, dto.getUsername());
//            statement.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new TokenSaveFailedException();
//        }
//    }
//
//    public String getUserByToken(String token) throws UserNotFoundByTokenException {
//        String query = "select username from users where token = ?";
//        try (
//                Connection conn = databaseConnection.getConnection();
//                PreparedStatement statement = conn.prepareStatement(query)
//        ) {
//            statement.setString(1, token);
//
//            ResultSet rs = statement.executeQuery();
//
//            while(rs.next()){
//                return rs.getString("username");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            throw new UserNotFoundByTokenException();
//        }
//
//        throw new UserNotFoundByTokenException();
//    }
//
//    public boolean authorization(String token) throws UserNotAuthorizedException {
//        String query = "SELECT 1 as 'Selected' FROM users WHERE token = ?";
//        try (
//                Connection conn = databaseConnection.getConnection();
//                PreparedStatement statement = conn.prepareStatement(query)
//        ) {
//            statement.setString(1, token);
//
//            ResultSet rs = statement.executeQuery();
//            if(rs.isBeforeFirst()){
//                return true;
//            }
//            throw new UserNotAuthorizedException();
//        } catch (Exception e){
//            e.printStackTrace();
//            throw new UserNotAuthorizedException();
//        }
//    }
//
//}
