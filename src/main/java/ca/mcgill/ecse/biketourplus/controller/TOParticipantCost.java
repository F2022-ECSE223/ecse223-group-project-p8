/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.biketourplus.controller;

// line 14 "../../../../../BikeTourPlusTransferObjects.ump"
public class TOParticipantCost
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOParticipantCost Attributes
  private String participantEmail;
  private String participantName;
  private int totalCostForBookableItems;
  private int totalCostForBikingTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOParticipantCost(String aParticipantEmail, String aParticipantName, int aTotalCostForBookableItems, int aTotalCostForBikingTour)
  {
    participantEmail = aParticipantEmail;
    participantName = aParticipantName;
    totalCostForBookableItems = aTotalCostForBookableItems;
    totalCostForBikingTour = aTotalCostForBikingTour;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getParticipantEmail()
  {
    return participantEmail;
  }

  public String getParticipantName()
  {
    return participantName;
  }

  public int getTotalCostForBookableItems()
  {
    return totalCostForBookableItems;
  }

  public int getTotalCostForBikingTour()
  {
    return totalCostForBikingTour;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "participantEmail" + ":" + getParticipantEmail()+ "," +
            "participantName" + ":" + getParticipantName()+ "," +
            "totalCostForBookableItems" + ":" + getTotalCostForBookableItems()+ "," +
            "totalCostForBikingTour" + ":" + getTotalCostForBikingTour()+ "]";
  }
}