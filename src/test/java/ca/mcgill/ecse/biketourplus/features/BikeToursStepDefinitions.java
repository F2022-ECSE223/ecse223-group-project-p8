package ca.mcgill.ecse.biketourplus.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import ca.mcgill.ecse.biketourplus.model.BookableItem;
import ca.mcgill.ecse.biketourplus.model.Combo;
import ca.mcgill.ecse.biketourplus.model.Gear;
import ca.mcgill.ecse.biketourplus.model.Guide;
import ca.mcgill.ecse.biketourplus.model.Participant;
import ca.mcgill.ecse.biketourplus.model.User;
import ca.mcgill.ecse.biketourplus.model.BikeTour;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.controller.BikeToursFeatureSetController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.sql.Date;


public class BikeToursStepDefinitions { //TODO make sure that new states are matched

  private String error;
  private BikeTourPlus btp;

  /**
   * This function sets up an instance of BikeTourPlus with values specified in the feature file
   * Originally written in and copied from UpdateBikeTourPlusStepDefinitions.java
   * @param dataTable the data table from the feature file
   * @author LukeBebee
   */
  @Given("the following BikeTourPlus system exists:")
  public void the_following_bike_tour_plus_system_exists(
      io.cucumber.datatable.DataTable dataTable) {
    // clear errors
    error = "";

    // create instance of BikeTourPlus
    btp = BikeTourPlusApplication.getBikeTourPlus();

    // initialize variables that will be used for attributes (null s.t. an error is thrown if the
    // feature file is not giving values)
    String startDateString = null;
    String nrWeeksString = null;
    String priceOfGuidePerWeekString = null;

    // making a list of maps from the data table from the feature file
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

    // this will only have one iteration as the feature file only gives one row
    for (Map<String, String> row : rows) { 
      startDateString = row.get("startDate");
      nrWeeksString = row.get("nrWeeks");
      priceOfGuidePerWeekString = row.get("priceOfGuidePerWeek");
    }

    // set attributes
    // startDate
    Date startDate = Date.valueOf(startDateString);
    btp.setStartDate(startDate);

    // nrWeeks
    int nrWeeks = Integer.parseInt(nrWeeksString);
    btp.setNrWeeks(nrWeeks);

    // priceOfGuidePerWeek
    int priceOfGuidePerWeek = Integer.parseInt(priceOfGuidePerWeekString);
    btp.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
  }

  /**
   * This function sets up instances of Guide with values specified in the feature file
   * @param dataTable
   * @author LukeBebee
   */
  @Given("the following guides exist in the system:")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    // initialize variables that will be used for attributes
    String email = null;
    String password = null;
    String name = null;
    String emergencyContact = null;

    // making a list of maps from the data table from the feature file
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

