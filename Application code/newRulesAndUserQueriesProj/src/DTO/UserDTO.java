package DTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ESA
 */
public class UserDTO 
{
    private int userID;
    private String username;
    private String password;
    private boolean authenticated;

    public UserDTO()
    {
    }

    public UserDTO(String username, String password, boolean authenticated)
    {
        this.username = username;
        this.password = password;
        this.authenticated = authenticated;
    }

    public UserDTO(int userID, String username, String password, boolean authenticated)
    {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.authenticated = authenticated;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isAuthenticated()
    {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated)
    {
        this.authenticated = authenticated;
    }
    
    
}
