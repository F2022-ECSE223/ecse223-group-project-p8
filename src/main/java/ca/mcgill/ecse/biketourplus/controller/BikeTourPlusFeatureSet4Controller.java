package ca.mcgill.ecse.biketourplus.controller;

import ca.mcgill.ecse.biketourplus.application.*;
import ca.mcgill.ecse.biketourplus.model.*;

/**
 * @author Ralph Choucha (RalphChoucha on GitHub)
 */

public class BikeTourPlusFeatureSet4Controller {
	
	// get instance of the BikeTourPlus from the Application
	static BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();
	
   /**
    * Method that registers a Guide into the BikeTourPlus system. 
    * Checks if input is valid before calling the constructor in BikeTourPlus.
    * @param email = A string that represents the guide's e-mail address
    * @param password = A string that represents the guide's password
    * @param name = A string that represents the guide's name
    * @param emergencyContact = A string that represents the guide's emergency contact
    * @return A string error that describes the input error. Blank if no error has been detected
    */
	
   public static String registerGuide(String email, String password, String name, String emergencyContact) {
      
	  var error = "";
	  
	  //check for blank email
	  if(email.isBlank()) {
		  error = "Email cannot be empty";
	  }
	  
	  //check if email already registered as guide or participant
	  //check guides
	  for(Guide e: btp.getGuides()) {
		 if(e.getEmail().equals(email)){
			 error = "Email already linked to a guide account";
		 }
	  }
	  
	  //check participants
	  for(Participant p: btp.getParticipants()) {
		if(p.getEmail().equals(email)){
			error = "Email already linked to a participant account";
		 }
	  }
	  
	  //check if email isn't "manager@btp.com"
	  if(email.equals("manager@btp.com")) {
		  error = "Email cannot be manager@btp.com";
	  }
	  
	  //check if email has spaces
	  if(email.contains(" ")) {
		  error = "Invalid email";
	  }
	  
	  //check if email doesnt end with "@email.com"
	  if(!email.endsWith("@email.com")) {
		  error = "Invalid email";
	  }
	  
	  //check if email doesn't start with "@email.com"
	  if(email.startsWith("@email.com")) {
		  error = "Invalid email";
	  }
	  
	  //check for blank password
	  if(password.isBlank()) {
		  error = "Password cannot be empty";
	  }
	  
	  //check for blank name
	  if(name.isBlank()) {
		  error = "Name cannot be empty";
	  }
	  
	  //check for blank emergencyContact
	  if(emergencyContact.isBlank()) {
		  error = "Emergency contact cannot be empty";
	  }
	  
	  // call constructor if error is a blank string to create new guide with the parameters specified in the input
	  if(error.isBlank()) {
		 new Guide(email, password, name, emergencyContact, btp); 
	  }
	   
      return error;
   }

   public static String updateGuide(String email, String newPassword, String newName, String newEmergencyContact) {
	  
	  var error = "";
	  
	  //get guide object from the btp instance
	  Guide guideToUpdate = getSpecificGuide(email);
	  
	  //if guideToUpdate is null, no guide is registered with the input email
	  if(guideToUpdate == null) {
		  error = "The guide account does not exist";
	  }
	  else {
		  if(newPassword.isBlank()) {
			  error = "Password cannot be empty";
		  }
		  
		  if(newName.isBlank()) {
			  error = "Name cannot be empty";
		  }
		  if(newEmergencyContact.isBlank()) {
			  error = "Emergency contact cannot be empty";
		  }
	  }
	  
	  if(error.isBlank()) {
		  guideToUpdate.setPassword(newPassword);
		  guideToUpdate.setName(newName);
		  guideToUpdate.setEmergencyContact(newEmergencyContact);
	  }
	  
      return error;
   }

   public static void deleteGuide(String email) {
	   
	   //get specific guide instance
	   Guide guideToDelete = getSpecificGuide(email);
	   
	   //if guide with input email exists, we remove them. Otherwise do nothing
	   if(guideToDelete == null) {
		   btp.removeGuide(guideToDelete);
	   }
   }
   
   //helper method to find a specific guide by email
   //returns a guide object if the method finds a match in the Guide list
   //else returns a null guide if no matches are found
   private static Guide getSpecificGuide(String email) {
	   Guide guide = null;
	   for(Guide g: btp.getGuides()) {
		   if(g.getEmail().equals(email)){
				g = guide;  
		   }
	   }
	   return guide;
   }

}