package DTO;

/**
 *
 * @author ESA
 */
public class RiskDTO 
{
    private int riskID;
    private String riskDesc;
    private int riskScore;

    public RiskDTO()
    {
        this.riskID = 0;
        this.riskDesc = "";
        this.riskScore = 0;
    }
    
    /**
     * Constructs a Risk
     * @param riskID
     * @param riskDesc
     * @param riskScore 
     */
    public RiskDTO(int riskID, String riskDesc, int riskScore)
    {
        this.riskID = riskID;
        this.riskDesc = riskDesc;
        this.riskScore = riskScore;
    }

    public int getRiskID()
    {
        return riskID;
    }

    public void setRiskID(int riskID)
    {
        this.riskID = riskID;
    }

    public String getRiskDesc()
    {
        return riskDesc;
    }

    public void setRiskDesc(String riskDesc)
    {
        this.riskDesc = riskDesc;
    }

    public int getRiskScore()
    {
        return riskScore;
    }

    public void setRiskScore(int riskScore)
    {
        this.riskScore = riskScore;
    }

    @Override
    public String toString()
    {
        return "riskID: " + riskID + ", riskDesc: " + riskDesc + ", riskScore: " + riskScore;
    }
    
    
}
