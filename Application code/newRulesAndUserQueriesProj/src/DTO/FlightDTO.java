package DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author ESA
 */
public class FlightDTO 
{
    private int flightID;
    private int planeID;
    private String destination;
    private String origin;
    private Calendar departureDate = new GregorianCalendar(00,00,00);
    private Calendar arrivalDate = new GregorianCalendar(00,00,00);
    private Calendar estimatedTimeOfArrival = new GregorianCalendar(00,00,00);
    private int maxRiskValue;

    public FlightDTO() 
    {
        
    }

    public FlightDTO(int flightID, int planeID, String destination, String origin, Date departureDate, Date arrivalDate)
    {
        this.flightID = flightID;
        this.planeID = planeID;
        this.destination = destination;
        this.origin = origin;
        this.departureDate.setTime(departureDate);
        this.arrivalDate.setTime(arrivalDate);
    }

    public int getFlightID()
    {
        return flightID;
    }

    public void setFlightID(int flightID)
    {
        this.flightID = flightID;
    }

    public int getPlaneID()
    {
        return planeID;
    }

    public void setPlaneID(int planeID)
    {
        this.planeID = planeID;
    }

    public String getDestination()
    {
        return destination;
    }

    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String origin)
    {
        this.origin = origin;
    }

    public Calendar getDepartureDate()
    {
        return departureDate;
    }

    public void setDepartureDate(Calendar departureDate)
    {
        this.departureDate = departureDate;
    }

    public Calendar getArrivalDate()
    {
        return arrivalDate;
    }

    public void setArrivalDate(Calendar arrivalDate)
    {
        this.arrivalDate = arrivalDate;
    }

    public Calendar getEstimatedTimeOfArrival()
    {
        return estimatedTimeOfArrival;
    }

    public void setEstimatedTimeOfArrival(Calendar estimatedTimeOfArrival)
    {
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
    }

    public int getMaxRiskValue()
    {
        return maxRiskValue;
    }

    public void setMaxRiskValue(int maxRiskValue)
    {
        this.maxRiskValue = maxRiskValue;
    }

    @Override
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "flightID: " + flightID + "\nplaneID: " + planeID + "\ndestination: " + destination + "\norigin: " + origin + "\ndepartureDate: " + sdf.format(this.departureDate.getTime()) + "\narrivalDate: " + sdf.format(this.arrivalDate.getTime()) + "\nestimatedTimeOfArrival: " + estimatedTimeOfArrival + "\nmaxRiskValue: " + maxRiskValue;
    }
}
