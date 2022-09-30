/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 42 "DomainModel.ump"
public class Tour
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tour Attributes
  private int startWeek;
  private int numberOfWeeks;

  //Tour Associations
  private BikeTourPlus bikeTourPlus;
  private Season bikingSeason;
  private List<Participant> bikers;
  private Guide guide;
  private Lodge lodging;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tour(int aStartWeek, int aNumberOfWeeks, BikeTourPlus aBikeTourPlus, Season aBikingSeason, Guide aGuide, Lodge aLodging)
  {
    startWeek = aStartWeek;
    numberOfWeeks = aNumberOfWeeks;
    boolean didAddBikeTourPlus = setBikeTourPlus(aBikeTourPlus);
    if (!didAddBikeTourPlus)
    {
      throw new RuntimeException("Unable to create tour due to bikeTourPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBikingSeason = setBikingSeason(aBikingSeason);
    if (!didAddBikingSeason)
    {
      throw new RuntimeException("Unable to create tour due to bikingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bikers = new ArrayList<Participant>();
    boolean didAddGuide = setGuide(aGuide);
    if (!didAddGuide)
    {
      throw new RuntimeException("Unable to create tour due to guide. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setLodging(aLodging))
    {
      throw new RuntimeException("Unable to create Tour due to aLodging. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
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

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getNumberOfWeeks()
  {
    return numberOfWeeks;
  }

  public int getEndWeek()
  {
    return startWeek + numberOfWeeks;
  }
  /* Code from template association_GetOne */
  public BikeTourPlus getBikeTourPlus()
  {
    return bikeTourPlus;
  }
  /* Code from template association_GetOne */
  public Season getBikingSeason()
  {
    return bikingSeason;
  }
  /* Code from template association_GetMany */
  public Participant getBiker(int index)
  {
    Participant aBiker = bikers.get(index);
    return aBiker;
  }

  public List<Participant> getBikers()
  {
    List<Participant> newBikers = Collections.unmodifiableList(bikers);
    return newBikers;
  }

  public int numberOfBikers()
  {
    int number = bikers.size();
    return number;
  }

  public boolean hasBikers()
  {
    boolean has = bikers.size() > 0;
    return has;
  }

  public int indexOfBiker(Participant aBiker)
  {
    int index = bikers.indexOf(aBiker);
    return index;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }
  /* Code from template association_GetOne */
  public Lodge getLodging()
  {
    return lodging;
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
      existingBikeTourPlus.removeTour(this);
    }
    bikeTourPlus.addTour(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBikingSeason(Season aBikingSeason)
  {
    boolean wasSet = false;
    if (aBikingSeason == null)
    {
      return wasSet;
    }

    Season existingBikingSeason = bikingSeason;
    bikingSeason = aBikingSeason;
    if (existingBikingSeason != null && !existingBikingSeason.equals(aBikingSeason))
    {
      existingBikingSeason.removeTour(this);
    }
    bikingSeason.addTour(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBikers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addBiker(Participant aBiker)
  {
    boolean wasAdded = false;
    if (bikers.contains(aBiker)) { return false; }
    Tour existingOnlyTour = aBiker.getOnlyTour();
    if (existingOnlyTour == null)
    {
      aBiker.setOnlyTour(this);
    }
    else if (!this.equals(existingOnlyTour))
    {
      existingOnlyTour.removeBiker(aBiker);
      addBiker(aBiker);
    }
    else
    {
      bikers.add(aBiker);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBiker(Participant aBiker)
  {
    boolean wasRemoved = false;
    if (bikers.contains(aBiker))
    {
      bikers.remove(aBiker);
      aBiker.setOnlyTour(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBikerAt(Participant aBiker, int index)
  {  
    boolean wasAdded = false;
    if(addBiker(aBiker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBikers()) { index = numberOfBikers() - 1; }
      bikers.remove(aBiker);
      bikers.add(index, aBiker);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBikerAt(Participant aBiker, int index)
  {
    boolean wasAdded = false;
    if(bikers.contains(aBiker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBikers()) { index = numberOfBikers() - 1; }
      bikers.remove(aBiker);
      bikers.add(index, aBiker);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBikerAt(aBiker, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    if (aGuide == null)
    {
      return wasSet;
    }

    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeTour(this);
    }
    guide.addTour(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setLodging(Lodge aNewLodging)
  {
    boolean wasSet = false;
    if (aNewLodging != null)
    {
      lodging = aNewLodging;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    BikeTourPlus placeholderBikeTourPlus = bikeTourPlus;
    this.bikeTourPlus = null;
    if(placeholderBikeTourPlus != null)
    {
      placeholderBikeTourPlus.removeTour(this);
    }
    Season placeholderBikingSeason = bikingSeason;
    this.bikingSeason = null;
    if(placeholderBikingSeason != null)
    {
      placeholderBikingSeason.removeTour(this);
    }
    while( !bikers.isEmpty() )
    {
      bikers.get(0).setOnlyTour(null);
    }
    Guide placeholderGuide = guide;
    this.guide = null;
    if(placeholderGuide != null)
    {
      placeholderGuide.removeTour(this);
    }
    lodging = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "startWeek" + ":" + getStartWeek()+ "," +
            "numberOfWeeks" + ":" + getNumberOfWeeks()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bikeTourPlus = "+(getBikeTourPlus()!=null?Integer.toHexString(System.identityHashCode(getBikeTourPlus())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bikingSeason = "+(getBikingSeason()!=null?Integer.toHexString(System.identityHashCode(getBikingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "lodging = "+(getLodging()!=null?Integer.toHexString(System.identityHashCode(getLodging())):"null");
  }
}