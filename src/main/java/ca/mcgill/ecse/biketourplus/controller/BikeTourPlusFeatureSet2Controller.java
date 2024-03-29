package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import ca.mcgill.ecse.biketourplus.model.Participant;
import ca.mcgill.ecse.biketourplus.model.User;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.Persistence.BikeTourPlusPersistence;

import java.sql.Date;

// completed by Lukas Bebee (LukeBebee on github)
public class BikeTourPlusFeatureSet2Controller {

  /**
   * Updates the existing BikeTourPlus instance with new attributes
   * 
   * @param startDate start date of the biking season
   * @param nrWeeks number of weeks the biking season will last
   * @param priceOfGuidePerWeek the weekly cost of a guide
   * @return String of error message (empty string if no error)
   * @author LukeBebee
   */
  public static String updateBikeTourPlus(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
    var error = "";

    // check to ensure the three inputs are possible with given constraints
    if (nrWeeks < 0) { // check number of weeks
      error = "The number of riding weeks must be greater than or equal to zero";
      return error;
    }
    if (priceOfGuidePerWeek < 0) { // check guide price
      error = "The price of guide per week must be greater than or equal to zero";
      return error;
    }
    // get current date
    long millis = System.currentTimeMillis();
    java.sql.Date curDate = new java.sql.Date(millis);

    if (curDate.after(startDate)) { // check start date against current date
      error = "The start date cannot be from previous year or earlier";
      return error;
    }

    // If valid (no error from inputs), apply changes
    try {
      BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();
      btp.setStartDate(startDate);
      btp.setNrWeeks(nrWeeks);
      btp.setPriceOfGuidePerWeek(priceOfGuidePerWeek);

      try {
        BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
        }catch (Exception e){
        error += e.getMessage();
        }
        try {
          BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
        }catch (Exception e){
          error += e.getMessage();
        }

    } catch (RuntimeException e) {
      error = e.getMessage();
    }
    return error;
  }

  /**
   * This method removes a participant from BikeTourPlus
   * 
   * @param email String of email of participant to be removed
   * @author LukeBebee
   * @throws InvalidInputException
   */
  public static String deleteParticipant(String email) throws InvalidInputException {
    String error = "";
    try {
      User u = User.getWithEmail(email);
      if (u instanceof Participant) { // Ensure user is a participant
        u.delete(); // Delete instance of participant
        // Our model ensures that any necessary associations are removed
      }
      else {
        error += "Participant does not exist";
      }
    } catch (RuntimeException e) {
      error += e.getMessage(); // Display any error that arises
    }
    
    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    }catch (Exception e){
      throw new InvalidInputException(e.getMessage());
    }
    return error;
  }

  /**
   * This method is incomplete as we are only a team of five members
   * 
   * @param name
   */
  public static void deleteCombo(String name) {
    // This method is incomplete as we are a team of only five members
  }
}
