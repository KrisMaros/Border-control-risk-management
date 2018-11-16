package DTO;

/**
 *
 * @author ESA
 */
public class SeatDTO
{
    private int seatID;
    private int flightID;
    private int seatNumber;
    private boolean seatTaken;

    public SeatDTO()
    {
    }

    public SeatDTO(int seatID, int flightID, int seatNumber, boolean seatTaken)
    {
        this.seatID = seatID;
        this.flightID = flightID;
        this.seatNumber = seatNumber;
        this.seatTaken = seatTaken;
    }

    public int getSeatID()
    {
        return seatID;
    }

    public void setSeatID(int seatID)
    {
        this.seatID = seatID;
    }

    public int getFlightID()
    {
        return flightID;
    }

    public void setFlightID(int flightID)
    {
        this.flightID = flightID;
    }

    public int getSeatNumber()
    {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber)
    {
        this.seatNumber = seatNumber;
    }

    public boolean isSeatTaken()
    {
        return seatTaken;
    }

    public void setSeatTaken(boolean seatTaken)
    {
        this.seatTaken = seatTaken;
    }

    @Override
    public String toString()
    {
        return "SEAT" + "\nseatID: " + seatID + "\nflightID: " + flightID + "\nseatNumber: " + seatNumber + "\nseatTaken: " + seatTaken;
    }
    
    
}
