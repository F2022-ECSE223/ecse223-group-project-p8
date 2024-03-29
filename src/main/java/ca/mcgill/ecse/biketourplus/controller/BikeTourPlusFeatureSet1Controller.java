package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.model.*;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.Persistence.BikeTourPlusPersistence;

import java.util.ArrayList;
import java.util.List;

public class BikeTourPlusFeatureSet1Controller {

  static BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

  /**
   * Update manager account and password so that the account is accurate
   * 
   * @param password the new password that the Manager wants to set
   * @return String of error message (empty string if no error)
   * @author Jacques Zaarour
   * @author Lukas Bebee
   */

  public static String updateManager(String password) {
    var error = "";
    if (!btp.hasManager()) {
      Manager m = new Manager("manager@btp.com", password, btp);
      btp.setManager(m);
    } else {
      btp.getManager().setEmail("manager@btp.com");
    }


    // check for blank password
    if (password.isBlank()) {
      error += "Password cannot be empty";
    }

    // check for the minimum length
    if (password.length() < 4) {
      error += "Password must be at least four characters long";
    }

    // Check if the password contains !,#, or $
    if (!password.contains("!")) {
      if (!password.contains("#")) {
        if (!password.contains("$")) {
          error += "Password must contain one character out of !#$";
        }
      }
    }

    // Check if the password has an upper case letter
    boolean ContainsUppercase = false;
    for (int i = 0; i < password.length(); i++) {
      char ch = password.charAt(i);

      if (Character.isUpperCase(ch)) {
        ContainsUppercase = true;
      }
    }
    if (ContainsUppercase == false) {
      error += "Password must contain one upper-case character";
    }

    // Check if the password has a lower case letter
    boolean ContainsLowercase = false;
    for (int i = 0; i < password.length(); i++) {
      char ch = password.charAt(i);

      if (Character.isLowerCase(ch)) {
        ContainsLowercase = true;
      }
    }
    if (ContainsLowercase == false) {
      error += "Password must contain one lower-case character";
    }

    // Update Password if no errors are found
    if (error.isEmpty()) {
      btp.getManager().setPassword(password);
    } else {
      btp.getManager().setPassword("manager");
    }

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      error += e.getMessage();
    }

