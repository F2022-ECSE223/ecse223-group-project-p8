/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 36 "DomainModel.ump"
public class Season
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Season Attributes
  private Date startDate;
  private int numberOfWeeks;
  private int guideCost;

  //Season Associations
  private BikeTourPlus bikeTourPlus;
  private List<Tour> tours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Season(Date aStartDate, int aNumberOfWeeks, int aGuideCost, BikeTourPlus aBikeTourPlus)
  {
    startDate = aStartDate;
    numberOfWeeks = aNumberOfWeeks;
    guideCost = aGuideCost;
    boolean didAddBikeTourPlus = setBikeTourPlus(aBikeTourPlus);
    if (!didAddBikeTourPlus)
    {
      throw new RuntimeException("Unable to create season due to bikeTourPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    tours = new ArrayList<Tour>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfWeeks(int aNumberOfWeeks)
  {
    boolean wasSet = false;
    numberOfWeeks = aNumberOfWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideCost(int aGuideCost)
  {
    boolean wasSet = false;
    guideCost = aGuideCost;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public int getNumberOfWeeks()
  {
    return numberOfWeeks;
  }

  public int getGuideCost()
  {
    return guideCost;
  }
  /* Code from template association_GetOne */
  public BikeTourPlus getBikeTourPlus()
  {
    return bikeTourPlus;
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
  /* Code from template association_SetOneToMany */
  public boolean setBikeTourPlus(BikeTourPlus aBikeTourPlus)
  {
    boolean wasSet = false;
    if (aBikeTourPlus == null)
    {
      return wasSet;
    }

    BikeTourPlus existingBikeTourPlus = bikeTourPlus;
    bikeTourPlus = aBikeTourPlus;
    if (existingBikeTourPlus != null && !existingBikeTourPlus.equals(aBikeTourPlus))
    {
      existingBikeTourPlus.removeSeason(this);
    }
    bikeTourPlus.addSeason(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tour addTour(int aStartWeek, int aNumberOfWeeks, BikeTourPlus aBikeTourPlus, Guide aGuide, Lodge aLodging)
  {
    return new Tour(aStartWeek, aNumberOfWeeks, aBikeTourPlus, this, aGuide, aLodging);
  }

  public boolean addTour(Tour aTour)
  {
    boolean wasAdded = false;
    if (tours.contains(aTour)) { return false; }
    Season existingBikingSeason = aTour.getBikingSeason();
    boolean isNewBikingSeason = existingBikingSeason != null && !this.equals(existingBikingSeason);
    if (isNewBikingSeason)
    {
      aTour.setBikingSeason(this);
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
    //Unable to remove aTour, as it must always have a bikingSeason
    if (!this.equals(aTour.getBikingSeason()))
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
    BikeTourPlus placeholderBikeTourPlus = bikeTourPlus;
    this.bikeTourPlus = null;
    if(placeholderBikeTourPlus != null)
    {
      placeholderBikeTourPlus.removeSeason(this);
    }
    while (tours.size() > 0)
    {
      Tour aTour = tours.get(tours.size() - 1);
      aTour.delete();
      tours.remove(aTour);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfWeeks" + ":" + getNumberOfWeeks()+ "," +
            "guideCost" + ":" + getGuideCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "bikeTourPlus = "+(getBikeTourPlus()!=null?Integer.toHexString(System.identityHashCode(getBikeTourPlus())):"null");
  }
}