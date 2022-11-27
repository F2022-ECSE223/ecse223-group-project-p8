package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import ca.mcgill.ecse.biketourplus.controller.*;

private class Iteration4FeatureSet1 {
    // based on IDs in SceneBuilder
    @FXML
    private TextField inputPasswordTextField;
    @FXML
    private Button updatePasswordButton;

    // event listeners on buttons (onAction in SceneBuilder)
    @FXML
    public void updatePasswordClicked(ActionEvent event) {
        BikeTourPlusFeatureSet1Controller.updateManager(inputPasswordTextField);
    }


}