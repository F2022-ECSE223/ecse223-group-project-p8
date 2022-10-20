package ca.mcgill.ecse.biketourplus.features;

import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class UpdateBikeTourPlusStepDefinitions {
    @Given("the following BikeTourPlus system exists: \\(p8)")
    public void the_following_bike_tour_plus_system_exists_p8(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        
        // For other transformations you can register a DataTableType.

        // create btp
        BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        // if (rows.size() != 1) {;} potentially want to ensure 1 row for this?

        // is initializing these to null bad???
        String startDateString = "";
        String nrWeeksString = "";
        String priceOfGuidePerWeekString = "";


        for (Map<String, String> row : rows) {
            startDateString = row.get("startDate");
            nrWeeksString = row.get("nrWeeks");
            priceOfGuidePerWeekString = row.get("priceOfGuidePerWeek");
        }
        
        // set attributes
            // startDate
        Date startDate=Date.valueOf(startDateString);
        btp.setStartDate(startDate);

            // nrWeeks
        int nrWeeks = Integer.parseInt(nrWeeksString);
        btp.setNrWeeks(nrWeeks);

            // priceOfGuidePerWeek
        int priceOfGuidePerWeek = Integer.parseInt(priceOfGuidePerWeekString);
        btp.setPriceOfGuidePerWeek(priceOfGuidePerWeek);

    }

    @When("the manager attempts to update the BikeTourPlus information to start date {string}, number of weeks {string}, and price of guide per week {string} \\(p8)")
    public void the_manager_attempts_to_update_the_bike_tour_plus_information_to_start_date_number_of_weeks_and_price_of_guide_per_week_p8(
            String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions

        /* Pseudocode
         * 
         * 
         * 
         */

        //     // startDate
        // Date startDate=Date.valueOf(string);
        //     // nrWeeks
        // int nrWeeks = Integer.parseInt(string2);
        //     // priceOfGuidePerWeek
        // int price = Integer.parseInt(string3);


        throw new io.cucumber.java.PendingException();
    }

    @Then("the BikeTourPlus information shall be start date {string}, number of weeks {string}, and price of guide per week {string} \\(p8)")
    public void the_bike_tour_plus_information_shall_be_start_date_number_of_weeks_and_price_of_guide_per_week_p8(
            String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        

        //     // startDate
        // Date startDate=Date.valueOf(string);
        //     // nrWeeks
        // int nrWeeks = Integer.parseInt(string2);
        //     // priceOfGuidePerWeek
        // int price = Integer.parseInt(string3);


        // btp.setStartDate(startDate);
        // btp.setNrWeeks(nrWeeks);
        // btp.setPriceOfGuidePerWeek(price)




        throw new io.cucumber.java.PendingException();
    }

    @Then("the system shall raise the error {string} \\(p8)")
    public void the_system_shall_raise_the_error_p8(String string) {
        // Write code here that turns the phrase above into concrete actions









        throw new io.cucumber.java.PendingException();
    }
}
