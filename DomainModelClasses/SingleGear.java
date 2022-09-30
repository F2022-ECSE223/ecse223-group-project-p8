/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 59 "DomainModel.ump"
public class SingleGear extends Gear
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SingleGear Attributes
  private int price;

  //SingleGear Associations
  private List<Combo> gearCombos;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SingleGear(String aName, BikeTourPlus aBikeTourPlus, int aPrice)
  {
    super(aName, aBikeTourPlus);
    price = aPrice;
    gearCombos = new ArrayList<Combo>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public int getPrice()
  {
    return price;
  }
  /* Code from template association_GetMany */
  public Combo getGearCombo(int index)
  {
    Combo aGearCombo = gearCombos.get(index);
    return aGearCombo;
  }

  public List<Combo> getGearCombos()
  {
    List<Combo> newGearCombos = Collections.unmodifiableList(gearCombos);
    return newGearCombos;
  }

  public int numberOfGearCombos()
  {
    int number = gearCombos.size();
    return number;
  }

  public boolean hasGearCombos()
  {
    boolean has = gearCombos.size() > 0;
    return has;
  }

  public int indexOfGearCombo(Combo aGearCombo)
  {
    int index = gearCombos.indexOf(aGearCombo);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGearCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addGearCombo(Combo aGearCombo)
  {
    boolean wasAdded = false;
    if (gearCombos.contains(aGearCombo)) { return false; }
    gearCombos.add(aGearCombo);
    if (aGearCombo.indexOfSingleItem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aGearCombo.addSingleItem(this);
      if (!wasAdded)
      {
        gearCombos.remove(aGearCombo);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeGearCombo(Combo aGearCombo)
  {
    boolean wasRemoved = false;
    if (!gearCombos.contains(aGearCombo))
    {
      return wasRemoved;
    }

    int oldIndex = gearCombos.indexOf(aGearCombo);
    gearCombos.remove(oldIndex);
    if (aGearCombo.indexOfSingleItem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aGearCombo.removeSingleItem(this);
      if (!wasRemoved)
      {
        gearCombos.add(oldIndex,aGearCombo);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGearComboAt(Combo aGearCombo, int index)
  {  
    boolean wasAdded = false;
    if(addGearCombo(aGearCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGearCombos()) { index = numberOfGearCombos() - 1; }
      gearCombos.remove(aGearCombo);
      gearCombos.add(index, aGearCombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGearComboAt(Combo aGearCombo, int index)
  {
    boolean wasAdded = false;
    if(gearCombos.contains(aGearCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGearCombos()) { index = numberOfGearCombos() - 1; }
      gearCombos.remove(aGearCombo);
      gearCombos.add(index, aGearCombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGearComboAt(aGearCombo, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Combo> copyOfGearCombos = new ArrayList<Combo>(gearCombos);
    gearCombos.clear();
    for(Combo aGearCombo : copyOfGearCombos)
    {
      if (aGearCombo.numberOfSingleItems() <= Combo.minimumNumberOfSingleItems())
      {
        aGearCombo.delete();
      }
      else
      {
        aGearCombo.removeSingleItem(this);
      }
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "]";
  }
}