package handler;

import DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ESA
 */
public class UserHandler 
{
    private UserDTO loggedUser = new UserDTO();
    private static UserHandler instance = new UserHandler();

    private UserHandler()
    {
    }

    public static UserHandler getInstance()
    {
        return instance;
    }

    public UserDTO getLoggedUser()
    {
        return loggedUser;
    }
    
    public boolean checkLoginCredentials(UserDTO user)
    {
        boolean correctCredentials = false;
        DatabaseHandler db = DatabaseHandler.getInstance();

        try
        {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();

            correctCredentials = rs.next() && rs.getString("password").equals(user.getPassword());
            user.setUserID(rs.getInt("user_id"));
            user.setAuthenticated(rs.getBoolean("authenticated"));
            
            if (correctCredentials)
            {
                this.loggedUser = user;
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Customer credentials incorrect.\n" + e);
        }
        return correctCredentials;
    }
}
