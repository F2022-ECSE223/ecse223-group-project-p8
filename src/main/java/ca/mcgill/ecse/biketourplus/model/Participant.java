/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.biketourplus.model;
import java.util.*;

// line 1 "../../../../../ParticipantStateMachine.ump"
// line 43 "../../../../../BikeTourPlus.ump"
public class Participant extends NamedUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Participant Attributes
  private int nrWeeks;
  private int weekAvailableFrom;
  private int weekAvailableUntil;
  private boolean lodgeRequired;
  private String authorizationCode;
  private int refundedPercentageAmount;

  //Participant State Machines
  public enum TourStatus { NotAssigned, Assigned, Paid, Started, Cancelled, Finished, Banned }
  private TourStatus tourStatus;

  //Participant Associations
  private BikeTourPlus bikeTourPlus;
  private BikeTour bikeTour;
  private List<BookedItem> bookedItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Participant(String aEmail, String aPassword, String aName, String aEmergencyContact, int aNrWeeks, int aWeekAvailableFrom, int aWeekAvailableUntil, boolean aLodgeRequired, String aAuthorizationCode, int aRefundedPercentageAmount, BikeTourPlus aBikeTourPlus)
  {
    super(aEmail, aPassword, aName, aEmergencyContact);
    nrWeeks = aNrWeeks;
    weekAvailableFrom = aWeekAvailableFrom;
    weekAvailableUntil = aWeekAvailableUntil;
    lodgeRequired = aLodgeRequired;
    authorizationCode = aAuthorizationCode;
    refundedPercentageAmount = aRefundedPercentageAmount;
    boolean didAddBikeTourPlus = setBikeTourPlus(aBikeTourPlus);
    if (!didAddBikeTourPlus)
    {
      throw new RuntimeException("Unable to create participant due to bikeTourPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bookedItems = new ArrayList<BookedItem>();
    setTourStatus(TourStatus.NotAssigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNrWeeks(int aNrWeeks)
  {
    boolean wasSet = false;
    nrWeeks = aNrWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekAvailableFrom(int aWeekAvailableFrom)
  {
    boolean wasSet = false;
    weekAvailableFrom = aWeekAvailableFrom;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekAvailableUntil(int aWeekAvailableUntil)
  {
    boolean wasSet = false;
    weekAvailableUntil = aWeekAvailableUntil;
    wasSet = true;
    return wasSet;
  }

  public boolean setLodgeRequired(boolean aLodgeRequired)
  {
    boolean wasSet = false;
    lodgeRequired = aLodgeRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthorizationCode(String aAuthorizationCode)
  {
    boolean wasSet = false;
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefundedPercentageAmount(int aRefundedPercentageAmount)
  {
    boolean wasSet = false;
    refundedPercentageAmount = aRefundedPercentageAmount;
    wasSet = true;
    return wasSet;
  }

  public int getNrWeeks()
  {
    return nrWeeks;
  }

  public int getWeekAvailableFrom()
  {
    return weekAvailableFrom;
  }

  public int getWeekAvailableUntil()
  {
    return weekAvailableUntil;
  }

  public boolean getLodgeRequired()
  {
    return lodgeRequired;
  }

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public int getRefundedPercentageAmount()
  {
    return refundedPercentageAmount;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isLodgeRequired()
  {
    return lodgeRequired;
  }

  public String getTourStatusFullName()
  {
    String answer = tourStatus.toString();
    return answer;
  }

  public TourStatus getTourStatus()
  {
    return tourStatus;
  }

  public boolean setParticipantTour(BikeTour aBikeTour)
  {
    boolean wasEventProcessed = false;
    
    TourStatus aTourStatus = tourStatus;
    switch (aTourStatus)
    {
      case NotAssigned:
        // line 6 "../../../../../ParticipantStateMachine.ump"
        doSetBikeTour(aBikeTour);
        setTourStatus(TourStatus.Assigned);
        wasEventProcessed = true;
        break;
      case Assigned:
        // line 19 "../../../../../ParticipantStateMachine.ump"
        rejectSetBikeTour();
        setTourStatus(TourStatus.Assigned);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 29 "../../../../../ParticipantStateMachine.ump"
        rejectSetBikeTour();
        setTourStatus(TourStatus.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        // line 38 "../../../../../ParticipantStateMachine.ump"
        rejectSetBikeTour();
        setTourStatus(TourStatus.Started);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 44 "../../../../../ParticipantStateMachine.ump"
        rejectSetBikeTour();
        setTourStatus(TourStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 52 "../../../../../ParticipantStateMachine.ump"
        rejectSetBikeTour();
        setTourStatus(TourStatus.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 60 "../../../../../ParticipantStateMachine.ump"
        rejectSetBikeTour();
        setTourStatus(TourStatus.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelTripForParticipant()
  {
    boolean wasEventProcessed = false;
    
    TourStatus aTourStatus = tourStatus;
    switch (aTourStatus)
    {
      case NotAssigned:
        // line 7 "../../../../../ParticipantStateMachine.ump"
        doSetRefund(0);
        setTourStatus(TourStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Assigned:
        // line 17 "../../../../../ParticipantStateMachine.ump"
        doSetRefund(0);
        setTourStatus(TourStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 24 "../../../../../ParticipantStateMachine.ump"
        doSetRefund(50);
        setTourStatus(TourStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 35 "../../../../../ParticipantStateMachine.ump"
        doSetRefund(10);
        setTourStatus(TourStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 48 "../../../../../ParticipantStateMachine.ump"
        rejectCancelTripForParticipantFromCancelled();
        setTourStatus(TourStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 56 "../../../../../ParticipantStateMachine.ump"
        rejectCancelTripForParticipantFromFinished();
        setTourStatus(TourStatus.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 64 "../../../../../ParticipantStateMachine.ump"
        rejectCancelTripForParticipantFromBanned();
        setTourStatus(TourStatus.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean payForTrip()
  {
    boolean wasEventProcessed = false;
    
    TourStatus aTourStatus = tourStatus;
    switch (aTourStatus)
    {
      case NotAssigned:
        // line 9 "../../../../../ParticipantStateMachine.ump"
        rejectPayForTripFromNotAssigned();
        setTourStatus(TourStatus.NotAssigned);
        wasEventProcessed = true;
        break;
      case Assigned:
        setTourStatus(TourStatus.Paid);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 27 "../../../../../ParticipantStateMachine.ump"
        rejectPayForTripIfPaid();
        setTourStatus(TourStatus.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        // line 37 "../../../../../ParticipantStateMachine.ump"
        rejectPayForTripIfPaid();
        setTourStatus(TourStatus.Started);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 45 "../../../../../ParticipantStateMachine.ump"
        rejectPayForTripFromCancelled();
        setTourStatus(TourStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 53 "../../../../../ParticipantStateMachine.ump"
        rejectPayForTripIfPaid();
        setTourStatus(TourStatus.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 61 "../../../../../ParticipantStateMachine.ump"
        rejectPayForTripFromBanned();
        setTourStatus(TourStatus.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startTripForParticipant()
  {
    boolean wasEventProcessed = false;
    
    TourStatus aTourStatus = tourStatus;
    switch (aTourStatus)
    {
      case NotAssigned:
        // line 10 "../../../../../ParticipantStateMachine.ump"
        rejectStartTripForParticipantFromNotAssigned();
        setTourStatus(TourStatus.NotAssigned);
        wasEventProcessed = true;
        break;
      case Assigned:
        setTourStatus(TourStatus.Banned);
        wasEventProcessed = true;
        break;
      case Paid:
        setTourStatus(TourStatus.Started);
        wasEventProcessed = true;
        break;
      case Started:
        // line 39 "../../../../../ParticipantStateMachine.ump"
        rejectStartTripForParticipantFromStarted();
        setTourStatus(TourStatus.Started);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 46 "../../../../../ParticipantStateMachine.ump"
        rejectStartTripForParticipantFromCancelled();
        setTourStatus(TourStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 54 "../../../../../ParticipantStateMachine.ump"
        rejectStartTripForParticipantFromFinished();
        setTourStatus(TourStatus.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 62 "../../../../../ParticipantStateMachine.ump"
        rejectStartTripForParticipantFromBanned();
        setTourStatus(TourStatus.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishTripForParticipant()
  {
    boolean wasEventProcessed = false;
    
    TourStatus aTourStatus = tourStatus;
    switch (aTourStatus)
    {
      case NotAssigned:
        // line 11 "../../../../../ParticipantStateMachine.ump"
        rejectFinishTripForParticipantFromNotAssigned();
        setTourStatus(TourStatus.NotAssigned);
        wasEventProcessed = true;
        break;
      case Assigned:
        // line 20 "../../../../../ParticipantStateMachine.ump"
        rejectFinishTripFromAssigned();
        setTourStatus(TourStatus.Assigned);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 28 "../../../../../ParticipantStateMachine.ump"
        rejectFinishTripFromPaid();
        setTourStatus(TourStatus.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        // line 34 "../../../../../ParticipantStateMachine.ump"
        doSetRefund(0);
        setTourStatus(TourStatus.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 47 "../../../../../ParticipantStateMachine.ump"
        rejectFinishTripForParticipantFromCancelled();
        setTourStatus(TourStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 55 "../../../../../ParticipantStateMachine.ump"
        rejectFinishTripForParticipantFromFinished();
        setTourStatus(TourStatus.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 63 "../../../../../ParticipantStateMachine.ump"
        rejectFinishTripForParticipantFromBanned();
        setTourStatus(TourStatus.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setTourStatus(TourStatus aTourStatus)
  {
    tourStatus = aTourStatus;
  }
  /* Code from template association_GetOne */
  public BikeTourPlus getBikeTourPlus()
  {
    return bikeTourPlus;
  }
  /* Code from template association_GetOne */
  public BikeTour getBikeTour()
  {
    return bikeTour;
  }

  public boolean hasBikeTour()
  {
    boolean has = bikeTour != null;
    return has;
  }
  /* Code from template association_GetMany */
  public BookedItem getBookedItem(int index)
  {
    BookedItem aBookedItem = bookedItems.get(index);
    return aBookedItem;
  }

  public List<BookedItem> getBookedItems()
  {
    List<BookedItem> newBookedItems = Collections.unmodifiableList(bookedItems);
    return newBookedItems;
  }

  public int numberOfBookedItems()
  {
    int number = bookedItems.size();
    return number;
  }

  public boolean hasBookedItems()
  {
    boolean has = bookedItems.size() > 0;
    return has;
  }

  public int indexOfBookedItem(BookedItem aBookedItem)
  {
    int index = bookedItems.indexOf(aBookedItem);
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
      existingBikeTourPlus.removeParticipant(this);
    }
    bikeTourPlus.addParticipant(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setBikeTour(BikeTour aBikeTour)
  {
    boolean wasSet = false;
    BikeTour existingBikeTour = bikeTour;
    bikeTour = aBikeTour;
    if (existingBikeTour != null && !existingBikeTour.equals(aBikeTour))
    {
      existingBikeTour.removeParticipant(this);
    }
    if (aBikeTour != null)
    {
      aBikeTour.addParticipant(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookedItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BookedItem addBookedItem(int aQuantity, BikeTourPlus aBikeTourPlus, BookableItem aItem)
  {
    return new BookedItem(aQuantity, aBikeTourPlus, this, aItem);
  }

  public boolean addBookedItem(BookedItem aBookedItem)
  {
    boolean wasAdded = false;
    if (bookedItems.contains(aBookedItem)) { return false; }
    Participant existingParticipant = aBookedItem.getParticipant();
    boolean isNewParticipant = existingParticipant != null && !this.equals(existingParticipant);
    if (isNewParticipant)
    {
      aBookedItem.setParticipant(this);
    }
    else
    {
      bookedItems.add(aBookedItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBookedItem(BookedItem aBookedItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aBookedItem, as it must always have a participant
    if (!this.equals(aBookedItem.getParticipant()))
    {
      bookedItems.remove(aBookedItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookedItemAt(BookedItem aBookedItem, int index)
  {  
    boolean wasAdded = false;
    if(addBookedItem(aBookedItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookedItems()) { index = numberOfBookedItems() - 1; }
      bookedItems.remove(aBookedItem);
      bookedItems.add(index, aBookedItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookedItemAt(BookedItem aBookedItem, int index)
  {
    boolean wasAdded = false;
    if(bookedItems.contains(aBookedItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookedItems()) { index = numberOfBookedItems() - 1; }
      bookedItems.remove(aBookedItem);
      bookedItems.add(index, aBookedItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookedItemAt(aBookedItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    BikeTourPlus placeholderBikeTourPlus = bikeTourPlus;
    this.bikeTourPlus = null;
    if(placeholderBikeTourPlus != null)
    {
      placeholderBikeTourPlus.removeParticipant(this);
    }
    if (bikeTour != null)
    {
      BikeTour placeholderBikeTour = bikeTour;
      this.bikeTour = null;
      placeholderBikeTour.removeParticipant(this);
    }
    for(int i=bookedItems.size(); i > 0; i--)
    {
      BookedItem aBookedItem = bookedItems.get(i - 1);
      aBookedItem.delete();
    }
    super.delete();
  }


  /**
   * actual methods
   */
  // line 70 "../../../../../ParticipantStateMachine.ump"
   private void doSetRefund(int percent){
    setRefundedPercentageAmount(percent);
  }

  // line 73 "../../../../../ParticipantStateMachine.ump"
   private void doSetBikeTour(BikeTour aBikeTour){
    setBikeTour(aBikeTour);
  }


  /**
   * General reject method
   */
  // line 78 "../../../../../ParticipantStateMachine.ump"
   private void rejectSetBikeTour(){
    throw new RuntimeException("Cannot set a tour for a participant who already is assigned to a tour"); 
    // No message specified by feature file
  }

  // line 82 "../../../../../ParticipantStateMachine.ump"
   private void rejectPayForTripIfPaid(){
    throw new RuntimeException("The participant has already paid for their tour");
  }


  /**
   * DONE reject throws for NotAssigned
   */
  // line 88 "../../../../../ParticipantStateMachine.ump"
   private void rejectPayForTripFromNotAssigned(){
    throw new RuntimeException("The participant has not been assigned to their tour");
  }

  // line 91 "../../../../../ParticipantStateMachine.ump"
   private void rejectStartTripForParticipantFromNotAssigned(){
    throw new RuntimeException(""); // no error message for this according to feature file
  }

  // line 94 "../../../../../ParticipantStateMachine.ump"
   private void rejectFinishTripForParticipantFromNotAssigned(){
    throw new RuntimeException("Cannot finish a tour for a participant who has not started their tour");
  }


  /**
   * DONE reject throws for Assigned
   */
  // line 98 "../../../../../ParticipantStateMachine.ump"
   private void rejectFinishTripFromAssigned(){
    throw new RuntimeException("Cannot finish a tour for a participant who has not started their tour");
  }


  /**
   * DONE reject throws for Paid
   */
  // line 102 "../../../../../ParticipantStateMachine.ump"
   private void rejectFinishTripFromPaid(){
    throw new RuntimeException("Cannot finish a tour for a participant who has not started their tour");
  }


  /**
   * DONE reject throws for Started
   */
  // line 106 "../../../../../ParticipantStateMachine.ump"
   private void rejectStartTripForParticipantFromStarted(){
    throw new RuntimeException("Cannot start tour because the participant has already started their tour");
  }


  /**
   * reject throws for "final" states
   * DONE Banned
   */
  // line 113 "../../../../../ParticipantStateMachine.ump"
   private void rejectPayForTripFromBanned(){
    throw new RuntimeException("Cannot pay for tour because the participant is banned");
  }

  // line 116 "../../../../../ParticipantStateMachine.ump"
   private void rejectStartTripForParticipantFromBanned(){
    throw new RuntimeException("Cannot start tour because the participant is banned");
  }

  // line 119 "../../../../../ParticipantStateMachine.ump"
   private void rejectFinishTripForParticipantFromBanned(){
    throw new RuntimeException("Cannot finish tour because the participant is banned");
  }

  // line 122 "../../../../../ParticipantStateMachine.ump"
   private void rejectCancelTripForParticipantFromBanned(){
    throw new RuntimeException("Cannot cancel tour because the participant is banned");
  }


  /**
   * Cancelled
   */
  // line 126 "../../../../../ParticipantStateMachine.ump"
   private void rejectPayForTripFromCancelled(){
    throw new RuntimeException(""); // no message provided by feature file
  }

  // line 129 "../../../../../ParticipantStateMachine.ump"
   private void rejectStartTripForParticipantFromCancelled(){
    throw new RuntimeException("Cannot start tour because the participant has cancelled their tour");
  }

  // line 132 "../../../../../ParticipantStateMachine.ump"
   private void rejectFinishTripForParticipantFromCancelled(){
    throw new RuntimeException(""); // no message provided by feature file
  }

  // line 135 "../../../../../ParticipantStateMachine.ump"
   private void rejectCancelTripForParticipantFromCancelled(){
    throw new RuntimeException(""); // no message provided by feature file
  }


  /**
   * Finished
   */
  // line 139 "../../../../../ParticipantStateMachine.ump"
   private void rejectStartTripForParticipantFromFinished(){
    throw new RuntimeException("Cannot start tour because the participant has finished their tour");
  }

  // line 142 "../../../../../ParticipantStateMachine.ump"
   private void rejectFinishTripForParticipantFromFinished(){
    throw new RuntimeException("Cannot finish tour because the participant has already finished their tour");
  }

  // line 145 "../../../../../ParticipantStateMachine.ump"
   private void rejectCancelTripForParticipantFromFinished(){
    throw new RuntimeException("Cannot cancel tour because the participant has finished their tour");
  }


  public String toString()
  {
    return super.toString() + "["+
            "nrWeeks" + ":" + getNrWeeks()+ "," +
            "weekAvailableFrom" + ":" + getWeekAvailableFrom()+ "," +
            "weekAvailableUntil" + ":" + getWeekAvailableUntil()+ "," +
            "lodgeRequired" + ":" + getLodgeRequired()+ "," +
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "refundedPercentageAmount" + ":" + getRefundedPercentageAmount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bikeTourPlus = "+(getBikeTourPlus()!=null?Integer.toHexString(System.identityHashCode(getBikeTourPlus())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bikeTour = "+(getBikeTour()!=null?Integer.toHexString(System.identityHashCode(getBikeTour())):"null");
  }
}