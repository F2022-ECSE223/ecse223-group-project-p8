package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import ca.mcgill.ecse.biketourplus.controller.BikeTourPlusFeatureSet4Controller;
import ca.mcgill.ecse.biketourplus.controller.InvalidInputException;
import ca.mcgill.ecse.biketourplus.javafx.fxml.controllers.ViewUtils;
import javafx.event.ActionEvent;

/**
 * @author Ralph Choucha (RalphChoucha on GitHub)
 */

public class FeatureSet4PageController {
  @FXML
  private TextField guideNameField;
  @FXML
  private TextField guideEmailField;
  @FXML
  private TextField contactField;
  @FXML
  private Button registerGuideButton;
  @FXML
  private TextField passwordField;
  @FXML
  private TextField newNameField;
  @FXML
  private TextField guideEmailDField;
  @FXML
  private TextField newPasswordField;
  @FXML
  private TextField newContactField;
  @FXML
  private Button clearSelectionButton;
  @FXML
  private Button saveChangesButton;
  @FXML
  private Button deleteGuideButton;
  @FXML
  private TextField emailUpdateField;


  // Event Listener on Button[#registerGuideButton].onAction
  @FXML
  public void registerGuideClicked(ActionEvent event) {
    if (ViewUtils
        .callController(BikeTourPlusFeatureSet4Controller.registerGuide(guideEmailField.getText(),
            passwordField.getText(), guideNameField.getText(), contactField.getText()))) {
      guideEmailField.clear();
      passwordField.clear();
      guideNameField.clear();
      contactField.clear();
    }

  }

  // Event Listener on Button[#clearSelectionButton].onAction
  @FXML
  public void clearSelectionClicked(ActionEvent event) {
    newNameField.clear();
    newPasswordField.clear();
    newContactField.clear();
    emailUpdateField.clear();
  }

  // Event Listener on Button[#saveChangesButton].onAction
  @FXML
  public void saveChangesClicked(ActionEvent event) {

    if (ViewUtils
        .callController(BikeTourPlusFeatureSet4Controller.updateGuide(emailUpdateField.getText(),
            newPasswordField.getText(), newNameField.getText(), newContactField.getText()))) {

      newNameField.clear();
      newPasswordField.clear();
      newContactField.clear();
      emailUpdateField.clear();
    }
  }

  // Event Listener on Button[#deleteGuideButton].onAction
  @FXML
  public void deleteGuideClicked(ActionEvent event) throws InvalidInputException {

    ViewUtils
        .callController(BikeTourPlusFeatureSet4Controller.deleteGuide(guideEmailDField.getText()));
    guideEmailDField.clear();
  }

}
