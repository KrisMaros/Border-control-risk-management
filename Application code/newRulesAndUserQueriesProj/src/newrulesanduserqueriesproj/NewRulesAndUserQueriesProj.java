package newrulesanduserqueriesproj;

import DTO.PassengerDTO;
import DTO.RiskDTO;
import DTO.UserDTO;
import handler.FlightHandler;
import handler.PassengerHandler;
import handler.RiskHandler;
import handler.UserHandler;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ESA
 */
public class NewRulesAndUserQueriesProj
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        boolean exit = false;
        Scanner kybd = new Scanner(System.in);
        while (!exit)
        {
            System.out.println("===================");
            System.out.printf("%12s", "MENU\n");
            System.out.println("===================");
            System.out.println("1. Login");
            System.out.println("2. Check the risks of all the passengers on a flight");
            System.out.println("3. Set the maximum risk value for a flight");
            System.out.println("4. Create new risk");
            System.out.println("5. Restrict a passenger's details");
            System.out.println("6. De-restrict a passenger's details");
            System.out.println("0. Exit");
            System.out.print("Enter: ");
            int input = kybd.nextInt();
            kybd.nextLine(); //flush buffer
            
            if(input == 1)
            {
                login();
            }
            
            if(input == 2)
            {
                checkAllPassengerRisksOnFlight(); //this is for the new rules task.
            }
            
            if(input == 3)
            {
                if(UserHandler.getInstance().getLoggedUser().isAuthenticated())
                {
                    setNewMaximumRiskForFlight();
                }
                else
                {
                    System.out.println("You need to be authenticated to do this.");
                }
            }

            if (input == 4)
            {
                if (UserHandler.getInstance().getLoggedUser().isAuthenticated())
                {
                    createNewRisk();
                }
                else
                {
                    System.out.println("You need to be authenticated to do this.");
                }
            }
            if (input == 5)
            {
                if (UserHandler.getInstance().getLoggedUser().isAuthenticated())
                {
                    updatePassengerRestrictions(1);
                }
                else
                {
                    System.out.println("You need to be authenticated to do this.");
                }
            }

            if (input == 6)
            {
                if (UserHandler.getInstance().getLoggedUser().isAuthenticated())
                {
                    updatePassengerRestrictions(0);
                }
                else
                {
                    System.out.println("You need to be authenticated to do this.");
                }
            }

            if (input == 0)
            {
                exit = true;
            }
        }
        //hardcodedRiskMethod(); <- This was for the new rules task.
    }
    
    //OUTDATED IGNORE
    public static void hardcodedRiskMethod()
    {
        ArrayList<PassengerDTO> passengers;
        try
        {
            passengers = (new PassengerHandler().getPassengers());

            for (PassengerDTO passenger : passengers)
            {
                ArrayList<RiskDTO> pRisks = (new RiskHandler().getRisksForPassenger(passenger));
                System.out.println(passenger.toString());
                System.out.println("Passenger " + passenger.getPassengerID() + "'s risks:");

                for (RiskDTO risk : pRisks)
                {
                    System.out.println(risk.toString());
                }

                String totalRiskScore = (new PassengerHandler().checkPassengerRiskLevel(passenger, 30));
                System.out.println("RISK LEVEL: " + totalRiskScore);

                if (totalRiskScore.equalsIgnoreCase("red"))
                {
                    System.out.println("WARNING: PASSENGER CANNOT FLY / ALERT AUTHORITIES");
                }
                System.out.println("\n");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(NewRulesAndUserQueriesProj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setNewMaximumRiskForFlight()
    {
        Scanner kybd = new Scanner(System.in);
        System.out.println("Flight ID: ");
        int flightID = kybd.nextInt();
        System.out.println("New max risk value: ");
        int newMaxRiskValue = kybd.nextInt();
        kybd.nextLine(); // flush buffer
        
        boolean updateOK = new FlightHandler().updateMaxRiskValue(flightID, newMaxRiskValue);
        
        if(updateOK)
        {
            System.out.println("UPDATE SUCCESS");
        }
        else
        {
            System.out.println("UPDATE FAILURE");
        }
    }
    
    public static void checkAllPassengerRisksOnFlight()
    {
        Scanner kybd = new Scanner(System.in);
        System.out.print("Flight ID: ");
        int flightID = kybd.nextInt();
        kybd.nextLine(); // flush buffer
        
        ArrayList<PassengerDTO> passengersOnFlight = new RiskHandler().checkAllRisksOnFlight(flightID);
        
        int flightsMaxRiskValue = new FlightHandler().getFlightsMaxRiskValue(flightID);
        
        for (PassengerDTO passenger : passengersOnFlight)
        {
            String passengerRiskLevel = new PassengerHandler().checkPassengerRiskLevel(passenger, flightsMaxRiskValue);

            System.out.println(passenger.toString());
            System.out.println(passenger.getRisks().toString());
            System.out.println("RISK LEVEL: " + passengerRiskLevel);

            if (passengerRiskLevel.equalsIgnoreCase("red"))
            {
                System.out.println("WARNING: PASSENGER CANNOT FLY / ALERT AUTHORITIES");
            }
            System.out.println("\n");
        }
    }
    
    public static boolean login()
    {
        Scanner kybd = new Scanner(System.in);
        System.out.print("Username: ");
        String username = kybd.nextLine();
        System.out.print("Password: ");
        String password = kybd.nextLine();

        try
        {
            byte[] hash = MessageDigest.getInstance("SHA-256")
                    .digest(password.getBytes(StandardCharsets.UTF_8));

            password = Base64.getEncoder().encodeToString(hash);

        }
        catch (NoSuchAlgorithmException nsae)
        {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, nsae);
        }

        UserDTO user = new UserDTO(username, password, false);
        
        boolean correctLogin = UserHandler.getInstance().checkLoginCredentials(user);
        
        return correctLogin;
    }

    private static void createNewRisk()
    {
        Scanner kybd = new Scanner(System.in);
        System.out.print("Risk description: ");
        String riskDesc = kybd.nextLine();
        System.out.print("Risk score: ");
        int riskScore = kybd.nextInt();
        kybd.nextLine(); // flush buffer
        
        RiskDTO newRisk = new RiskDTO(0, riskDesc, riskScore);
        
        boolean insertOK = new RiskHandler().insertNewRiskValue(newRisk);
        
        if(insertOK)
        {
            System.out.println("INSERT SUCCESS");
        }
        else
        {
            System.out.println("INSERT FAILURE");
        }
    }

    private static void updatePassengerRestrictions(int i)
    {
        boolean updateOK = false;
        Scanner kybd = new Scanner(System.in);
        System.out.print("Passenger ID: ");
        int passengerID = kybd.nextInt();
        
        if(i == 1)
        {
            updateOK = new PassengerHandler().updatePassengerRestrictions(passengerID, true);
        }
        else if(i == 0)
        {
            updateOK = new PassengerHandler().updatePassengerRestrictions(passengerID, false);
        }
        
        if(updateOK)
        {
            System.out.println("UPDATE SUCCESS");
        }
        else
        {
            System.out.println("UPDATE FAILURE");
        }
    }
}
