package handler;

import DTO.PassengerDTO;
import DTO.RiskDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rhys Jones
 */
public class PassengerHandler 
{
    public ArrayList<PassengerDTO> getPassengers() throws SQLException
    {
        ArrayList<PassengerDTO> passengers = new ArrayList();
        DatabaseHandler db = DatabaseHandler.getInstance();
        try
        {
            Connection conn = db.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Passenger");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                PassengerDTO passengerDetails = new PassengerDTO(rs.getInt("passenger_id"), rs.getString("forename"), rs.getString("surname"), rs.getDate("DOB"), rs.getString("nationality"), rs.getInt("passport_number"), rs.getBoolean("restricted"));
                if(passengerDetails.isRestricted() && UserHandler.getInstance().getLoggedUser().isAuthenticated())
                {
                        passengers.add(passengerDetails);
                }
                else
                {
                    passengers.add(passengerDetails);
                }
            }
            
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException sqle)
        {
            System.out.println("Error\n" + sqle);
        }
        return passengers;
    }
    
    public String checkPassengerRiskLevel(PassengerDTO passenger, int maxRiskValue)
    {
        String riskLevel = "Green";
        int totalRiskScore = 0;
        
        for (RiskDTO risk : passenger.getRisks())
        {
            totalRiskScore += risk.getRiskScore();
        }
        
        if(totalRiskScore < (maxRiskValue / 2))
        {
            riskLevel = "Green";
        }
        else if(totalRiskScore >= (maxRiskValue / 2) && totalRiskScore < maxRiskValue)
        {
            riskLevel = "Amber";
        }
        else if(totalRiskScore >= maxRiskValue)
        {
            riskLevel = "Red";
        }
        return riskLevel;
    }

    public boolean updatePassengerRestrictions(int passengerID, boolean restricted)
    {
        boolean updateOK = false;
        DatabaseHandler db = DatabaseHandler.getInstance();
        
        try
        {
            Connection conn = db.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("UPDATE PASSENGER SET RESTRICTED = ? WHERE PASSENGER_ID = ?");
            stmt.setBoolean(1, restricted);
            stmt.setInt(2, passengerID);
            int rows = stmt.executeUpdate();
            
            updateOK = rows == 1;
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(PassengerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateOK;
    }
}
