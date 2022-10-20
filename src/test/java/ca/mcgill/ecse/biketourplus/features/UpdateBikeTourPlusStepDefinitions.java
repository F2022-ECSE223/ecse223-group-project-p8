package ca.mcgill.ecse.biketourplus.features;

import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.controller.BikeTourPlusFeatureSet2Controller;
import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class UpdateBikeTourPlusStepDefinitions {

private String error;
private BikeTourPlus BTP;


    /**
     * This function sets up an instance of BikeTourPlus with values specified in the feature file
     * 
     * @param dataTable the data table from the feature file
     * @author LukeBebee
     */
    @Given("the following BikeTourPlus system exists: \\(p8)")
    public void the_following_bike_tour_plus_system_exists_p8(io.cucumber.datatable.DataTable dataTable) {

        /* Current situation:
         * Done?
         * Potentially change from get to constructor (see lines 34 and 51 but I don't think it should be) and maybe delete previously existing btp
         */

        // clear errors and clear btp?
        error = "";
        

        // create instance of BikeTourPlus
        BTP = BikeTourPlusApplication.getBikeTourPlus(); 
        // or should it be the following after we get the attribute values from data table: (see line 51 $$)
        //btp = BikeTourPlus(atartDate, nrWeeks, priceOfGuidePerWeek);

        // initialize variables that will be used for attributes (null s.t. an error is thrown if the feature file is not giving values)
        String startDateString = null;
        String nrWeeksString = null;
        String priceOfGuidePerWeekString = null;

        // making a list of maps from the data table from the feature file
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {          // this will only have one iteration as the feature file only gives one row
            startDateString = row.get("startDate");
            nrWeeksString = row.get("nrWeeks");
            priceOfGuidePerWeekString = row.get("priceOfGuidePerWeek");
        }
        
        // set attributes (can do with constructor, see line 33 $$))
            // startDate
        Date startDate=Date.valueOf(startDateString);
        BTP.setStartDate(startDate);

            // nrWeeks
        int nrWeeks = Integer.parseInt(nrWeeksString);
        BTP.setNrWeeks(nrWeeks);

            // priceOfGuidePerWeek
        int priceOfGuidePerWeek = Integer.parseInt(priceOfGuidePerWeekString);
        BTP.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
    }

    /**
     * This function updates the instance of BikeTourPlus with variables from the feature file
     * 
     * @param string string from data table in feature file (Start date)
     * @param string2 string from data table in feature file (Number of weeks)
     * @param string3 string from data table in feature file (Price of guide per week)
     * @author LukeBebee
     */
    @When("the manager attempts to update the BikeTourPlus information to start date {string}, number of weeks {string}, and price of guide per week {string} \\(p8)")
    public void the_manager_attempts_to_update_the_bike_tour_plus_information_to_start_date_number_of_weeks_and_price_of_guide_per_week_p8(
        String string, String string2, String string3) {
        /* Current situation:
         * Done
         */

        // strings into proper types to call controller
        Date startDate=Date.valueOf(string);
        int nrWeeks = Integer.parseInt(string2);
        int price = Integer.parseInt(string3);

        // call controller 
        // (this is called on controller, not instance, but I am not sure how it knows that there is only one instance of BikeTourPlus, maybe ask TA)
        BikeTourPlusFeatureSet2Controller.updateBikeTourPlus(startDate, nrWeeks, price); 
    }

    /**
     * This funciton checks whether the updated variables of the BikeTourPlus instance are correct
     * 
     * @param string string from data table in feature file (Start date)
     * @param string2 string from data table in feature file (Number of weeks)
     * @param string3 string from data table in feature file (Price of guide per week)
     * @author LukeBebee
     * @author 
     */
    @Then("the BikeTourPlus information shall be start date {string}, number of weeks {string}, and price of guide per week {string} \\(p8)")
    public void the_bike_tour_plus_information_shall_be_start_date_number_of_weeks_and_price_of_guide_per_week_p8(
        String string, String string2, String string3) {

        /* Current situation:
         * Simple?
         * ensure we should use getters
         * assertEqual() stuff
         * append error messages with messages from feature files??
         */ 
        
        // get attributes of instance
        Date instanceStartDate = BTP.getStartDate();
        int instanceNrWeeks = BTP.getNrWeeks();
        int instancePrice = BTP.getPriceOfGuidePerWeek();
        
        // get desired attributes
        Date startDate = Date.valueOf(string);
        int nrWeeks = Integer.parseInt(string2);
        int price = Integer.parseInt(string3);

        // assert equalities and check for errors

        // assertEqual()
        // error += (insert error here)

    }

    /**
     * I DON'T KNOW WHAT THIS DOES TBH, maybe raises an error?
     * 
     * @param string I ALSO DON'T KNOW THE INPUT
     * @author 
     */
    @Then("the system shall raise the error {string} \\(p8)")
    public void the_system_shall_raise_the_error_p8(String string) {
        // Write code here that turns the phrase above into concrete actions

        /* Current Situation:
         * 
         * If error string is not "" we raise an exception
         * 
         * Check how we raise exception and if we need to return a specific error message
         */


    
    }
}
