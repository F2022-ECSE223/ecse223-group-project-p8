/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 9 "DomainModel.ump"
public abstract class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String password;

  //User Associations
  private BikeTourPlus bikeTourPlus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aEmail, String aPassword, BikeTourPlus aBikeTourPlus)
  {
    email = aEmail;
    password = aPassword;
    boolean didAddBikeTourPlus = setBikeTourPlus(aBikeTourPlus);
    if (!didAddBikeTourPlus)
    {
      throw new RuntimeException("Unable to create user due to bikeTourPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public BikeTourPlus getBikeTourPlus()
  {
    return bikeTourPlus;
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
      existingBikeTourPlus.removeUser(this);
    }
    bikeTourPlus.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    BikeTourPlus placeholderBikeTourPlus = bikeTourPlus;
    this.bikeTourPlus = null;
    if(placeholderBikeTourPlus != null)
    {
      placeholderBikeTourPlus.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bikeTourPlus = "+(getBikeTourPlus()!=null?Integer.toHexString(System.identityHashCode(getBikeTourPlus())):"null");
  }
}