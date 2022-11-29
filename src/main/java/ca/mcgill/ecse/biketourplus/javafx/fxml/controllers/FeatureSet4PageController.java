package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import ca.mcgill.ecse.biketourplus.controller.BikeTourPlusFeatureSet4Controller;
import javafx.event.ActionEvent;

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
  private TextField emailSearchField;
  @FXML
  private Button searchButton;

  // Event Listener on Button[#registerGuideButton].onAction
  @FXML
  public void registerGuideClicked(ActionEvent event) {
    try {
      BikeTourPlusFeatureSet4Controller.registerGuide(guideEmailField.getText(),
          passwordField.getText(), guideNameField.getText(), contactField.getText());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    guideEmailField.clear();
    passwordField.clear();
    guideNameField.clear();
    contactField.clear();
  }

  // Event Listener on Button[#clearSelectionButton].onAction
  @FXML
  public void clearSelectionClicked(ActionEvent event) {
    newNameField.clear();
    newPasswordField.clear();
    newContactField.clear();
    emailSearchField.clear();
  }

  // Event Listener on Button[#saveChangesButton].onAction
  @FXML
  public void saveChangesClicked(ActionEvent event) {
    try {
      BikeTourPlusFeatureSet4Controller.updateGuide(emailSearchField.getText(),
          newPasswordField.getText(), newNameField.getText(), newContactField.getText());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    newNameField.clear();
    newPasswordField.clear();
    newContactField.clear();
    emailSearchField.clear();
  }

  // Event Listener on Button[#deleteGuideButton].onAction
  @FXML
  public void deleteGuideClicked(ActionEvent event) {
    try {
      BikeTourPlusFeatureSet4Controller.deleteGuide(guideEmailDField.getText());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    guideEmailDField.clear();
  }

  // Event Listener on Button[#searchButton].onAction
  @FXML
  public void searchButtonClicked(ActionEvent event) {
    if (emailSearchField.getText().isBlank()) {
      JOptionPane.showMessageDialog(null, "Cannot search if field is blank", "ERROR",
          JOptionPane.ERROR_MESSAGE);
      // TODO Complete the method
    }
  }
}
