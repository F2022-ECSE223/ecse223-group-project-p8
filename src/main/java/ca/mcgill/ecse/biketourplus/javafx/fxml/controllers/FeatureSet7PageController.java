package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import ca.mcgill.ecse.biketourplus.controller.BikeToursFeatureSetController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FeatureSet7PageController {

    @FXML
    private Button beginTourCreationButton;

    @FXML
    void beginTourCreationClicked(ActionEvent event) {
      ViewUtils.callController(BikeToursFeatureSetController.initiateBikeTourCreationProcess());
    }

}
