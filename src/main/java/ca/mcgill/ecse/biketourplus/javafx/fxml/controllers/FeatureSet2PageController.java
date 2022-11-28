package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import ca.mcgill.ecse.biketourplus.controller.*;
import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import java.sql.Date;
import ca.mcgill.ecse.biketourplus.application.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FeatureSet2PageController {
  BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();
  
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


  @FXML
  void removeParticipantClicked(ActionEvent event) {
    String email = emailParticipantRemoveTextField.getText();
    try {
      BikeTourPlusFeatureSet2Controller.deleteParticipant(email);
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getMessage());
    }
  }

  @FXML
  void updateSeasonClicked(ActionEvent event) {
    Date date = Date.valueOf(newStartDateTextField.getText());
    int weeks = Integer.parseInt(newNrWeeksTextField.getText());
    int guidePrice = Integer.parseInt(newGuidePriceTextField.getText());
    try {
      BikeTourPlusFeatureSet2Controller.updateBikeTourPlus(date, weeks, guidePrice);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
  }
}
