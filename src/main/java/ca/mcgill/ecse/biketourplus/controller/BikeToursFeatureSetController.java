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
      
      //if all conditions met call payForTrip method in the model
      p.payForTrip();
      
      return error;
    }
      
    /**
     * Method that starts the BikeTour trips for Participants on a specified week 
     * @param weekNumber The specific week that needs to be started
     * @return A String error. Blank if no error has occurred.
     * @author Ralph Choucha (RalphChoucha on Github)
     */
      
    public static String startWeekTrips(int weekNumber) {
      
      ArrayList <Participant> participantForWeek = new ArrayList <Participant>();
      var error = "";
      
      //loop through all BikeTours for the specified week and add the involved participants into a list
      for(BikeTour b: btp.getBikeTours()) {
        if(b.getStartWeek() == weekNumber) {
          participantForWeek.addAll(b.getParticipants());
        }
      }
      
      //loop through the participants in the list and check for errors
      for(Participant p: participantForWeek) {
        
        //check if tour already started for the participant
        if(p.getTourStatus() == TourStatus.Started) {
          error = "Cannot start tour because the participant has already started their tour";
          return error;
        }
        
        //check if participant is banned
        if(p.getTourStatus() == TourStatus.Banned) {
          error = "Cannot start tour because the participant is banned";
          return error;
        }
        
        //check if participant has cancelled trip
        if(p.getTourStatus() == TourStatus.Cancelled) {
          error = "Cannot start tour because the participant has cancelled their tour";
          return error;
        }
        
        //check if participant finished their trip
        if(p.getTourStatus() == TourStatus.Finished) {
          error = "Cannot start tour because the participant has finished their tour";
          return error;
        }
        
        //if participant is clear, call startTripForParticipant method in the model
        p.startTripForParticipant();
      }
      return error;
    }

    public static String finishParticipantTrip(String email) {
        return "";
    }
    
    /**
     * Method that cancels trip for the specified participant using their email
     * @param email The specific email of the participant
     * @return A String error. Blank if no error has occurred.
     * @author Ralph Choucha (RalphChoucha on Github)
     */
    
    public static String cancelParticipantTrip(String email) {
        
        var error = "";
        //get participant
        Participant p = getSpecificParticipant(email);
        
        //if participant does not exist
        if(p == null) {
          error = "Participant with email address "+ email +" does not exist";
        }
        
        //if participant has already canceled
        if(p.getTourStatus() == TourStatus.Cancelled) {
          error = "Cannot cancel tour because the participant has already cancelled their tour";
        }
        
        //if participant is banned
        if(p.getTourStatus() == TourStatus.Banned) {
          error = "Cannot cancel tour because the participant is banned";
        }
        
        //if participant finished their tour
        if(p.getTourStatus() == TourStatus.Finished) {
          error = "Cannot cancel tour because the participant has finished their tour";
        }
        
        //if participant is good to go call cancelTripForParticipant method in the model
        p.cancelTripForParticipant();
        
        return error;
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
