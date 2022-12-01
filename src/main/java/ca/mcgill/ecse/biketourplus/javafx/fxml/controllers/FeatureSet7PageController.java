package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import ca.mcgill.ecse.biketourplus.controller.BikeToursFeatureSetController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FeatureSet7PageController {

  @FXML
  private Button beginTourCreationButton;

  /**
   * This initiates the bike tour creation when the user clicks the button
   * 
   * @param event
   * @author LukeBebee
   */
  @FXML
  void beginTourCreationClicked(ActionEvent event) {
    ViewUtils.callController(BikeToursFeatureSetController.initiateBikeTourCreationProcess());
  }

}
