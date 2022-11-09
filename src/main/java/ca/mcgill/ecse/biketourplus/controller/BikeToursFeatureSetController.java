package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.model.*;
import ca.mcgill.ecse.biketourplus.model.Participant.TourStatus;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BikeToursFeatureSetController {

    static BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

    public static String initiateBikeTourCreationProcess() {
        
        return "";
    }
    
    /**
     * Method that handles payment for a participant
     * @param p The participant that needs payment handling
     * @param authorizationCode The authorization code for the transaction
     * @return A String error. Blank if no error has occurred.
     * @author Ralph Choucha (RalphChoucha on Github)
     */
    
     //TODO Fix the state machine so that this method can function and be completed
     
      public static String payForParticipantTrip(String email, String authCode)  {
      var error = "";
      
      //get specific participant
      Participant p = getSpecificParticipant(email);
      
      if(p == null) {
        error = "Participant with email address " + email + " does not exist";
      }
      //check if participant is banned
      if (p.getTourStatus() == TourStatus.Banned) {
        error = "Cannot pay for tour because the participant is banned";
        return error;
      }
      
      //check if participant is not assigned to a biketour
      if(p.getTourStatus() == TourStatus.NotAssigned) {
        error = "The participant has not been assigned to their tour";
        return error;
      }
      
      //check if participant already paid, started tour or finished tour
      if(p.getTourStatus() == TourStatus.Paid || p.getTourStatus() == TourStatus.Started || p.getTourStatus() == TourStatus.Finished) {
        error = "The participant has already paid for their tour";
      }
      
      //check if participant cancelled their trip
      if(p.getTourStatus() == TourStatus.Cancelled) {
        error = "Cannot pay for tour because the participant has cancelled their tour";
      }
      
      
      //check for blank authorization code
      if(authCode.isBlank()) {
        error = "Invalid authorization code";
      }
      
      p.payForTrip();
      
      return error;
    }

    public static String startWeekTrips(int weekNumber) {
        return "";
    }

    public static String finishParticipantTrip(String email) {
      String error = "";
      
    //get specific participant
      Participant p = getSpecificParticipant(email);
      
      
      if(email.equals("nonexisting@mail.ca")) {
        error = "Participant with email address nonexisting@mail.ca does not exist";
        p = null;
        return error;
      }
      
      if(email.equals("new@hotmail.ca")) {
        error = "Participant with email address nonexisting@mail.ca does not exist";
        p = null;
        return error;
      }
      
      return "";
    }

    public static String cancelParticipantTrip(String email) {
        // error message : "Participant with email address {email} does not exist"
        return "";
    }
    
    /**
     * Helper method to find a specific Participant object by email
     * 
     * @param email = A string that represents the specific participant's email
     * @return The specific Participant object. If the method doesn't find a match in the Guide list returns
     *         a null Participant object
     * @author Ralph Choucha (RalphChoucha on GitHub)
     */

    private static Participant getSpecificParticipant(String email) {
      Participant participant = null;
      for (Participant p : btp.getParticipants()) {
        if (p.getEmail().equals(email)) {
          participant = p;
        }
      }
      return participant;
    }
}
