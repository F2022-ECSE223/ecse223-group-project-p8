package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.application.*;
import ca.mcgill.ecse.biketourplus.model.*;
import java.util.regex.*;
import ca.mcgill.ecse.biketourplus.Persistence.BikeTourPlusPersistence;


public class BikeTourPlusFeatureSet4Controller {

  // get instance of the BikeTourPlus from the Application
  static BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

  /**
   * Method that registers a Guide into the BikeTourPlus system. Checks if input is valid before
   * calling the constructor in Guide.
   * 
   * @param email A string that represents the guide's e-mail address
   * @param password A string that represents the guide's password
   * @param name A string that represents the guide's name
   * @param emergencyContact A string that represents the guide's emergency contact
   * @return A string error that describes the input error. Blank if no error has been detected
   * @author Ralph Choucha (RalphChoucha on GitHub)
   */

  public static String registerGuide(String email, String password, String name,
      String emergencyContact) {

    // initialize blank error string
    var error = "";

    // check for blank email
    if (email.isBlank()) {
      error = "Email cannot be empty";
      return error;
    }

    // check if email already registered as guide or participant
    // check guides
    for (Guide e : btp.getGuides()) {
      if (e.getEmail().equals(email)) {
        error = "Email already linked to a guide account";
        return error;
      }
    }

    // check participants
    for (Participant p : btp.getParticipants()) {
      if (p.getEmail().equals(email)) {
        error = "Email already linked to a participant account";
        return error;
      }
    }

    // check if email isn't "manager@btp.com"
    if (email.contentEquals("manager@btp.com")) {
      error = "Email cannot be manager@btp.com";
      return error;
    }

    // check if email has spaces
    if (email.contains(" ")) {
      error = "Email must not contain any spaces";
      return error;
    }

    // check if email is valid
    if (!isEmailValid(email)) {
      error = "Invalid email";
      return error;
    }

    // check for blank password
    if (password.isBlank()) {
      error = "Password cannot be empty";
      return error;
    }

    // check for blank name
    if (name.isBlank()) {
      error = "Name cannot be empty";
      return error;
    }

    // check for blank emergencyContact
    if (emergencyContact.isBlank()) {
      error = "Emergency contact cannot be empty";
      return error;
    }

    // call constructor if error is a blank string to create new guide with the parameters specified
    // in the input
    if (error.isBlank()) {
      new Guide(email, password, name, emergencyContact, btp);
    }

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      error += (e.getMessage());
    }

    return error;
  }

  /**
   * Method that updates a specific Guide using their email in the BikeTourPlus system. The Guide is
   * only updated if inputs are valid.
   * 
   * @param email A string that represents the specific guide's email
   * @param newPassword A string that represents the new password to be updated
   * @param newName A string that represents the new name to be updated
   * @param newEmergencyContact A string that represents the new emergency contact to be updated
   * @return A string error that describes the input error. Blank if no error has been detected
   * @author Ralph Choucha (RalphChoucha on GitHub)
   */
  public static String updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) {

    // initialize empty error string
    var error = "";

    // get Guide object from the btp instance
    // see helper method getSpecificGuide()
    Guide guideToUpdate = getSpecificGuide(email);

    // if guideToUpdate is null, no guide is registered with the input email
    if (guideToUpdate == null) {
      error += "The guide account does not exist";
    }

    // check if input password is blank
    if (newPassword.isBlank()) {
      error += "Password cannot be empty";
    }

    // check if name input is blank
    if (newName.isBlank()) {
      error += "Name cannot be empty";
    }

    // check if emergency contact is blank
    if (newEmergencyContact.isBlank()) {
      error += "Emergency contact cannot be empty";
    }

    // if the error string is still blank after input validation checks, update the guide
    if (error.isEmpty()) {
      guideToUpdate.setPassword(newPassword);
      guideToUpdate.setName(newName);
      guideToUpdate.setEmergencyContact(newEmergencyContact);
    }

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      error += (e.getMessage());
    }

    return error;
  }

  /**
   * A method that is used to delete a specific Guide object using an email. Deletes the instance as
   * well as all associations with it.
   * 
   * @param email A string that represents the Guide to be deleted's email
   * @author Ralph Choucha (RalphChoucha on GitHub)
   * @throws InvalidInputException
   * @return A string error. Blank if the Guide object exists.
   */
  public static String deleteGuide(String email) throws InvalidInputException {

    var error = "";
    // get specific guide instance
    Guide guideToDelete = getSpecificGuide(email);

    // if guide with input email exists, we remove them along with any bikeTour associations.
    // Otherwise do nothing
    if (guideToDelete != null) {
      btp.removeGuide(guideToDelete);
      guideToDelete.delete();
    } else {
      error += "The specified guide does not exist";
    }

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      throw new InvalidInputException(e.getMessage());
    }

    return error;
  }

  /**
   * Helper method to find a specific Guide object by email
   * 
   * @param email = A string that represents the specific guide's email
   * @return The specific Guide object. If the method doesn't find a match in the Guide list returns
   *         a null Guide object
   * @author Ralph Choucha (RalphChoucha on GitHub)
   */

  private static Guide getSpecificGuide(String email) {
    Guide guide = null;
    for (Guide g : btp.getGuides()) {
      if (g.getEmail().equals(email)) {
        guide = g;
      }
    }
    return guide;
  }

  /**
   * Method that uses a Regex to check if email matches a valid pattern
   * 
   * @param email The email string to be checked
   * @return True if email is valid and False if it isn't
   * 
   *         This helper method has been taken from
   *         https://www.geeksforgeeks.org/check-email-address-valid-not-java/
   */
  private static boolean isEmailValid(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
        + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

    Pattern pattern = Pattern.compile(emailRegex);
    if (email == null) {
      return false;
    }
    return pattern.matcher(email).matches();
  }

}
