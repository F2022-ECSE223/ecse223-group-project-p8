package ca.mcgill.ecse.biketourplus.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateBikeTourPlusStepDefinitions {
    @Given("the following BikeTourPlus system exists: \\(p8)")
    public void the_following_bike_tour_plus_system_exists_p8(
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

    @When("the manager attempts to update the BikeTourPlus information to start date {string}, number of weeks {string}, and price of guide per week {string} \\(p8)")
    public void the_manager_attempts_to_update_the_bike_tour_plus_information_to_start_date_number_of_weeks_and_price_of_guide_per_week_p8(
            String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the BikeTourPlus information shall be start date {string}, number of weeks {string}, and price of guide per week {string} \\(p8)")
    public void the_bike_tour_plus_information_shall_be_start_date_number_of_weeks_and_price_of_guide_per_week_p8(
            String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the system shall raise the error {string} \\(p8)")
    public void the_system_shall_raise_the_error_p8(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
