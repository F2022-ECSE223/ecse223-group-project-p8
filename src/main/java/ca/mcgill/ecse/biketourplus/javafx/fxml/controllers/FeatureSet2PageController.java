package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import ca.mcgill.ecse.biketourplus.controller.*;

import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FeatureSet2PageController {
  
  @FXML
  private TextField emailParticipantRemoveTextField;

  @FXML
  private TextField newGuidePriceTextField;

  @FXML
  private TextField newNrWeeksTextField;

  @FXML
  private TextField newStartDateTextField;

  @FXML
  private Button updateSeasonButton;


  
  /**
   * This method is called  when the user clicks on remove participant
   * It calls on ViewUtils to show the proper message
   * @param event
   * @author LukeBebee
   */
  @FXML
  void removeParticipantClicked(ActionEvent event) {
    String email = emailParticipantRemoveTextField.getText();
    String error = "";
    try {
      error += BikeTourPlusFeatureSet2Controller.deleteParticipant(email);
    } catch (Exception e) {
      error += e.getMessage();
    }
    ViewUtils.callController(error);
    
  }

  /**
   * This method is called  when the user clicks on updateSeason
   * It calls on ViewUtils to show the proper message
   * @param event
   * @author LukeBebee
   */
  @FXML
  void updateSeasonClicked(ActionEvent event) {

    
    if(newStartDateTextField.getText().equals("")) {
      ViewUtils.makePopupWindow("ERROR", "Enter a start date");
    }else if(newNrWeeksTextField.getText().equals("")) {
      ViewUtils.makePopupWindow("ERROR", "Enter a number of weeks");
    }else if(newGuidePriceTextField.getText().equals("")) {
      ViewUtils.makePopupWindow("ERROR", "Enter a price");
    }else{
      
      try {
        Date date = Date.valueOf(newStartDateTextField.getText()); 
      }catch(Exception e) {
        ViewUtils.makePopupWindow("ERROR", "Invalid Date Entry");
      }
      
      try {
        int weeks = Integer.parseInt(newNrWeeksTextField.getText()); 
      }catch(Exception e) {
        ViewUtils.makePopupWindow("ERROR", "Invalid Number of Weeks Entry");
      }
      
      try {
        int guidePrice = Integer.parseInt(newGuidePriceTextField.getText()); 
      }catch(Exception e) {
        ViewUtils.makePopupWindow("ERROR", "Invalid Price Entry");
      }
      
      try {
        Date date = Date.valueOf(newStartDateTextField.getText());
        int weeks = Integer.parseInt(newNrWeeksTextField.getText()); 
        int guidePrice = Integer.parseInt(newGuidePriceTextField.getText()); 
        ViewUtils.callController(BikeTourPlusFeatureSet2Controller.updateBikeTourPlus(date, weeks, guidePrice));
      }catch(Exception e) {
        ViewUtils.makePopupWindow("ERROR", "ERROR\n" +e);
      }
    }
  }
  
  
}
