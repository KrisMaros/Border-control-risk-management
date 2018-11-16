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
 * @author ESA
 */
public class RiskHandler 
{
    public ArrayList<PassengerDTO> checkAllRisksOnFlight(int FlightID)
    {
        ArrayList<PassengerDTO> passengersOnFlight = new FlightHandler().viewPassengersOnFlight(FlightID);
        
        for(PassengerDTO passenger : passengersOnFlight)
        {
            try
            {
                getRisksForPassenger(passenger);
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(RiskHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return passengersOnFlight;
    }
    
    public ArrayList<RiskDTO> getRisksForPassenger(PassengerDTO passenger) throws SQLException
    {
        ArrayList<RiskDTO> risks = new ArrayList();
        ArrayList<Integer> riskIDs = new ArrayList();
        DatabaseHandler db = DatabaseHandler.getInstance();
        try
        {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM passenger_risk WHERE passenger_id = ?");
            stmt.setInt(1, passenger.getPassengerID());
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                riskIDs.add(rs.getInt("risk_id"));
            }
            
            for(int riskID : riskIDs)
            {
                stmt = conn.prepareStatement("SELECT * FROM risk WHERE risk_id = ?");
                stmt.setInt(1, riskID);
                rs = stmt.executeQuery();
                
                while(rs.next())
                {
                    RiskDTO risk = new RiskDTO(rs.getInt("risk_id"), rs.getString("risk_desc"), rs.getInt("risk_score"));
                    risks.add(risk);
                    passenger.addRisk(risk);
                }
            }
        }
        catch (SQLException sqle)
        {
            System.out.println("ERROR\n" + sqle);
        }
        return risks;
    }

    public boolean insertNewRiskValue(RiskDTO newRisk)
    {
        boolean insertOK = false;
        DatabaseHandler db = DatabaseHandler.getInstance();
        
        try
        {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO RISK(RISK_DESC, RISK_SCORE) VALUES(?, ?)");
            stmt.setString(1, newRisk.getRiskDesc());
            stmt.setInt(2, newRisk.getRiskScore());
            
            int rows = stmt.executeUpdate();
            
            insertOK = rows == 1;
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(RiskHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return insertOK;
    }
}