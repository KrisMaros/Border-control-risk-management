package DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author ESA
 */
public class PassengerDTO 
{
    private int passengerID;
    private String forename;
    private String surname;
    private Calendar DOB = new GregorianCalendar(00,00,00);
    private String nationality;
    private int passportNumber;
    private ArrayList<RiskDTO> risks;
    private boolean restricted;

    public PassengerDTO()
    {
        this.passengerID = 0;
        this.forename = "";
        this.surname = "";
        this.nationality = "";
        this.passportNumber = 0;
        this.restricted = false;
    }

    public PassengerDTO(int passengerID, String forename, String surname, Date DOB, String nationality, int passportNumber, boolean restricted)
    {
        risks = new ArrayList<>();
        this.passengerID = passengerID;
        this.forename = forename;
        this.surname = surname;
        this.DOB.setTime(DOB);
        this.nationality = nationality;
        this.passportNumber = passportNumber;
        this.restricted = restricted;
    }

    public boolean isRestricted()
    {
        return restricted;
    }

    public void setRestricted(boolean restricted)
    {
        this.restricted = restricted;
    }

    public int getPassengerID()
    {
        return passengerID;
    }

    public void setPassengerID(int passengerID)
    {
        this.passengerID = passengerID;
    }

    public String getForename()
    {
        return forename;
    }

    public void setForename(String forename)
    {
        this.forename = forename;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public Calendar getDOB()
    {
        return DOB;
    }

    public void setDOB(Calendar DOB)
    {
        this.DOB = DOB;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public int getPassportNumber()
    {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber)
    {
        this.passportNumber = passportNumber;
    }
    
    public void addRisk(RiskDTO risk)
    {
        this.risks.add(risk);
    }
    
    public ArrayList<RiskDTO> getRisks()
    {
        return risks;
    }

    @Override
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "passengerID: " + passengerID + ", forename: " + forename + ", surname: " + surname + ", DOB: " + sdf.format(this.DOB.getTime()) + ", nationality: " + nationality + ", passportNumber: " + passportNumber + ", restricted: " + restricted;
    }
}
