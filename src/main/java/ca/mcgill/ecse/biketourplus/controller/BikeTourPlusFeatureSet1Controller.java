package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.application.*;
import ca.mcgill.ecse.biketourplus.model.*;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.controller.BikeTourPlusFeatureSet1Controller;
import ca.mcgill.ecse.biketourplus.controller.TOBikeTour;
import ca.mcgill.ecse.biketourplus.controller.TOParticipantCost;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BikeTourPlusFeatureSet1Controller {

  static BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

  /**
   * Update manager account and password so that the account is accurate
   * 
   * @param password the new password that the Manager wants to set
   * @return String of error message (empty string if no error)
   * @author Jacques Zaarour
   */

  public static String updateManager(String password) {
    var error = "";
    // if(btp.getManager().equals(null)){btp.setManager(null);}
    btp.getManager().setEmail("manager@btp.com");


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

    return error;
  }


  /**
   * Get an overview of the bike tours to keep track of the participants' and guides' activities
   *
   * @param id the id of the bike tour the manager wants to view
   * @return a populated TOBikeTour object with all the bike tour information needed
   * @author Jacques Zaarour
   */
  public static TOBikeTour getBikeTour(int id) {

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
    var participantsList = btp.getBikeTour(id).getParticipants();

    // get emails of participants
    List<String> participantsEmails = new ArrayList<String>();
    for (Participant p : participantsList) {
      participantsEmails.add(p.getEmail());
    }

    // get names of participants
    List<String> participantsNames = new ArrayList<String>();
    for (Participant p : participantsList) {
      participantsNames.add(p.getName());
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

      TOParticipantCost toParticipants =
          new TOParticipantCost(participantsEmails.get(i), participantsNames.get(i),
              numberofWeeks * participantcost, numberofWeeks * participantcost + totalCostForGuide);
      TOParticipantsArray[i] = toParticipants;
    }

    // return the ID to the correct value

    id += 1;
    TOBikeTour tobereturned = new TOBikeTour(id, startWeek, endWeek, guideEmail, guideName,
        totalCostForGuide, TOParticipantsArray);

    return tobereturned;
  }
}
