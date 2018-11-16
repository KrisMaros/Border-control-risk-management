package handler;

import DTO.FlightDTO;
import DTO.PassengerDTO;
import DTO.SeatDTO;
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
public class FlightHandler
{
    public ArrayList<PassengerDTO> viewPassengersOnFlight(int flightID)
    {
        ArrayList<PassengerDTO> passengersOnFlight = new ArrayList();
        ArrayList<SeatDTO> takenSeats = new ArrayList();
        DatabaseHandler db = DatabaseHandler.getInstance();
        try
        {
            Connection conn = db.getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SEAT WHERE FLIGHT_ID = ? AND SEAT_TAKEN = TRUE");
            stmt.setInt(1, flightID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                SeatDTO seat = new SeatDTO(rs.getInt("SEAT_ID"), rs.getInt("FLIGHT_ID"), rs.getInt("SEAT_NUMBER"), rs.getBoolean("SEAT_TAKEN"));
                takenSeats.add(seat);
            }

            for (SeatDTO seat : takenSeats)
            {
                stmt = conn.prepareStatement("SELECT * FROM BOOKING WHERE SEAT_ID = ?");
                stmt.setInt(1, seat.getSeatID());
                rs = stmt.executeQuery();

                while (rs.next())
                {
                    int passengerID = rs.getInt("PASSENGER_ID");

                    stmt = conn.prepareStatement("SELECT * FROM PASSENGER WHERE PASSENGER_ID = ?");
                    stmt.setInt(1, passengerID);
                    rs = stmt.executeQuery();

                    while (rs.next())
                    {
                        PassengerDTO passenger = new PassengerDTO(rs.getInt("passenger_id"), rs.getString("forename"), rs.getString("surname"), rs.getDate("DOB"), rs.getString("nationality"), rs.getInt("passport_number"), rs.getBoolean("restricted"));
                        
                        if (passenger.isRestricted() && UserHandler.getInstance().getLoggedUser().isAuthenticated())
                        {
                            passengersOnFlight.add(passenger);
                        } 
                        else if (passenger.isRestricted() && !(UserHandler.getInstance().getLoggedUser().isAuthenticated()))
                        {
                        }
                        else
                        {
                            passengersOnFlight.add(passenger);
                        }
                    }
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
        return passengersOnFlight;
    }
    
    public int getFlightsMaxRiskValue(int flightID)
    {
        int flightsMaxRiskValue = 0;
        
        DatabaseHandler db = DatabaseHandler.getInstance();
        try
        {
            Connection conn = db.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FLIGHT WHERE FLIGHT_ID = ?");
            stmt.setInt(1, flightID);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                flightsMaxRiskValue = rs.getInt("MAX_RISK");
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(FlightHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flightsMaxRiskValue;
    }

    public boolean updateMaxRiskValue(int flightID, int newMaxRiskValue)
    {
        boolean updateOK = false;
        DatabaseHandler db = DatabaseHandler.getInstance();

        try
        {
            Connection conn = db.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("UPDATE FLIGHT SET MAX_RISK = ? WHERE FLIGHT_ID = ?");
            stmt.setInt(1, newMaxRiskValue);
            stmt.setInt(2, flightID);
            int rows = stmt.executeUpdate();
            
            updateOK = rows == 1;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FlightHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateOK;
    }
}
