package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import ca.mcgill.ecse.biketourplus.controller.*;
//import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
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
  private Button removeParticipantButton;

  @FXML
  private Button updateGuidePriceButton;

  @FXML
  private Button updateNrWeeksButton;

  @FXML
  private Button updateStartDateButton;

  @FXML
  void removeParticipantClicked(ActionEvent event) {
    String email = emailParticipantRemoveTextField.getText();
    try {
      BikeTourPlusFeatureSet2Controller.deleteParticipant(email);
    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @FXML
  void updateGuidePriceClicked(ActionEvent event) {
    BikeTourPlusFeatureSet2Controller.updateBikeTourPlus();
  }

  @FXML
  void updateNrWeeksClicked(ActionEvent event) {

  }

  @FXML
  void updateStartDateClicked(ActionEvent event) {

  }
}
