package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import ca.mcgill.ecse.biketourplus.controller.*;

public class FeatureSet8PageController {
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField authCodeTextField;
	@FXML
	private TextField totalPriceTextField;
	@FXML
	private Button payButton;
	@FXML
	private Button calculateButton;

	// Event Listener on Button[#payButton].onAction
	@FXML
	public void payClicked(ActionEvent event) {
	  if(emailTextField.getText().equals("")) {
	    //JOptionPane.showMessageDialog(null, "Please enter an email","ERROR",JOptionPane.ERROR_MESSAGE);
	    ViewUtils.makePopupWindow("ERROR","Please enter an email");
	  }else {
	    String msg = BikeToursFeatureSetController.payForParticipantTrip(emailTextField.getText(),authCodeTextField.getText());
	    if(msg.equals("Participant with email address " + emailTextField.getText() + " does not exist")) {
	      //JOptionPane.showMessageDialog(null, "Participant with email address " + emailTextField.getText() + " does not exist","ERROR",JOptionPane.ERROR_MESSAGE);
	      ViewUtils.makePopupWindow("ERROR","Participant with email address " + emailTextField.getText() + " does not exist");
	      }else if(msg.equals("Invalid authorization code")) {
	          //JOptionPane.showMessageDialog(null, "Please enter an authorization code","ERROR",JOptionPane.ERROR_MESSAGE);
	          ViewUtils.makePopupWindow("ERROR","Please enter an authorization code");
	      }else if(msg.equals("")){
	        //JOptionPane.showMessageDialog(null,"Your payment was processed","SUCCESS",JOptionPane.PLAIN_MESSAGE);
	        ViewUtils.makePopupWindow("SUCCESS","Your payment was processed");
	      } 
	  }
	  emailTextField.clear();
	  authCodeTextField.clear();
	}
	// Event Listener on Button[#calculateButton].onAction
	@FXML
	public void calculateClicked(ActionEvent event) {
	  int id = -1;
	  boolean flag = false;
	  try {
	    id = BikeToursFeatureSetController.getBikeTourIdParticipant(emailTextField.getText());  
	  }catch(Exception e) {
	    ViewUtils.makePopupWindow("ERROR","Participant with email: " + emailTextField.getText() + " has not been registered for a bike tour");
	    emailTextField.clear();
	    authCodeTextField.clear();
	    flag = true;
	  }
	  
	  if(id != -1) {
	    try {
	        TOBikeTour bikeTourTO = BikeTourPlusFeatureSet1Controller.getBikeTour(id);
	        int indexOfParticipant = BikeToursFeatureSetController.getParticipantIndex(emailTextField.getText());
	        int costCents = bikeTourTO.getParticipantCost(indexOfParticipant).getTotalCostForBikingTour();
	        totalPriceTextField.setText("$" + Double.toString(costCents/100.));
	      } catch (InvalidInputException e) {
	        //JOptionPane.showMessageDialog(null, "Invalid participant email","ERROR",JOptionPane.ERROR_MESSAGE);
	        ViewUtils.makePopupWindow("ERROR","Invalid participant email");
	        emailTextField.clear();
	        authCodeTextField.clear();
	      } 
	  }else if(!flag) {
	    //JOptionPane.showMessageDialog(null, "Invalid participant email","ERROR",JOptionPane.ERROR_MESSAGE);
	    ViewUtils.makePopupWindow("ERROR","Invalid participant email");
	    emailTextField.clear();
	    authCodeTextField.clear();
	  }
  	  
	}
}
