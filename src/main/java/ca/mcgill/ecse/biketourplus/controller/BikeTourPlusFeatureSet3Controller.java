package ca.mcgill.ecse.biketourplus.controller;

// Import BTP classes and application

import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import ca.mcgill.ecse.biketourplus.model.BookableItem;
import ca.mcgill.ecse.biketourplus.model.BookedItem;
import ca.mcgill.ecse.biketourplus.model.Guide;
import ca.mcgill.ecse.biketourplus.model.Participant;
import ca.mcgill.ecse.biketourplus.model.User;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.Persistence.BikeTourPlusPersistence;

// Import other utilities
import java.util.List;
import java.util.LinkedList;

// completed by YuMeng Liu (liu-yuyu on github)
public class BikeTourPlusFeatureSet3Controller {

  /**
   * Registers a participant in the BikeTourPlus application, given the participant information
   *
   * @param email Email that uniquely identifies the participant
   * @param password Password of participant
   * @param name Name of participant
   * @param emergencyContact Emergency contact of participant
   * @param nrWeeks Number of weeks the participant wishes to go on a tour
   * @param weekAvailableFrom First available week of the participant
   * @param weekAvailableUntil Last available week of the participant
   * @param lodgeRequired Boolean representing whether or not the participant requires a lodge
   * @return String of error message (empty string if no error)
   * @author YuMeng Liu
   */
  public static String registerParticipant(String email, String password, String name,
      String emergencyContact, int nrWeeks, int weekAvailableFrom, int weekAvailableUntil,
      boolean lodgeRequired) {
    var error = "";
    BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

    // Check if valid inputs given the constraints
    if (email.isEmpty()) {
      error = "Email cannot be empty";
    } else if (email.equals("manager@btp.com")) {
      error = "Email cannot be manager@btp.com";
    } else if (User.hasWithEmail(email) && User.getWithEmail(email) instanceof Guide) {
      error = "Email already linked to a guide account";
    } else if (User.hasWithEmail(email) && User.getWithEmail(email) instanceof Participant) {
      error = "Email already linked to a participant account";
    } else if (email.contains(" ")) {
      error = "Email must not contain any spaces";
    } else if (!(email.indexOf("@") > 0 && email.indexOf("@") == email.lastIndexOf("@")
        && email.indexOf("@") < email.lastIndexOf(".") - 1
        && email.lastIndexOf(".") < email.length() - 1)) {
      error = "Invalid email";
    } else if (password.isEmpty()) {
      error = "Password cannot be empty";
    } else if (name.isEmpty()) {
      error = "Name cannot be empty";
    } else if (emergencyContact.isEmpty()) {
      error = "Emergency contact cannot be empty";
    } else if (nrWeeks <= 0) {
      error = "Number of weeks must be greater than zero";
    } else if (nrWeeks > btp.getNrWeeks()) {
      error =
          "Number of weeks must be less than or equal to the number of biking weeks in the biking season";
    } else if (weekAvailableFrom <= 0 || weekAvailableFrom > btp.getNrWeeks()
        || weekAvailableUntil <= 0 || weekAvailableUntil > btp.getNrWeeks()) {
      error = "Available weeks must be within weeks of biking season (1-" + btp.getNrWeeks() + ")";
    } else if (weekAvailableFrom > weekAvailableUntil) {
      error =
          "Week from which one is available must be less than or equal to the week until which one is available";
    } else if (nrWeeks > weekAvailableUntil - weekAvailableFrom + 1) {
      error = "Number of weeks must be less than or equal to the number of available weeks";
    }

    if (!error.equals("")) {
      System.out.println(error);
      return error;
    }

    // If valid inputs, add participant to btp
    btp.addParticipant(email, password, name, emergencyContact, nrWeeks, weekAvailableFrom,
        weekAvailableUntil, lodgeRequired, null, 0);

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    }catch (Exception e){
      error += (e.getMessage());
    }

