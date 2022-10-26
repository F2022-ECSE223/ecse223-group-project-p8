package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import ca.mcgill.ecse.biketourplus.model.Participant;
import ca.mcgill.ecse.biketourplus.model.User;
import ca.mcgill.ecse.biketourplus.model.BookedItem;
import ca.mcgill.ecse.biketourplus.model.BookableItem;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

// completed by Lukas Bebee (LukeBebee on github)
public class BikeTourPlusFeatureSet2Controller { 

   /**
    * @param startDate start date of the biking season
    * @param nrWeeks number of weeks the biking season will last
    * @param priceOfGuidePerWeek the weekly cost of a guide
    * @return String of error message (empty string if no error)
    * @author LukeBebee
    */
   public static String updateBikeTourPlus(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
      var error = "";

      // check to ensure the three inputs are possible with given constraints
      if (nrWeeks < 0) { // check number of weeks
         error = "The number of riding weeks must be greater than or equal to zero";
         System.out.println(error);
         return error;
      }
      if (priceOfGuidePerWeek < 0) { // check guide price
         error = "The price of guide per week must be greater than or equal to zero";
         System.out.println(error);
         return error;
      }
      // get current date
      long millis=System.currentTimeMillis();  
      java.sql.Date curDate = new java.sql.Date(millis);

      if (curDate.after(startDate)) { // check start date against current date
         error = "The start date cannot be from previous year or earlier";
         System.out.println(error);
         return error;
      }
      
      // If valid (no error returned), apply changes
      BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();
      btp.setStartDate(startDate);
      btp.setNrWeeks(nrWeeks);
      btp.setPriceOfGuidePerWeek(priceOfGuidePerWeek);

      return error; // return value will be an empty string if any errors detected
   }

   /**
    *  *******Currently still failing tests, not sure why)
    * 
    * 
    * This method removes a participant from BikeTourPlus
    * When doing so, it removes all associations that the participant has
    * @param email String containing email of participant to be removed
    * @author LukeBebee
    */
   public static void deleteParticipant(String email) {
      BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();
      User u = User.getWithEmail(email);
      if (u instanceof Participant) { // Ensure user is a participant
         Participant p = (Participant) u;

         // Manage booked gear 
         List<BookedItem> listOfBookedItems = new LinkedList<BookedItem>(p.getBookedItems()); //use List, not ArrayList
         for (BookedItem gear : listOfBookedItems) {
            BookableItem aBookableItem = gear.getItem();
            aBookableItem.removeBookedItem(gear); // From perspective of instance of BookableItem, remove association with BookedItem

            //p.removeBookedItem(gear); // remove instance of BookedItem from association with the participant (allows us to delete in the next line)
            gear.delete(); // Delete the instance of the association class BookedItem
         }

         btp.removeParticipant(p); // Remove association with instance of BikeTourPlus
         p.delete(); // Delete instance of participant
      }
   }

   // This method is incomplete as we are a team of only five members
   /**
    * @param name
    */
   public static void deleteCombo(String name) {
      // This method is incomplete as we are a team of only five members
   }
}