package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.application.*;
import ca.mcgill.ecse.biketourplus.model.*;
import ca.mcgill.ecse.biketourplus.model.Participant.TourStatus;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.controller.BikeTourPlusFeatureSet1Controller;
import ca.mcgill.ecse.biketourplus.controller.TOBikeTour;
import ca.mcgill.ecse.biketourplus.controller.TOParticipantCost;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BikeToursFeatureSetController {

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
     
    public static String payForParticipantTrip(Participant p, String authorizationCode) {
      var error = "";
      
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
      
      if(p.getTourStatus() == TourStatus.Paid || p.getTourStatus() == TourStatus.OnTrip || p.getTourStatus() == TourStatus.TripComplete) {
        error = "The participant has already paid for their tour";
      }
      
      //check if participant cancelled their trip
      //TODO implement check
      
      //check if participant is in the btp system
      if(p.getBikeTourPlus() == null) {
        error = "<error>";
      }
      
      //check for blank authorization code
      if(authorizationCode.isBlank()) {
        error = "<error>";
      }
      
      //need to fix state machine i think
      //TODO call relevant methods in the model
      
      return error;
    }

    public static String startWeekTrips(int weekNumber) {
        return "";
    }

    public static String finishParticipantTrip(Participant p) {
        return "";
    }

    public static String cancelParticipantTrip(Participant p) {
        return "";
    }






}
