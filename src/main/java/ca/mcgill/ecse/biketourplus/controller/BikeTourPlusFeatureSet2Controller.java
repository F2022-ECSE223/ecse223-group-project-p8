package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;

import java.sql.Date;

// completed by Lukas Bebee (LukeBebee on github)
public class BikeTourPlusFeatureSet2Controller { 

   public static String updateBikeTourPlus(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
      // TODO Implement the method, return error message (if any) 
      
      var error = "";
      
      if (nrWeeks < 0) {
         error = "The number of riding weeks must be greater than or equal to zero";
         System.out.println(error);
         return error;
      }
      
      if (priceOfGuidePerWeek < 0) {
         error = "The price of guide per week must be greater than or equal to zero";
         System.out.println(error);
         return error;
      }
      
      // get current date
      long millis=System.currentTimeMillis();  
      java.sql.Date curDate = new java.sql.Date(millis);

      if (curDate.after(startDate)) {
         error = "The start date cannot be from previous year or earlier";
         System.out.println(error);
         return error;
      }
      
      // If valid (no error returned), apply changes
      BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();
      btp.setStartDate(startDate);
      btp.setNrWeeks(nrWeeks);
      btp.setPriceOfGuidePerWeek(priceOfGuidePerWeek);

      return error;
   }

   public static void deleteParticipant(String email) {
      /* Pseudocode
       * 
       * **First find participant
       *  for each participant p in BTP {
       *    if (p.getEmail().equals(email)) {         ??????? get email
       *      p.delete();                              ???????
       *      return;
       *    }
       *  }
       * 
       * **If not returned, no such participant
       *  
       * 
       */

   }

   // This method is incomplete as we are a team of only five members
   public static void deleteCombo(String name) {
      // This method is incomplete as we are a team of only five members
   }

}