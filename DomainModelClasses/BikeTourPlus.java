/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 1 "DomainModel.ump"
public class BikeTourPlus
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BikeTourPlus Associations
  private List<User> users;
  private List<Gear> gears;
  private List<Lodge> lodges;
  private List<Season> seasons;
  private List<Tour> tours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BikeTourPlus()
  {
    users = new ArrayList<User>();
    gears = new ArrayList<Gear>();
    lodges = new ArrayList<Lodge>();
    seasons = new ArrayList<Season>();
    tours = new ArrayList<Tour>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Gear getGear(int index)
  {
    Gear aGear = gears.get(index);
    return aGear;
  }

  public List<Gear> getGears()
  {
    List<Gear> newGears = Collections.unmodifiableList(gears);
    return newGears;
  }

  public int numberOfGears()
  {
    int number = gears.size();
    return number;
  }

  public boolean hasGears()
  {
    boolean has = gears.size() > 0;
    return has;
  }

  public int indexOfGear(Gear aGear)
  {
    int index = gears.indexOf(aGear);
    return index;
  }
  /* Code from template association_GetMany */
  public Lodge getLodge(int index)
  {
    Lodge aLodge = lodges.get(index);
    return aLodge;
  }

  public List<Lodge> getLodges()
  {
    List<Lodge> newLodges = Collections.unmodifiableList(lodges);
    return newLodges;
  }

  public int numberOfLodges()
  {
    int number = lodges.size();
    return number;
  }

  public boolean hasLodges()
  {
    boolean has = lodges.size() > 0;
    return has;
  }

  public int indexOfLodge(Lodge aLodge)
  {
    int index = lodges.indexOf(aLodge);
    return index;
  }
  /* Code from template association_GetMany */
  public Season getSeason(int index)
  {
    Season aSeason = seasons.get(index);
    return aSeason;
  }

  public List<Season> getSeasons()
  {
    List<Season> newSeasons = Collections.unmodifiableList(seasons);
    return newSeasons;
  }

  public int numberOfSeasons()
  {
    int number = seasons.size();
    return number;
  }

  public boolean hasSeasons()
  {
    boolean has = seasons.size() > 0;
    return has;
  }

  public int indexOfSeason(Season aSeason)
  {
    int index = seasons.indexOf(aSeason);
    return index;
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
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    BikeTourPlus existingBikeTourPlus = aUser.getBikeTourPlus();
    boolean isNewBikeTourPlus = existingBikeTourPlus != null && !this.equals(existingBikeTourPlus);
    if (isNewBikeTourPlus)
    {
      aUser.setBikeTourPlus(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a bikeTourPlus
    if (!this.equals(aUser.getBikeTourPlus()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGears()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addGear(Gear aGear)
  {
    boolean wasAdded = false;
    if (gears.contains(aGear)) { return false; }
    BikeTourPlus existingBikeTourPlus = aGear.getBikeTourPlus();
    boolean isNewBikeTourPlus = existingBikeTourPlus != null && !this.equals(existingBikeTourPlus);
    if (isNewBikeTourPlus)
    {
      aGear.setBikeTourPlus(this);
    }
    else
    {
      gears.add(aGear);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGear(Gear aGear)
  {
    boolean wasRemoved = false;
    //Unable to remove aGear, as it must always have a bikeTourPlus
    if (!this.equals(aGear.getBikeTourPlus()))
    {
      gears.remove(aGear);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGearAt(Gear aGear, int index)
  {  
    boolean wasAdded = false;
    if(addGear(aGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGears()) { index = numberOfGears() - 1; }
      gears.remove(aGear);
      gears.add(index, aGear);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGearAt(Gear aGear, int index)
  {
    boolean wasAdded = false;
    if(gears.contains(aGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGears()) { index = numberOfGears() - 1; }
      gears.remove(aGear);
      gears.add(index, aGear);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGearAt(aGear, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLodges()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Lodge addLodge(String aName, String aAddress, int aRating)
  {
    return new Lodge(aName, aAddress, aRating, this);
  }

  public boolean addLodge(Lodge aLodge)
  {
    boolean wasAdded = false;
    if (lodges.contains(aLodge)) { return false; }
    BikeTourPlus existingBikeTourPlus = aLodge.getBikeTourPlus();
    boolean isNewBikeTourPlus = existingBikeTourPlus != null && !this.equals(existingBikeTourPlus);
    if (isNewBikeTourPlus)
    {
      aLodge.setBikeTourPlus(this);
    }
    else
    {
      lodges.add(aLodge);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLodge(Lodge aLodge)
  {
    boolean wasRemoved = false;
    //Unable to remove aLodge, as it must always have a bikeTourPlus
    if (!this.equals(aLodge.getBikeTourPlus()))
    {
      lodges.remove(aLodge);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLodgeAt(Lodge aLodge, int index)
  {  
    boolean wasAdded = false;
    if(addLodge(aLodge))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLodges()) { index = numberOfLodges() - 1; }
      lodges.remove(aLodge);
      lodges.add(index, aLodge);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLodgeAt(Lodge aLodge, int index)
  {
    boolean wasAdded = false;
    if(lodges.contains(aLodge))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLodges()) { index = numberOfLodges() - 1; }
      lodges.remove(aLodge);
      lodges.add(index, aLodge);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLodgeAt(aLodge, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSeasons()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Season addSeason(Date aStartDate, int aNumberOfWeeks, int aGuideCost)
  {
    return new Season(aStartDate, aNumberOfWeeks, aGuideCost, this);
  }

  public boolean addSeason(Season aSeason)
  {
    boolean wasAdded = false;
    if (seasons.contains(aSeason)) { return false; }
    BikeTourPlus existingBikeTourPlus = aSeason.getBikeTourPlus();
    boolean isNewBikeTourPlus = existingBikeTourPlus != null && !this.equals(existingBikeTourPlus);
    if (isNewBikeTourPlus)
    {
      aSeason.setBikeTourPlus(this);
    }
    else
    {
      seasons.add(aSeason);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeason(Season aSeason)
  {
    boolean wasRemoved = false;
    //Unable to remove aSeason, as it must always have a bikeTourPlus
    if (!this.equals(aSeason.getBikeTourPlus()))
    {
      seasons.remove(aSeason);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSeasonAt(Season aSeason, int index)
  {  
    boolean wasAdded = false;
    if(addSeason(aSeason))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeasons()) { index = numberOfSeasons() - 1; }
      seasons.remove(aSeason);
      seasons.add(index, aSeason);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeasonAt(Season aSeason, int index)
  {
    boolean wasAdded = false;
    if(seasons.contains(aSeason))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeasons()) { index = numberOfSeasons() - 1; }
      seasons.remove(aSeason);
      seasons.add(index, aSeason);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeasonAt(aSeason, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tour addTour(int aStartWeek, int aNumberOfWeeks, Season aBikingSeason, Guide aGuide, Lodge aLodging)
  {
    return new Tour(aStartWeek, aNumberOfWeeks, this, aBikingSeason, aGuide, aLodging);
  }

  public boolean addTour(Tour aTour)
  {
    boolean wasAdded = false;
    if (tours.contains(aTour)) { return false; }
    BikeTourPlus existingBikeTourPlus = aTour.getBikeTourPlus();
    boolean isNewBikeTourPlus = existingBikeTourPlus != null && !this.equals(existingBikeTourPlus);
    if (isNewBikeTourPlus)
    {
      aTour.setBikeTourPlus(this);
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
    //Unable to remove aTour, as it must always have a bikeTourPlus
    if (!this.equals(aTour.getBikeTourPlus()))
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
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (gears.size() > 0)
    {
      Gear aGear = gears.get(gears.size() - 1);
      aGear.delete();
      gears.remove(aGear);
    }
    
    while (lodges.size() > 0)
    {
      Lodge aLodge = lodges.get(lodges.size() - 1);
      aLodge.delete();
      lodges.remove(aLodge);
    }
    
    while (seasons.size() > 0)
    {
      Season aSeason = seasons.get(seasons.size() - 1);
      aSeason.delete();
      seasons.remove(aSeason);
    }
    
    while (tours.size() > 0)
    {
      Tour aTour = tours.get(tours.size() - 1);
      aTour.delete();
      tours.remove(aTour);
    }
    
  }

}