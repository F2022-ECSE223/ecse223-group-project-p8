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

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.sql.Date;


public class BikeToursStepDefinitions {

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

    for (Map<String, String> row : rows) { // this will only have one iteration as the feature file
                                           // only gives one row
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

    for (Map<String, String> row : rows) { // this will only have one iteration as the feature file
                                           // only gives one row
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

    for (Map<String, String> row : rows) { // this will only have one iteration as the feature file
                                           // only gives one row
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
   * 
   */
  @When("the administrator attempts to initiate the bike tour creation process")
  public void the_administrator_attempts_to_initiate_the_bike_tour_creation_process() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param dataTable
   */
  @Then("the following bike tours shall exist in the system:")
  public void the_following_bike_tours_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   * @param string2
   */
  @Then("the participant with email {string} shall be marked as {string}")
  public void the_participant_with_email_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @Then("the number of bike tours shall be {string}")
  public void the_number_of_bike_tours_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
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

    for (Map<String, String> row : rows) { // this will only have one iteration as the feature file
                                           // only gives one row
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

  /**
   * @param string
   * @author LukeBebee
   */
  @Given("the participant with email {string} is banned")
  public void the_participant_with_email_is_banned(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @Given("the participant with email {string} has started their tour")
  public void the_participant_with_email_has_started_their_tour(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @Given("the participant with email {string} has paid for their tour")
  public void the_participant_with_email_has_paid_for_their_tour(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param dataTable
   */
  @Given("the following bike tours exist in the system:")
  public void the_following_bike_tours_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @Given("the participant with email {string} has finished their tour")
  public void the_participant_with_email_has_finished_their_tour(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @When("the manager attempts to cancel the tour for email {string}")
  public void the_manager_attempts_to_cancel_the_tour_for_email(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @When("the manager attempts to finish the tour for the participant with email {string}")
  public void the_manager_attempts_to_finish_the_tour_for_the_participant_with_email(
      String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @When("the manager attempts to start the tours for week {string}")
  public void the_manager_attempts_to_start_the_tours_for_week(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   * @param string2
   */
  @When("the manager attempts to confirm payment for email {string} using authorization code {string}")
  public void the_manager_attempts_to_confirm_payment_for_email_using_authorization_code(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   * @param string2
   */
  @Then("the participant with {string} shall be marked as {string}")
  public void the_participant_with_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @Then("a participant account shall not exist with email {string}")
  public void a_participant_account_shall_not_exist_with_email(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   */
  @Then("the number of participants shall be {string}")
  public void the_number_of_participants_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }


  /**
   * @param string
   * @param string2
   */
  @Then("a participant account shall exist with email {string} and a refund of {string} percent")
  public void a_participant_account_shall_exist_with_email_and_a_refund_of_percent(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   * @param string2
   */
  @Then("a participant account shall exist with email {string} and authorization code {string}")
  public void a_participant_account_shall_exist_with_email_and_authorization_code(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param string
   * @param string2
   * @author 
   */
  @Then("the participant account with email {string} shall be marked as {string}")
  public void the_participant_account_with_email_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