    return error;
  }


  /**
   * Get an overview of the bike tours to keep track of the participants' and guides' activities
   *
   * @param id the id of the bike tour the manager wants to view
   * @return a populated TOBikeTour object with all the bike tour information needed
   * @author Jacques Zaarour
   * @author LukeBebee
   * @throws InvalidInputException
   */
  public static TOBikeTour getBikeTour(int id) throws InvalidInputException {

    // The ID is indexed in the list rather than following the actual id of the app (could be a
    // mistake :/)
    id -= 1;

    // basic information attainable from the class diagram
    var startWeek = btp.getBikeTour(id).getStartWeek();
    var endWeek = btp.getBikeTour(id).getEndWeek();
    var numberofWeeks = endWeek - startWeek + 1;
    var guideEmail = btp.getBikeTour(id).getGuide().getEmail();
    var guideName = btp.getBikeTour(id).getGuide().getName();
    var totalCostForGuide = btp.getPriceOfGuidePerWeek() * numberofWeeks;


    // create a list of participants
    List<Participant> participantsList = new ArrayList();
    participantsList.addAll(btp.getBikeTour(id).getParticipants());

    // get attributes of participants
    List<String> participantsEmails = new ArrayList<String>();
    List<String> participantsNames = new ArrayList<String>();
    // List<String> participantsStatus = new ArrayList<String>();
    // List<String> participantsAuth = new ArrayList<String>();
    // List<Integer> participantsRefund = new ArrayList<Integer>();
    for (Participant p : participantsList) {
      participantsEmails.add(p.getEmail());
      participantsNames.add(p.getName());
      // participantsNames.add(p.getTourStatusFullName());
      // participantsAuth.add(p.getAuthorizationCode());
      // participantsRefund.add(p.getRefundedPercentageAmount());
    }


    // add an empty array ot TOParticipantCost in order to accomodate differnet costs for each
    // individual person
    TOParticipantCost[] TOParticipantsArray = new TOParticipantCost[participantsList.size()];


    // add costs to each participant depending on of it is a combo or gear
    // keep in mind combos are discounted if a lodge is requested
    for (int i = 0; i < participantsList.size(); i++) {
      int participantcost = 0;
      var bookedItems = btp.getBikeTour(id).getParticipant(i).getBookedItems();

      List<Gear> Glist = btp.getGear();
      List<Combo> Clist = btp.getCombos();

      for (BookedItem b : bookedItems) {
        if (b.getItem() instanceof Gear) {
          var name = b.getItem().getName();
          for (int j = 0; j < Glist.size(); j++) {
            if (Glist.get(j).getName() == name) {
              participantcost += b.getQuantity() * Glist.get(j).getPricePerWeek();
            }
          }
        } else if (b.getItem() instanceof Combo) {
          float discount = 1;
          int pricefinal = 0;
          var comboName = b.getItem().getName();
          for (int j = 0; j < Clist.size(); j++) {
            if (Clist.get(j).getName() == comboName) {
              if (btp.getBikeTour(id).getParticipant(i).isLodgeRequired()) {
                discount = 1 - ((float) Clist.get(j).getDiscount() / 100);
              }
              for (ComboItem G : Clist.get(j).getComboItems()) {
                pricefinal += G.getQuantity() * G.getGear().getPricePerWeek();
              }
            }
          }
          participantcost += b.getQuantity() * pricefinal * discount;
        }
      }


      String status = "Unset";
      String auth = "Unset";
      int refund = -1;

      status = participantsList.get(i).getTourStatusFullName();
      auth = participantsList.get(i).getAuthorizationCode();
      refund = participantsList.get(i).getRefundedPercentageAmount();

      // TOParticipantCost toParticipants =
      // new TOParticipantCost(participantsEmails.get(i), participantsNames.get(i),
      // participantsStatus.get(i),
      // numberofWeeks * participantcost, numberofWeeks * participantcost + totalCostForGuide,
      // participantsAuth.get(i), participantsRefund.get(i));
      TOParticipantCost toParticipants =
          new TOParticipantCost(participantsEmails.get(i), participantsNames.get(i),
              numberofWeeks * participantcost, numberofWeeks * participantcost + totalCostForGuide,
              participantsList.get(i).getBikeTour().getId(), status, auth, refund);

      TOParticipantsArray[i] = toParticipants;
    }

    // return the ID to the correct value

    id += 1;
    TOBikeTour tobereturned = new TOBikeTour(id, startWeek, endWeek, guideEmail, guideName,
        totalCostForGuide, TOParticipantsArray);


    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      throw new InvalidInputException(e.getMessage());
    }

    return tobereturned;


  }



  /**
   * Populate the TOBikeTourUI for the UI table generation
   * 
   * @param id the id of the bike tour the manager wants to view
   * @return a populated TOBikeTour object with all the bike tour information needed
   * @author Jacques Zaarour
   */

  public static TOBikeTourUI populateTOBikeTourUI(int id) throws InvalidInputException {

    // The ID is indexed in the list rather than following the actual id of the app (could be a
    // mistake :/)
    id -= 1;

    // basic information attainable from the class diagram
    var startWeek = btp.getBikeTour(id).getStartWeek();
    var endWeek = btp.getBikeTour(id).getEndWeek();
    var guideName = btp.getBikeTour(id).getGuide().getName();
    int guideTourCost = btp.getPriceOfGuidePerWeek()
        * (btp.getBikeTour(id).getEndWeek() - btp.getBikeTour(id).getStartWeek() + 1);

    // create a list of participants
    var participantsList = btp.getBikeTour(id).getParticipants();

    // get names of participants
    String participantsNames = "";
    for (Participant p : participantsList) {
      participantsNames += p.getName();
      participantsNames += ", ";
    }

    // return the ID to the correct value

    id += 1;
    TOBikeTourUI tobereturned =
        new TOBikeTourUI(id, guideName, startWeek, endWeek, participantsNames, guideTourCost);

    try {
      BikeTourPlusPersistence.save(BikeTourPlusApplication.getBikeTourPlus());
    } catch (Exception e) {
      throw new InvalidInputException(e.getMessage());
    }

    return tobereturned;
  }


}
