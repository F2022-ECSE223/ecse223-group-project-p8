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

  /*
   * TODO finish the method Unsure whether we need a try catch block or if we can throw the error
   * normally Unsure whether we assign a unique id to each new biketour created
   * 
   * @author Ralph Choucha (RalphChoucha on GitHub)
   */
  public static String initiateBikeTourCreationProcess() {
    var error = "";
    try {

    

    List<Participant> participants = btp.getParticipants();
    List<Guide> guides = btp.getGuides();
    try {
      // looping through participants using int j
      for (int j = 0; j < participants.size(); j++) {
        // if there are no biketours create a new one using the first participant
        if (btp.getBikeTours().size() == 0) {
          participants.get(j)
              .setBikeTour(new BikeTour(0, participants.get(j).getWeekAvailableFrom(),
                  participants.get(j).getWeekAvailableUntil(), null, btp));
        }
        // Otherwise if there are biketours already created
        // looping through existing biketours using int k
        for (int k = 0; k < btp.getBikeTours().size(); k++) {
          // if an existing biketour has same startWeek and endWeek as a participant's availability
          // add the participant to that specific biketour
          if (btp.getBikeTours().get(k).getStartWeek() == participants.get(j).getWeekAvailableFrom()
              && btp.getBikeTours().get(k).getEndWeek() == participants.get(j)
                  .getWeekAvailableUntil()) {
            participants.get(j).setBikeTour(btp.getBikeTours().get(k));
          }
          // otherwise if there are no matches create new biketour with id equal to the next
          // available index
          else {
            participants.get(j)
                .setBikeTour(new BikeTour(btp.getBikeTours().size(),
                    participants.get(j).getWeekAvailableFrom(),
                    participants.get(j).getWeekAvailableUntil(), null, btp));
          }
        }
      }

      // looping through guides to assign them to the Biketours created
      for (Guide g : guides) {
        // looping through the BikeTours that exist
        for (BikeTour b : btp.getBikeTours()) {
          // if the guide does not have any BikeTours and vice-versa
          // assign them to the earliest created BikeTour
          if (g.getBikeTours().size() == 0 && b.getGuide() == null) {
            b.setGuide(g);
          }
          //initialize boolean overlap that gets set to true if we detect any overlap with any of the biketours the guide is already assigned to
          boolean overlap = false;
          //loop through the list of biketours that the guide has
          for(BikeTour bT: g.getBikeTours()) {
            //if the start week matches the end week (either way around) there is overlap
            if(bT.getStartWeek() == b.getEndWeek() || bT.getEndWeek()== b.getStartWeek()) {
              overlap = true;
            }
            //check case where b's startweek is in between bT's start and end week
            if(bT.getStartWeek() < b.getStartWeek() && b.getStartWeek() < bT.getEndWeek() && b.getEndWeek() <= bT.getEndWeek()) {
              overlap = true;
            }
            //check case where bT's startweek is in between b's start and end week
            if(bT.getStartWeek() >= b.getStartWeek() && bT.getStartWeek() < b.getEndWeek() && b.getEndWeek() < bT.getEndWeek()) {
              overlap = true;
            }
            //if no overlap, set guide
            if(overlap == false) {
              b.setGuide(g);
            }
          }
        }
      }
    } catch (RuntimeException e) {
      error += e.getMessage();
    }
  }
  catch(Exception e) {

  }
    return error;
  }

  /**
   * Method that handles payment for a participant
   * 
   * @param p The participant that needs payment handling
   * @param authorizationCode The authorization code for the transaction
   * @return A String error. Blank if no error has occurred.
   * @author Ralph Choucha (RalphChoucha on Github)
   * @author LukeBebee
   */

  // TODO Fix the state machine so that this method can function and be completed

  public static String payForParticipantTrip(String email, String authCode) {
    var error = "";

    // get specific participant
    Participant p = getSpecificParticipant(email);

    // call payForTrip and check for errors
    try {
      if (p.getAuthorizationCode().equals(authCode)) {
        p.payForTrip();
      }
      else {
        error = "Invalid authorization code";
      }
    } catch (RuntimeException e) {
      error += e.getMessage();
    }
    return error;
  }

  /**
   * Method that starts the BikeTour trips for Participants on a specified week
   * 
   * @param weekNumber The specific week that needs to be started
   * @return A String error. Blank if no error has occurred.
   * @author Ralph Choucha (RalphChoucha on Github)
   */

  public static String startWeekTrips(int weekNumber) {

    ArrayList<Participant> participantForWeek = new ArrayList<Participant>();
    var error = "";

    // loop through all BikeTours for the specified week and add the involved participants into a
    // list
    for (BikeTour b : btp.getBikeTours()) {
      if (b.getStartWeek() == weekNumber) {
        participantForWeek.addAll(b.getParticipants());
      }
    }

    // loop through the participants in the list and check for errors
    for (Participant p : participantForWeek) {
      try {
        p.startTripForParticipant();
      } catch (RuntimeException e) {
        error += e.getMessage();
      }
    }
    return error;
  }

  /**
   * Method used to mark the end of a participant's trip
   * 
   * @param email The specific email of the participant
   * @return A String error. Blank if no error has occurred.
   * @author Ralph Choucha (RalphChoucha on Github)
   */
  public static String finishParticipantTrip(String email) {
    var error = "";
    // get participant
    Participant p = getSpecificParticipant(email);
    try {
      p.finishTripForParticipant();
    } catch (RuntimeException e) {
      error += e.getMessage();
    }
    return error;
  }

  /**
   * Method that cancels trip for the specified participant using their email
   * 
   * @param email The specific email of the participant
   * @return A String error. Blank if no error has occurred.
   * @author Ralph Choucha (RalphChoucha on Github)
   */

  public static String cancelParticipantTrip(String email) {

    var error = "";
    // get participant
    Participant p = getSpecificParticipant(email);

    try {
      p.cancelTripForParticipant();
    } catch (RuntimeException e) {
      error += e.getMessage();
    }
    return error;
  }

  /**
   * Helper method to find a specific Participant object by email
   * 
   * @param email = A string that represents the specific participant's email
   * @return The specific Participant object. If the method doesn't find a match in the Guide list
   *         returns a null Participant object
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
