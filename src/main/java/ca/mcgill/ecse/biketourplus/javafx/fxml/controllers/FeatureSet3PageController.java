package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import ca.mcgill.ecse.biketourplus.controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class FeatureSet3PageController {

  @FXML
  private TextField addEmail;
  @FXML
  private Button registerParticipantButton;
  @FXML
  private TextField AddFirstName;
  @FXML
  private TextField AddLastName;
  @FXML
  private TextField addPassword;
  @FXML
  private TextField addEmergencyPhone;
  @FXML
  private TextField addNumberWeeks;
  @FXML
  private CheckBox lodgeRequiredCheck;
  @FXML
  private Button addItemButton;
  @FXML
  private Button RemoveItemButton;
  @FXML
  private TextField addStartweek;
  @FXML
  private TextField addEndWeek;
  @FXML
  private TextField addItemTextField;
  
  // Event Listener on Button[#registerParticipantButton].onAction
  @FXML
  /**
   * This method will register the info of a participant by calling the controller when the user presses
   * the button
   * 
   * @param event - press of the register button
   * @author Jacques Zaarour
   */
  public void RegisterParticipantUI(ActionEvent event) {

    // Get the required parameters from the UI

    String name = AddFirstName.getText() + " " + AddLastName.getText();
    String email = addEmail.getText();
    String password = addPassword.getText();
    String emergency = addEmergencyPhone.getText();
    boolean lodge = lodgeRequiredCheck.isSelected();

    // Check if information entered is not empty
    if (AddFirstName.getText().equals("") || AddLastName.getText().equals("") || email.equals("")|| password.equals("") || emergency.equals("")) {
      ViewUtils.showError("The input fields must not be empty.");
      return;
    }

    // Check if name entered is alphanumeric
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("The input must only contain letters.");
      return;
    }

    int numberOfWeeksWanted;

    // Check if number of weeks entered is an integer
    try {
      numberOfWeeksWanted = Integer.parseInt(this.addNumberWeeks.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of weeks wanted must be an integer");
      return;
    }


    int startweek;

    // Check if startweek entered is an integer
    try {
      startweek = Integer.parseInt(this.addStartweek.getText());
    } catch (Exception e) {
      ViewUtils.showError("The start week must be be an integer");
      return;
    }


    int endweek;

    // Check if endweek entered is an integer
    try {
      endweek = Integer.parseInt(this.addEndWeek.getText());
    } catch (Exception e) {
      ViewUtils.showError("The end week must be an integer");
      return;
    }

    // Try to register the participant

    String error;
    error = BikeTourPlusFeatureSet3Controller.registerParticipant(email, password, name, emergency,
        numberOfWeeksWanted, startweek, endweek, lodge);


    if (error.equals("")){
      ViewUtils.showSuccess("Registration successfully processed for member " + name + "." + '\n');
    }else{
      ViewUtils.showError(error);
    }

    // Catch and output the error if there's one
    return;

  }

  // Event Listener on Button[#updateParticipantButton].onAction
  @FXML
  /**
   * This method will update the info of a participant by calling the controller when the user presses
   * the button
   * 
   * @param event - press of the update button
   * @author Jacques Zaarour
   */
  public void updateParticipantUI(ActionEvent event) {

    // Get the required parameters from the UI
    String name = AddFirstName.getText() + " " + AddLastName.getText();
    String email = addEmail.getText();
    String password = addPassword.getText();
    String emergency = addEmergencyPhone.getText();
    boolean lodge = lodgeRequiredCheck.isSelected();


    // Check if information entered is alphanumeric
    if (!ViewUtils.isAlpha(name)) {
      ViewUtils.showError("The name input must only contain letters.");
      return;
    }

    int numberOfWeeksWanted;

    // Check if information entered is an integer
    try {
      numberOfWeeksWanted = Integer.parseInt(this.addNumberWeeks.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of weeks wanted must be an integer");
      return;
    }

    int startweek;

    // Check if startweek entered is an integer
    try {
      startweek = Integer.parseInt(this.addStartweek.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of weeks wanted must be an integer");
      return;
    }


    int endweek;

    // Check if endweek entered is an integer
    try {
      endweek = Integer.parseInt(this.addEndWeek.getText());
    } catch (Exception e) {
      ViewUtils.showError("The number of weeks wanted must be an integer");
      return;
    }

    // Check if information entered is not empty
    if (AddFirstName.getText().equals("") || AddLastName.getText().equals("")
        || email.equals("") || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input fields must not be empty.");
      return;
    }

    // Try to update the participant

    String error;
    error = BikeTourPlusFeatureSet3Controller.updateParticipant(email, password, name, emergency,numberOfWeeksWanted, startweek, endweek, lodge);


    if (error.equals("")){
      ViewUtils.showSuccess("Registration successfully processed for member " + name + "." + '\n');
    }else{
      ViewUtils.showError(error);
    }

    return;

  }


  // Event Listener on Button[#addItemButton].onAction
  @FXML
  /**
   * This method will add the gear to a participant by adding it to a temp list when the user presses the
   * button
   * 
   * @param event - press of the add button
   * @author Jacques Zaarour
   */
  public void addItem(ActionEvent event) {
      ViewUtils.callController(BikeTourPlusFeatureSet3Controller.addBookableItemToParticipant(addEmail.getText(), addItemTextField.getText()));
  }


  // Event Listener on Button[#removeGears].onAction
  @FXML
  /**
   * Remove the item chosen of the user in register
   * 
   * @param event
   * @author Jacques Zaarour
   */
  public void removeItem(ActionEvent event) {
      ViewUtils.callController(BikeTourPlusFeatureSet3Controller.removeBookableItemFromParticipant(addEmail.getText(), addItemTextField.getText()));
  }


}
