/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 64 "DomainModel.ump"
public class Combo extends Gear
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Combo Associations
  private List<SingleGear> singleItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Combo(String aName, BikeTourPlus aBikeTourPlus, SingleGear... allSingleItems)
  {
    super(aName, aBikeTourPlus);
    singleItems = new ArrayList<SingleGear>();
    boolean didAddSingleItems = setSingleItems(allSingleItems);
    if (!didAddSingleItems)
    {
      throw new RuntimeException("Unable to create Combo, must have at least 2 singleItems. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public SingleGear getSingleItem(int index)
  {
    SingleGear aSingleItem = singleItems.get(index);
    return aSingleItem;
  }

  public List<SingleGear> getSingleItems()
  {
    List<SingleGear> newSingleItems = Collections.unmodifiableList(singleItems);
    return newSingleItems;
  }

  public int numberOfSingleItems()
  {
    int number = singleItems.size();
    return number;
  }

  public boolean hasSingleItems()
  {
    boolean has = singleItems.size() > 0;
    return has;
  }

  public int indexOfSingleItem(SingleGear aSingleItem)
  {
    int index = singleItems.indexOf(aSingleItem);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfSingleItemsValid()
  {
    boolean isValid = numberOfSingleItems() >= minimumNumberOfSingleItems();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSingleItems()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addSingleItem(SingleGear aSingleItem)
  {
    boolean wasAdded = false;
    if (singleItems.contains(aSingleItem)) { return false; }
    singleItems.add(aSingleItem);
    if (aSingleItem.indexOfGearCombo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSingleItem.addGearCombo(this);
      if (!wasAdded)
      {
        singleItems.remove(aSingleItem);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeSingleItem(SingleGear aSingleItem)
  {
    boolean wasRemoved = false;
    if (!singleItems.contains(aSingleItem))
    {
      return wasRemoved;
    }

    if (numberOfSingleItems() <= minimumNumberOfSingleItems())
    {
      return wasRemoved;
    }

    int oldIndex = singleItems.indexOf(aSingleItem);
    singleItems.remove(oldIndex);
    if (aSingleItem.indexOfGearCombo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSingleItem.removeGearCombo(this);
      if (!wasRemoved)
      {
        singleItems.add(oldIndex,aSingleItem);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setSingleItems(SingleGear... newSingleItems)
  {
    boolean wasSet = false;
    ArrayList<SingleGear> verifiedSingleItems = new ArrayList<SingleGear>();
    for (SingleGear aSingleItem : newSingleItems)
    {
      if (verifiedSingleItems.contains(aSingleItem))
      {
        continue;
      }
      verifiedSingleItems.add(aSingleItem);
    }

    if (verifiedSingleItems.size() != newSingleItems.length || verifiedSingleItems.size() < minimumNumberOfSingleItems())
    {
      return wasSet;
    }

    ArrayList<SingleGear> oldSingleItems = new ArrayList<SingleGear>(singleItems);
    singleItems.clear();
    for (SingleGear aNewSingleItem : verifiedSingleItems)
    {
      singleItems.add(aNewSingleItem);
      if (oldSingleItems.contains(aNewSingleItem))
      {
        oldSingleItems.remove(aNewSingleItem);
      }
      else
      {
        aNewSingleItem.addGearCombo(this);
      }
    }

    for (SingleGear anOldSingleItem : oldSingleItems)
    {
      anOldSingleItem.removeGearCombo(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSingleItemAt(SingleGear aSingleItem, int index)
  {  
    boolean wasAdded = false;
    if(addSingleItem(aSingleItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSingleItems()) { index = numberOfSingleItems() - 1; }
      singleItems.remove(aSingleItem);
      singleItems.add(index, aSingleItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSingleItemAt(SingleGear aSingleItem, int index)
  {
    boolean wasAdded = false;
    if(singleItems.contains(aSingleItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSingleItems()) { index = numberOfSingleItems() - 1; }
      singleItems.remove(aSingleItem);
      singleItems.add(index, aSingleItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSingleItemAt(aSingleItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<SingleGear> copyOfSingleItems = new ArrayList<SingleGear>(singleItems);
    singleItems.clear();
    for(SingleGear aSingleItem : copyOfSingleItems)
    {
      aSingleItem.removeGearCombo(this);
    }
    super.delete();
  }

}