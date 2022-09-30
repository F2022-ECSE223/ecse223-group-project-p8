/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 20 "DomainModel.ump"
public class Participant extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Participant Attributes
  private String emergencyContact;
  private int startWeekAvailability;
  private int endWeekAvailability;
  private int desiredWeeks;
  private boolean lodgeRequested;
  private boolean hasCancelled;
  private String authCode;

  //Participant Associations
  private Tour onlyTour;
  private List<Gear> equipment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Participant(String aEmail, String aPassword, BikeTourPlus aBikeTourPlus, String aEmergencyContact, int aStartWeekAvailability, int aEndWeekAvailability, int aDesiredWeeks, boolean aLodgeRequested, boolean aHasCancelled, String aAuthCode)
  {
    super(aEmail, aPassword, aBikeTourPlus);
    emergencyContact = aEmergencyContact;
    startWeekAvailability = aStartWeekAvailability;
    endWeekAvailability = aEndWeekAvailability;
    desiredWeeks = aDesiredWeeks;
    lodgeRequested = aLodgeRequested;
    hasCancelled = aHasCancelled;
    authCode = aAuthCode;
    equipment = new ArrayList<Gear>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmergencyContact(String aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeekAvailability(int aStartWeekAvailability)
  {
    boolean wasSet = false;
    startWeekAvailability = aStartWeekAvailability;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeekAvailability(int aEndWeekAvailability)
  {
    boolean wasSet = false;
    endWeekAvailability = aEndWeekAvailability;
    wasSet = true;
    return wasSet;
  }

  public boolean setDesiredWeeks(int aDesiredWeeks)
  {
    boolean wasSet = false;
    desiredWeeks = aDesiredWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setLodgeRequested(boolean aLodgeRequested)
  {
    boolean wasSet = false;
    lodgeRequested = aLodgeRequested;
    wasSet = true;
    return wasSet;
  }

  public boolean setHasCancelled(boolean aHasCancelled)
  {
    boolean wasSet = false;
    hasCancelled = aHasCancelled;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthCode(String aAuthCode)
  {
    boolean wasSet = false;
    authCode = aAuthCode;
    wasSet = true;
    return wasSet;
  }

  public String getEmergencyContact()
  {
    return emergencyContact;
  }

  public int getStartWeekAvailability()
  {
    return startWeekAvailability;
  }

  public int getEndWeekAvailability()
  {
    return endWeekAvailability;
  }

  public int getDesiredWeeks()
  {
    return desiredWeeks;
  }

  public boolean getLodgeRequested()
  {
    return lodgeRequested;
  }

  public boolean getHasCancelled()
  {
    return hasCancelled;
  }

  public String getAuthCode()
  {
    return authCode;
  }
  /* Code from template association_GetOne */
  public Tour getOnlyTour()
  {
    return onlyTour;
  }

  public boolean hasOnlyTour()
  {
    boolean has = onlyTour != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Gear getEquipment(int index)
  {
    Gear aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Gear> getEquipment()
  {
    List<Gear> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Gear aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setOnlyTour(Tour aOnlyTour)
  {
    boolean wasSet = false;
    Tour existingOnlyTour = onlyTour;
    onlyTour = aOnlyTour;
    if (existingOnlyTour != null && !existingOnlyTour.equals(aOnlyTour))
    {
      existingOnlyTour.removeBiker(this);
    }
    if (aOnlyTour != null)
    {
      aOnlyTour.addBiker(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addEquipment(Gear aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    equipment.add(aEquipment);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Gear aEquipment)
  {
    boolean wasRemoved = false;
    if (equipment.contains(aEquipment))
    {
      equipment.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Gear aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Gear aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    if (onlyTour != null)
    {
      Tour placeholderOnlyTour = onlyTour;
      this.onlyTour = null;
      placeholderOnlyTour.removeBiker(this);
    }
    equipment.clear();
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "emergencyContact" + ":" + getEmergencyContact()+ "," +
            "startWeekAvailability" + ":" + getStartWeekAvailability()+ "," +
            "endWeekAvailability" + ":" + getEndWeekAvailability()+ "," +
            "desiredWeeks" + ":" + getDesiredWeeks()+ "," +
            "lodgeRequested" + ":" + getLodgeRequested()+ "," +
            "hasCancelled" + ":" + getHasCancelled()+ "," +
            "authCode" + ":" + getAuthCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "onlyTour = "+(getOnlyTour()!=null?Integer.toHexString(System.identityHashCode(getOnlyTour())):"null");
  }
}