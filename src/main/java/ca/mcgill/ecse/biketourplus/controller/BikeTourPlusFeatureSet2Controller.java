package ca.mcgill.ecse.biketourplus.controller;

import java.sql.Date;

// completed by Lukas Bebee (LukeBebee on github)
public class BikeTourPlusFeatureSet2Controller { 

   public static String updateBikeTourPlus(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
      // TODO Implement the method, return error message (if any) 
      return "Not implemented yet!";

      /* Pseudocode
       * 
       * **First check if inputs are valid
       *  var error = "";
       * 
       *  if (nrWeeks < 0) {
       *    error = "The number of riding weeks must be greater than or equal to zero";
       *    return error;
       *  }
       * 
       *  if (priceOfGuidePerWeek < 0) {
       *    error = "The price of guide per week must be greater than or equal to zero";
       *    return error;
       *  }
       * 
       *  startYear = startDate.year();     ?????
       *  curYear = currentYear();          ?????
       *  if (startYear < curYear) {
       *    error = "The start date cannot be from previous year or earlier";
       *    return error;
       *  }
       * 
       * 
       * **If valid (no error returned), apply changes
       * 
       *  Attributes of BikeTourPlus object should be changed to input
       * 
       *  return "You have successfully implemented the changes";
       * 
       */
      
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