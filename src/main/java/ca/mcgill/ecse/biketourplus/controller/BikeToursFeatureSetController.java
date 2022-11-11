package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.model.*;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.Persistence.BikeTourPlusPersistence;
import java.util.ArrayList;

public class BikeToursFeatureSetController {

  static BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();



  /**
   * This method creates bike tours and assigns guides/participants to them as described in the
   * Iteration3Description
   * 
   * @author RalphChoucha
   * @author LukeBebee
   * @author liu-yuyu
   * @author Morava83
   * @author jacqueszaarour
   * @return A String error. Blank if no error has occurred.
   */
  public static String initiateBikeTourCreationProcess() {
    var error = "";
    try {
      for (Guide guide : btp.getGuides()) {
        for (Participant participant : btp.getParticipants()) {
          if (participant.getTourStatusFullName().equals("NotAssigned")) {

            // check for existing tours
            for (BikeTour guideTour : guide.getBikeTours()) {
              boolean tourMatches = (guideTour.getStartWeek() >= participant.getWeekAvailableFrom())
                  && (guideTour.getEndWeek() <= participant.getWeekAvailableUntil()) && (participant
                      .getNrWeeks() == (guideTour.getEndWeek() - guideTour.getStartWeek()) + 1);
              if (tourMatches) {
                participant.setParticipantTour(guideTour);
                if (participant.getTourStatusFullName().equals("Assigned")) {
                  break;
                }
              }
            }

            // create proposed tours
            for (int i = 0; i <= participant.getWeekAvailableUntil() - participant.getNrWeeks()
                - participant.getWeekAvailableFrom() + 1; i++) {

              if (participant.getTourStatusFullName().equals("Assigned")) {
                break;
              } // added because of check for existing tours above

              int proposedStartWeek = participant.getWeekAvailableFrom() + i;
              int proposedEndWeek = proposedStartWeek + participant.getNrWeeks() - 1;
              // at this point we have our proposed tours, now check for earliest match and if there
              // is one then we need to assign
              boolean conflictWithProposedTour = false;
              for (BikeTour guideTour : guide.getBikeTours()) {
                // loop through all of the guides tours to try and check for bad overlap
                // check for conflict
                if (((proposedStartWeek >= guideTour.getStartWeek())
                    && (proposedStartWeek <= guideTour.getEndWeek()))
                    || ((proposedEndWeek >= guideTour.getStartWeek())
                        && (proposedEndWeek <= guideTour.getEndWeek()))) { // if there is a
                                                                           // different conflict
                  conflictWithProposedTour = true;
                } else if ((proposedStartWeek < guideTour.getStartWeek())
                    && (guideTour.getEndWeek() < proposedEndWeek)) {
                  conflictWithProposedTour = true;
                }
              }
              // if we found that the current proposed tour has no conflicts, then create new tour
              // and assign
              if (!conflictWithProposedTour
                  && participant.getTourStatusFullName().equals("NotAssigned")) {
                BikeTour tourToAssign = btp.addBikeTour(btp.getBikeTours().size() + 1,
                    proposedStartWeek, proposedEndWeek, guide);
                participant.setParticipantTour(tourToAssign);
              }
              if (participant.getTourStatusFullName().equals("Assigned")) {
                break;
              }
            }
          }
        }
      }
      // for loop to see if any participants not assigned
      for (Participant p : btp.getParticipants()) {
        if (p.getTourStatusFullName().equals("NotAssigned")) {
          error += "At least one participant could not be assigned to their bike tour";
          break;
        }
      }
    } catch (Exception e) {
      error += e.getMessage();
    }
    // Persistence
    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      error += (e.getMessage());
    }
    return error;
  }

  /**
   * Method that handles payment for a participant
   * 
   * @param email The email of the participant that needs payment handling
   * @param authCode The authorization code for the transaction
   * @return A String error. Blank if no error has occurred.
   * @author Ralph Choucha (RalphChoucha on Github)
   * @author LukeBebee
   */


  public static String payForParticipantTrip(String email, String authCode) {
    var error = "";

    // get specific participant
    Participant p = getSpecificParticipant(email);

    // call payForTrip and check for errors
    // check first if p is null and return appropriate error
    if (p == null) {
      error = "Participant with email address " + email + " does not exist";
    } else if (authCode.isBlank()) {
      error = "Invalid authorization code";
    } else {
      try {
        p.payForTrip();
        p.setAuthorizationCode(authCode);
      } catch (RuntimeException e) {
        error += e.getMessage();
      }
    }

    // Persistence
    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      error += (e.getMessage());
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

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      error += (e.getMessage());
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

    // check if p is null and return appropriate error
    if (p == null) {
      error = "Participant with email address " + email + " does not exist";
    } else {
      try {
        p.finishTripForParticipant();
      } catch (RuntimeException e) {
        error += e.getMessage();
      }
    }

    // Persistence
    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      error += (e.getMessage());
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


    // check if p is null and return the appropriate message
    if (p == null) {
      error = "Participant with email address " + email + " does not exist";
    } else {
      try {
        p.cancelTripForParticipant();
      } catch (RuntimeException e) {
        error += e.getMessage();
      }
    }

    // Persistence
    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      error += (e.getMessage());
    }
    return error;
  }

  /**
   * Helper method to find a specific Participant object by email
   * 
   * @param email A string that represents the specific participant's email
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
