/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 31 "DomainModel.ump"
public class Guide extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide Attributes
  private String emergencyContact;

  //Guide Associations
  private List<Tour> tours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aEmail, String aPassword, BikeTourPlus aBikeTourPlus, String aEmergencyContact)
  {
    super(aEmail, aPassword, aBikeTourPlus);
    emergencyContact = aEmergencyContact;
    tours = new ArrayList<Tour>();
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

  public String getEmergencyContact()
  {
    return emergencyContact;
  }
  /* Code from template association_GetMany */
  public Tour getTour(int index)
  {
    Tour aTour = tours.get(index);
    return aTour;
  }

  public List<Tour> getTours()
  {
    List<Tour> newTours = Collections.unmodifiableList(tours);
    return newTours;
  }

  public int numberOfTours()
  {
    int number = tours.size();
    return number;
  }

  public boolean hasTours()
  {
    boolean has = tours.size() > 0;
    return has;
  }

  public int indexOfTour(Tour aTour)
  {
    int index = tours.indexOf(aTour);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tour addTour(int aStartWeek, int aNumberOfWeeks, BikeTourPlus aBikeTourPlus, Season aBikingSeason, Lodge aLodging)
  {
    return new Tour(aStartWeek, aNumberOfWeeks, aBikeTourPlus, aBikingSeason, this, aLodging);
  }

  public boolean addTour(Tour aTour)
  {
    boolean wasAdded = false;
    if (tours.contains(aTour)) { return false; }
    Guide existingGuide = aTour.getGuide();
    boolean isNewGuide = existingGuide != null && !this.equals(existingGuide);
    if (isNewGuide)
    {
      aTour.setGuide(this);
    }
    else
    {
      tours.add(aTour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTour(Tour aTour)
  {
    boolean wasRemoved = false;
    //Unable to remove aTour, as it must always have a guide
    if (!this.equals(aTour.getGuide()))
    {
      tours.remove(aTour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTourAt(Tour aTour, int index)
  {  
    boolean wasAdded = false;
    if(addTour(aTour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTours()) { index = numberOfTours() - 1; }
      tours.remove(aTour);
      tours.add(index, aTour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTourAt(Tour aTour, int index)
  {
    boolean wasAdded = false;
    if(tours.contains(aTour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTours()) { index = numberOfTours() - 1; }
      tours.remove(aTour);
      tours.add(index, aTour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTourAt(aTour, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=tours.size(); i > 0; i--)
    {
      Tour aTour = tours.get(i - 1);
      aTour.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "emergencyContact" + ":" + getEmergencyContact()+ "]";
  }
}