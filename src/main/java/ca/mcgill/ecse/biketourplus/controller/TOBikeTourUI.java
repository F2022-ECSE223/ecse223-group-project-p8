package ca.mcgill.ecse.biketourplus.controller;
import java.util.*;

public class TOBikeTourUI
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBikeTour Attributes
  private int id;
  private String guideName;
  private int startWeek;
  private int endWeek;
  private String participants;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBikeTourUI(int aId, String aGuideName, int aStartWeek, int aEndWeek,  String apaticipants)
  {
    id = aId;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    guideName = aGuideName;
    participants = apaticipants;
  }
  
  public int getId()
  {
    return id;
  }
  public String getGuideName()
  {
    return guideName;
  }
  public int getStartWeek()
  {
    return startWeek;
  }
  public int getEndWeek()
  {
    return endWeek;
  }
  public String getParticipants()
  {
    return participants;
  }
  
  
  
  
}