    // If there is no error, error will be ""
    return error;
  }

  /**
   * Updates information relating to an existing participant (identifiable by their unique email) in
   * the BikeTourPlus application
   *
   * @param email Email that uniquely identifies the participant
   * @param newPassword Updated password of participant
   * @param newName Updated name of participant
   * @param newEmergencyContact Updated emergency contact of participant
   * @param newNrWeeks Updated number of weeks the participant wishes to go on a tour
   * @param newWeekAvailableFrom Updated first available week of the participant
   * @param newWeekAvailableUntil Updated last available week of the participant
   * @param newLodgeRequired Updated boolean representing whether or not the participant requires a
   *        lodge
   * @return String of error message (empty string if no error)
   * @author YuMeng Liu
   */
  public static String updateParticipant(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, int newWeekAvailableFrom,
      int newWeekAvailableUntil, boolean newLodgeRequired) {
    var error = "";
    BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

    // Check if participant exists
    if (!User.hasWithEmail(email) || !(User.getWithEmail(email) instanceof Participant)) {
      error = "The participant account does not exist";
    }
    // Check if valid inputs given the constraints
    else if (newPassword.isEmpty()) {
      error = "Password cannot be empty";
    } else if (newName.isEmpty()) {
      error = "Name cannot be empty";
    } else if (newEmergencyContact.isEmpty()) {
      error = "Emergency contact cannot be empty";
    } else if (newNrWeeks <= 0) {
      error = "Number of weeks must be greater than zero";
    } else if (newNrWeeks > btp.getNrWeeks()) {
      error =
          "Number of weeks must be less than or equal to the number of biking weeks in the biking season";
    } else if (newWeekAvailableFrom <= 0 || newWeekAvailableFrom > btp.getNrWeeks()
        || newWeekAvailableUntil <= 0 || newWeekAvailableUntil > btp.getNrWeeks()) {
      error = "Available weeks must be within weeks of biking season (1-" + btp.getNrWeeks() + ")";
    } else if (newWeekAvailableFrom > newWeekAvailableUntil) {
      error =
          "Week from which one is available must be less than or equal to the week until which one is available";
    } else if (newNrWeeks > newWeekAvailableUntil - newWeekAvailableFrom + 1) {
      error = "Number of weeks must be less than or equal to the number of available weeks";
    }

    if (!error.equals("")) {
      System.out.println(error);
      return error;
    }

    // If valid inputs, update changes
    Participant p = (Participant) Participant.getWithEmail(email);
    p.setPassword(newPassword);
    p.setName(newName);
    p.setEmergencyContact(newEmergencyContact);
    p.setNrWeeks(newNrWeeks);
    p.setWeekAvailableFrom(newWeekAvailableFrom);
    p.setWeekAvailableUntil(newWeekAvailableUntil);
    p.setLodgeRequired(newLodgeRequired);

    // If there is no error, error will be "" 

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    }catch (Exception e){
      error += (e.getMessage());
    }

    return error;
  }

  /**
   * Add an existing gear or combo to an existing participant
   *
   * @param email Email of the participant to whom is added the bookable item by increasing quantity
   *        by one
   * @param bookableItemName Item to be added to participant
   * @return String of error message (empty string if no error)
   * @author YuMeng Liu
   */
  public static String addBookableItemToParticipant(String email, String bookableItemName) {
    var error = "";
    BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

    // Check if participant exists
    if (!User.hasWithEmail(email) || !(User.getWithEmail(email) instanceof Participant)) {
      error = "The participant does not exist";
      System.out.println(error);
      return error;
    }
    // Check if bookable item exists
    if (!BookableItem.hasWithName(bookableItemName)) {
      error = "The piece of gear or combo does not exist";
      System.out.println(error);
      return error;
    }

    // If valid inputs, add bookable item
    Participant p = (Participant) Participant.getWithEmail(email);
    List<BookedItem> listOfItemsAssociatedWithParticipant =
        new LinkedList<BookedItem>(p.getBookedItems());
    List<BookedItem> listOfItemsAssociatedWithBookableItemName =
        new LinkedList<BookedItem>(BookableItem.getWithName(bookableItemName).getBookedItems());
    // Two cases:
    // First is if the bookable item is already in participant's items
    for (BookedItem b : listOfItemsAssociatedWithParticipant) {
      if (listOfItemsAssociatedWithBookableItemName.contains(b)) {
        b.setQuantity(b.getQuantity() + 1); // Increase quantity by one
        return error;
      }
    }
    // Second is if the bookable item is not in participant's items
    BookedItem b = new BookedItem(1, btp, p, BookableItem.getWithName(bookableItemName));
    p.addBookedItem(b);

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    }catch (Exception e){
      error += (e.getMessage());
    }

    return error;
  }

  /**
   * Remove an existing gear or combo from an existing participant
   *
   * @param email Email of the participant from whom is removed the bookable item by decreasing
   *        quantity by one
   * @param bookableItemName Item to be removed from participant
   * @return String of error message (empty string if no error)
   * @author YuMeng Liu
   */
  public static String removeBookableItemFromParticipant(String email, String bookableItemName) {
    var error = "";
    BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

    // Check if participant exists
    if (!User.hasWithEmail(email) || !(User.getWithEmail(email) instanceof Participant)) {
      error = "The participant does not exist";
      System.out.println(error);
      return error;
    }

    // Check if bookable item exists
    if (!BookableItem.hasWithName(bookableItemName)) {
      error = "The piece of gear or combo does not exist";
      System.out.println(error);
      return error;
    }

    // If valid inputs, remove bookable item
    Participant p = (Participant) Participant.getWithEmail(email);
    List<BookedItem> listOfItemsAssociatedWithParticipant =
        new LinkedList<BookedItem>(p.getBookedItems());
    List<BookedItem> listOfItemsAssociatedWithBookableItemName =
        new LinkedList<BookedItem>(BookableItem.getWithName(bookableItemName).getBookedItems());
    // Two cases:
    // First is if the bookable item is already in participant's items, then remove one
    for (BookedItem b : listOfItemsAssociatedWithParticipant) {
      if (listOfItemsAssociatedWithBookableItemName.contains(b)) {
        b.setQuantity(b.getQuantity() - 1); // Decrease quantity by one
        // If quantity has become zero
        if (b.getQuantity() == 0) {
          b.delete();
        }
        return error;
      }
    }
    // Second is if the bookable item is not in participant's items, then do nothing\

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    }catch (Exception e){
      error += (e.getMessage());
    }
    
    return error;

  }

}
