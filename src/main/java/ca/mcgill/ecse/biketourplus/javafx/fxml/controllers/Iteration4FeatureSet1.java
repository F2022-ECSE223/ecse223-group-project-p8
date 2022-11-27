package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import ca.mcgill.ecse.biketourplus.controller.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Iteration4FeatureSet1 {
    // based on IDs in SceneBuilder
    @FXML
    private TextField inputPasswordTextField;
    @FXML
    private Button updatePasswordButton;

    // event listeners on buttons (onAction in SceneBuilder)
    @FXML
    public void updatePasswordClicked(ActionEvent event) {
        BikeTourPlusFeatureSet1Controller.updateManager(inputPasswordTextField.getText());
    }


}