    for (Map<String, String> row : rows) { 
      email = row.get("email");
      password = row.get("password");
      name = row.get("name");
      emergencyContact = row.get("emergencyContact");
      btp.addGuide(email, password, name, emergencyContact);
    }
  }

  /**
   * This function sets up instances of Participant with values specified in the feature file
   * @param dataTable
   * @author LukeBebee
   */
  @Given("the following participants exist in the system:")
  public void the_following_participants_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // initialize variables that will be used for attributes
    String email = null;
    String password = null;
    String name = null;
    String emergencyContact = null;
    String lodgeRequiredString = null;

    // making a list of maps from the data table from the feature file
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

    for (Map<String, String> row : rows) {
      email = row.get("email");
      password = row.get("password");
      name = row.get("name");
      emergencyContact = row.get("emergencyContact");
      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
      int weeksAvailableFrom = Integer.parseInt(row.get("weeksAvailableFrom"));
      int weeksAvailableUntil = Integer.parseInt(row.get("weeksAvailableUntil"));
      boolean lodgeRequired = Boolean.parseBoolean(lodgeRequiredString);
      btp.addParticipant(email, password, name, emergencyContact, nrWeeks, weeksAvailableFrom, weeksAvailableUntil, lodgeRequired, "", 0);
    }
  }

  /**
   * This method calls the controller to initiate the bike tour creation proccess
   * @author LukeBebee
   */
  @When("the administrator attempts to initiate the bike tour creation process")
  public void the_administrator_attempts_to_initiate_the_bike_tour_creation_process() {
    error = BikeToursFeatureSetController.initiateBikeTourCreationProcess();
  }

  // TODO this method currently relies on biketours being in order of id, which may not be true
  /**
   * This method checks if bike tours exist in the system as they should
   * @param dataTable
   * @author LukeBebee
   */
  @Then("the following bike tours shall exist in the system:")
  public void the_following_bike_tours_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // making a list of maps from the data table from the feature file
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

    //initialize variables
    int id;
    int startWeek;
    int endWeek;
    Guide guide;

    for (Map<String, String> row : rows) { 
      // get variables for expected tours
      id = Integer.parseInt(row.get("id"));
      startWeek = Integer.parseInt(row.get("startWeek"));
      endWeek = Integer.parseInt(row.get("endWeek"));
      String guideString = row.get("guide");
      guide = (Guide) Guide.getWithEmail(guideString);
      String participantListString = row.get("participants");
      List<String> participantList = new ArrayList<String>(Arrays.asList(participantListString.split(",")));

      // get actual tour for the right id (assuming in order of ids)
      BikeTour actualTour = btp.getBikeTour(id - 1);

      // check if they match
      assertEquals(id, actualTour.getId());
      assertEquals(startWeek, actualTour.getStartWeek());
      assertEquals(endWeek, actualTour.getEndWeek());
      assertEquals(guide, actualTour.getGuide());
      assertEquals(participantList, actualTour.getParticipants());
    }
  }

  /**
   * checks if the participant is properly marked
   * @param string
   * @param string2
   * @author LukeBebee
   */
  @Then("the participant with email {string} shall be marked as {string}")
  public void the_participant_with_email_shall_be_marked_as(String string, String string2) {
    User u = Participant.getWithEmail(string);
    if (u instanceof Participant) {
      Participant p = (Participant) u;
      assertEquals(string2, p.getTourStatusFullName());
    }

  }

  /**
   * checks the number of bike tours
   * @param string
   * @author LukeBebee
   */
  @Then("the number of bike tours shall be {string}")
  public void the_number_of_bike_tours_shall_be(String string) {
    assertEquals(Integer.parseInt(string), btp.getBikeTours().size());
  }

  /**
   * checks the error raised by the controller
   * @param string
   * @author LukeBebee
   */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    assertEquals(string, error);
  }

  /**
   * This function sets up instances of Gear with values specified in the feature file
   * @param dataTable
   * @author LukeBebee
   */
  @Given("the following pieces of gear exist in the system:")
  public void the_following_pieces_of_gear_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // initialize variables that will be used for attributes
    String name = null;
    int pricePerWeek = 0;

    // making a list of maps from the data table from the feature file
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

    for (Map<String, String> row : rows) {
      name = row.get("name");
      pricePerWeek = Integer.parseInt(row.get("pricePerWeek"));
      btp.addGear(name, pricePerWeek);
    }
  }

  /**
   * This function sets cancells the Tour of the inputted participant for testing
   * @param string
   * @author LukeBebee
   */
  @Given("the participant with email {string} has cancelled their tour")
  public void the_participant_with_email_has_cancelled_their_tour(String string) {
    User u = Participant.getWithEmail(string);
    if (u instanceof Participant) {
      Participant p = (Participant) u;
      BikeTour tour = p.getBikeTour();
      tour.removeParticipant(p); // remove participant from bike tour
    }
  }


  /**
   * @author Gabrielle Lavoie
   * @param dataTable This method parses the given dataTable to create combos associated with
   *        multiple gear items and quantities
   */
  @Given("the following combos exist in the system:")
  public void the_following_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    // loop through each row of data table
    for (var row : rows) {
      // create a combo with the specified name and discount
      String name = row.get("name");
      int discount = Integer.parseInt(row.get("discount"));
      Combo combo = btp.addCombo(name, discount);

      // get items and quantities strings
      String item_names_string = row.get("items");
      String item_quantities_string = row.get("quantity");

      // split the comma-separated strings into list of strings
      List<String> item_names_list = Arrays.asList(item_names_string.split(","));
      List<String> item_quantities_list = Arrays.asList(item_quantities_string.split(","));

      for (int i = 0; i < (item_names_list).size(); i++) {
        // get existing gear object with name in data table
        Gear gear = (Gear) BookableItem.getWithName(item_names_list.get(i));

        // create a combo item, which links the combo with its gear items and specifies
        // quantities
        gear.addComboItem(Integer.valueOf(item_quantities_list.get(i)), btp, combo);
      }
    }
  }

    /**
     * @author Liu, ZiXu
     *
     * @Description: the code to set up all the gears the participants want
     * 
     * Edits: Changed variable names
     *
     * @param dataTable the table generated by cucumber
     */
  @Given("the following participants request the following pieces of gear:")
  public void the_following_participants_request_the_following_pieces_of_gear(
      io.cucumber.datatable.DataTable dataTable) {
    
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row: rows) { // Iterating through each row
          String email = row.get("email"); // Getting the email of the participant
          String gearName = row.get("gear"); // Getting the name of the gear
          int quantity = Integer.parseInt(row.get("quantity")); // Getting the amount of gear a participant want
          for (Participant p: btp.getParticipants()) { // Iterate through all the participants in bikeTourPlus
            if (p.getEmail().equals(email)) { // If the emails match...
              for (Gear g: btp.getGear()) { // Iterate through all the gears in bikeTourPlus
                if (g.getName().equals(gearName)) { // If the names match, create a BookedItem for the participant
                  p.addBookedItem(quantity, btp, g);
                  break;
                  }
                }
              break;
              }
            }
          }
  }

    /**
     * @author Liu, ZiXu
     *
     * @Description: the code to set up all the combos each participant want
     * 
     * Edits: Changed variable names
     *
     * @param dataTable the table generated by cucumber
     */
  @Given("the following participants request the following combos:")
  public void the_following_participants_request_the_following_combos(
      io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row: rows) { // Iterating through each row
          String email = row.get("email"); // Getting the email of the participant
          String gearName = row.get("gear"); // Getting the name of the combo
          int quantity = Integer.parseInt(row.get("quantity")); // Getting the amount of combo a participant want
          for (Participant p: btp.getParticipants()) { // Iterate through all the participants in bikeTourPlus
            if (p.getEmail().equals(email)) { // If the emails match...
              for (Combo c: btp.getCombos()) { // Iterate through all the combos in bikeTourPlus
                if (c.getName().equals(gearName)) { // If the names match, create a BookedItem for the participant
                  p.addBookedItem(quantity, btp, c);
                  break;
                  }
                }
              break;
              }
            }
          }
  }

  // TODO Check if this method works, it seems sketchy
  /**
   * Sets up the participant as banned
   * @param string
   * @author LukeBebee
   * @author 
   */
  @Given("the participant with email {string} is banned")
  public void the_participant_with_email_is_banned(String string) {
    User u = Participant.getWithEmail(string);
    if (u instanceof Participant) {
      Participant p = (Participant) u;
      if (p.getTourStatusFullName().equals("NotAssigned")) {
        p.setParticipantTour(null);
      }
      if (p.getTourStatusFullName().equals("AssignedUnpaid")) {
        p.startTripForParticipant();
      }
    }
  }

  // TODO any changes to the above method may apply to this method as well
  /**
   * Sets up a participant as having started their tour
   * @param string
   * @author LukeBebee
   */
  @Given("the participant with email {string} has started their tour")
  public void the_participant_with_email_has_started_their_tour(String string) {
    User u = Participant.getWithEmail(string);
    if (u instanceof Participant) {
      Participant p = (Participant) u;
      if (p.getTourStatusFullName().equals("NotAssigned")) {
        p.setParticipantTour(null);
      }
      if (p.getTourStatusFullName().equals("AssignedUnpaid")) {
        p.payForTrip();
      }
      if (p.getTourStatusFullName().equals("Paid")) {
        p.startTripForParticipant();
      }
    }
  }

  // TODO any changes to the above method may apply to this method as well
  /**
   * Sets up a participant as having paid for their tour
   * @param string
   * @author LukeBebee
   */
  @Given("the participant with email {string} has paid for their tour")
  public void the_participant_with_email_has_paid_for_their_tour(String string) {
    User u = Participant.getWithEmail(string);
    if (u instanceof Participant) {
      Participant p = (Participant) u;
      if (p.getTourStatusFullName().equals("NotAssigned")) {
        p.setParticipantTour(null);
      }
      if (p.getTourStatusFullName().equals("AssignedUnpaid")) {
        p.payForTrip();
      }
    }
  }

  /**
   * This method creates instances of BikeTour in the system
   * @param dataTable
   * @author LukeBebee
   */
  @Given("the following bike tours exist in the system:")
  public void the_following_bike_tours_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // making a list of maps from the data table from the feature file
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    int id;
    int startWeek;
    int endWeek;
    Guide guide;
    for (Map<String, String> row : rows) { 
      id = Integer.parseInt(row.get("id"));
      startWeek = Integer.parseInt(row.get("startWeek"));
      endWeek = Integer.parseInt(row.get("endWeek"));
      String guideString = row.get("guide");
      guide = (Guide) Guide.getWithEmail(guideString);
      String participantListString = row.get("participants");
      List<String> participantList = new ArrayList<String>(Arrays.asList(participantListString.split(",")));

      BikeTour tour =  btp.addBikeTour(id, startWeek, endWeek, guide);
      for (String pString : participantList) {
        Participant p = (Participant) Participant.getWithEmail(pString);
        tour.addParticipant(p);
      }
    }
  }

  // TODO similar methods above may change, in which case this should as well
  /**
   * This method sets up the participant as having finished their tour
   * @param string
   * @author LukeBebee
   */
  @Given("the participant with email {string} has finished their tour")
  public void the_participant_with_email_has_finished_their_tour(String string) {
    User u = Participant.getWithEmail(string);
    if (u instanceof Participant) {
      Participant p = (Participant) u;
      if (p.getTourStatusFullName().equals("NotAssigned")) {
        p.setParticipantTour(null);
      }
      if (p.getTourStatusFullName().equals("AssignedUnpaid")) {
        p.payForTrip();
      }
      if (p.getTourStatusFullName().equals("Paid")) {
        p.startTripForParticipant();
      }
      if (p.getTourStatusFullName().equals("OnTrip")) {
        p.finishTripForParticipant();
      }
    }
  }

  /**
   * The manager cancels the tour for a participant using the controller
   * @param string
   * @author LukeBebee
   */
  @When("the manager attempts to cancel the tour for email {string}")
  public void the_manager_attempts_to_cancel_the_tour_for_email(String string) {
    error = BikeToursFeatureSetController.cancelParticipantTrip(string);
  }

  /**
   * The manager finishes the tour for a participant using the controller
   * @param string
   * @author LukeBebee
   */
  @When("the manager attempts to finish the tour for the participant with email {string}")
  public void the_manager_attempts_to_finish_the_tour_for_the_participant_with_email(
      String string) {
        error = BikeToursFeatureSetController.finishParticipantTrip(string);
  }

  /**
   * This method calls the controller to start the tours for the week
   * @param string
   * @author LukeBebee
   */
  @When("the manager attempts to start the tours for week {string}")
  public void the_manager_attempts_to_start_the_tours_for_week(String string) {
    error = BikeToursFeatureSetController.startWeekTrips(Integer.parseInt(string));
  }

  /**
   * This method attempts to confirm payment with a controller method
   * @param string
   * @param string2
   * @author LukeBebee
   */
  @When("the manager attempts to confirm payment for email {string} using authorization code {string}")
  public void the_manager_attempts_to_confirm_payment_for_email_using_authorization_code(
      String string, String string2) {
        error = BikeToursFeatureSetController.payForParticipantTrip(string, string2);
  }

  /**
   * Checks to ensure the participant does not exist
   * @param string
   * @author LukeBebee
   */
  @Then("a participant account shall not exist with email {string}")
  public void a_participant_account_shall_not_exist_with_email(String string) {
    for (Participant p : btp.getParticipants()) { // check each participant
      assertNotEquals(string, p.getEmail());
    }
  }

  /**
   * This method checks the number of participants
   * @param string
   * @author LukeBebee
   */
  @Then("the number of participants shall be {string}")
  public void the_number_of_participants_shall_be(String string) {
    assertEquals(Integer.parseInt(string), btp.getParticipants().size());
  }

  //TODO check participant existance
  /**
   * This checks a participant's existance and refund percent
   * @param string
   * @param string2
   * @author LukeBebee
   */
  @Then("a participant account shall exist with email {string} and a refund of {string} percent")
  public void a_participant_account_shall_exist_with_email_and_a_refund_of_percent(String string,
      String string2) {
        User u = Participant.getWithEmail(string);
        Participant p = (Participant) u;

        // verify existance
        boolean exists = false;
        for (Participant participant : btp.getParticipants()) {
          exists = string.equals(participant.getEmail());
          if (exists) {break;}
        }
        assertTrue(exists);
        // verify percentage amount
        assertEquals(Integer.parseInt(string2), p.getRefundedPercentageAmount());
  }

  // TODO still must check for participant existance
  /**
   * Check if such a participant exists
   * @param string
   * @param string2
   * @author LukeBebee
   */
  @Then("a participant account shall exist with email {string} and authorization code {string}")
  public void a_participant_account_shall_exist_with_email_and_authorization_code(String string,
      String string2) {
    User u = Participant.getWithEmail(string);
    Participant p = (Participant) u;
    // verify existance
    assertEquals(string2, p.getAuthorizationCode());
  }

}
