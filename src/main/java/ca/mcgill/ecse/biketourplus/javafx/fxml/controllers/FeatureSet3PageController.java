package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import ca.mcgill.ecse.biketourplus.controller.*;
import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import ca.mcgill.ecse.biketourplus.model.BookableItem;
import ca.mcgill.ecse.biketourplus.controller.InvalidInputException;


import java.util.ArrayList;
import java.util.List;


import ca.mcgill.ecse.biketourplus.application.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import ca.mcgill.ecse.biketourplus.model.ComboItem;
import ca.mcgill.ecse.biketourplus.model.BikeTour;
import ca.mcgill.ecse.biketourplus.model.Gear;
import ca.mcgill.ecse.biketourplus.model.Combo;
import ca.mcgill.ecse.biketourplus.model.Participant;
import ca.mcgill.ecse.biketourplus.model.User;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public class FeatureSet3PageController {
  BikeTourPlus btp = BikeTourPlusApplication.getBikeTourPlus();

  @FXML
  private Tab registerParticipantTab;
  @FXML
  private TextField addEmail;
  @FXML
  private Button registerParticipantButton;
  @FXML
  private TextField addFirstName;
  @FXML
  private TextField addLastName;
  @FXML
  private TextField addPassword;
  @FXML
  private TextField addEmergencyPhone;
  @FXML
  private TextField addNumberWeeks;
  @FXML
  private CheckBox lodgeRequiredCheck;
  @FXML
  private Button addGearButton;
  @FXML
  private Button addComboButton;
  @FXML
  private ComboBox<String> addedGearsList = new ComboBox<String>();
  @FXML
  private ComboBox<String> addedCombosList = new ComboBox<String>();
  @FXML
  private ListView<Label> listOfGearsChosen = new ListView<Label>();
  @FXML
  private ListView<Label> listOfNumberOfGearsChosen = new ListView<Label>();
  @FXML
  private Button removeGears;
  @FXML
  private TextField participantEmail;
  @FXML
  private TextField addStartweek;
  @FXML
  private TextField addEndWeek;
  @FXML
  private Tab deleteParticipantTab;
  @FXML
  private Button deleteParticipantButton;
  @FXML
  private TextField toBedeletedParticipantEmail;

  private static BikeTourPlus system = BikeTourPlusApplication.getBikeTourPlus();// The system instance

  // For Registering
  private List<String> bookedItemsToAdd = new ArrayList<>();
  private List<Integer> numberOfItemsToAdd = new ArrayList<>();
  private List<BookableItem> allBookedItemsList = new ArrayList<>();
  
  // For Updating
  private List<String> bookedGearsToUpdate = new ArrayList<>();
  private List<Integer> numberOfGearsToUpdate = new ArrayList<>();
  private List<BookableItem> updateAllBookedGearsList = new ArrayList<>();


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

    String name = addFirstName.getText() + " " + addLastName.getText();
    String email = addEmail.getText();
    String password = addPassword.getText();
    String emergency = addEmergencyPhone.getText();
    boolean lodge = lodgeRequiredCheck.isSelected();


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
    if (addFirstName.getText().equals("") || addLastName.getText().equals("") || email.equals("")
        || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input fields must not be empty.");
      return;

    }

      // Try to register the participant

      String error;
      error = BikeTourPlusFeatureSet3Controller.registerParticipant(email, password, name, emergency,
          numberOfWeeksWanted, startweek, endweek, lodge);


      // Clear the temporary lists for the next customer
      bookedItemsToAdd.clear();
      numberOfItemsToAdd.clear();
      allBookedItemsList.clear();


      // Clear the selected gears in the table
      refreshListViewString(listOfGearsChosen, bookedItemsToAdd);
      refreshListViewInteger(listOfNumberOfGearsChosen, numberOfItemsToAdd);

      clearFields();

      // Catch and output the error if there's one
  
      ViewUtils.showError(error);
      return;
  }

  // Event Listener on Button[#addGearButton].onAction
  @FXML
  /**
   * This method will add the gear to a participant by adding it to a temp list when the user presses the
   * button
   * 
   * @param event - press of the add button
   * @author Jacques Zaarour
   */
  public void addGear(ActionEvent event) {

    int numberOfItemWanted = 1;

    // Get the gear chosen from the name chosen in the comboBox
    BookableItem item = BookableItem.getWithName(addedGearsList.getValue());

    // Check that the participant chose a gear.
    if (!(item == null)) {
      for (String eqgear : bookedItemsToAdd) {
        if (eqgear.equals(item.getName())) {
          ViewUtils.showError("You already selected this gear.");
          return;
        }
      }
      // Add the name of the gear to the list
      bookedItemsToAdd.add(item.getName());

      // Add the equipment to the list
      allBookedItemsList.add((Gear) item);

      // Add the number of equipment requested by the participant
      numberOfItemsToAdd.add(numberOfItemWanted);

      // Refresh the list of gears chosen
      refreshListViewString(listOfGearsChosen, bookedItemsToAdd);
      refreshListViewInteger(listOfNumberOfGearsChosen, numberOfItemsToAdd);


      // The participant clicked on add without selecting any gear
    } else {
      ViewUtils.showError("You have to select an gear to add");
      return;

    }

  }

  // Event Listener on Button[#addComboButton].onAction
  @FXML
  /**
   * This method will add the combo to a participant by adding it to a temp list when the user presses
   * the button
   * 
   * @param event - press of the add button
   * @author Jacques Zaarour
   */
  public void addCombo(ActionEvent event) {

    int numberOfComboWanted = 1;



    // Get the name of the item chosen
    BookableItem combo = BookableItem.getWithName(addedCombosList.getValue());

    // Check thats the participant chose a combo.
    if (!(combo == null)) {
      for (String eqitem : bookedItemsToAdd) {
        if (eqitem.equals(combo.getName())) {
          ViewUtils.showError("You already selected this item.");
          return;
        }
      }
      // Add the name of the item to the list
      bookedItemsToAdd.add(combo.getName());

      // Add the equipment combo to the list
      allBookedItemsList.add((Combo) combo);

      // Add the number of equipment requested by the participant
      numberOfItemsToAdd.add(numberOfComboWanted);

      // Refresh the list of combo chosen
      refreshListViewString(listOfGearsChosen, bookedItemsToAdd);
      refreshListViewInteger(listOfNumberOfGearsChosen, numberOfItemsToAdd);

      // The participant clicked on add without selecting any combo
    } else {
      ViewUtils.showError("You have to select a combo to add");
      return;

    }

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
    String name = addFirstName.getText() + " " + addLastName.getText();
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
    if (addFirstName.getText().equals("") || addLastName.getText().equals("")
        || email.equals("") || password.equals("") || emergency.equals("")) {

      ViewUtils.showError("The input fields must not be empty.");
      return;
    }

    // Try to update the participant

    String error;
    error = BikeTourPlusFeatureSet3Controller.updateParticipant(email, password, name, emergency,numberOfWeeksWanted, startweek, endweek, lodge);

    // Clear the temporary lists for the next customer
    bookedItemsToAdd.clear();
    numberOfItemsToAdd.clear();
    allBookedItemsList.clear();


    // Clear the selected gears in the table
    refreshListViewString(listOfGearsChosen, bookedItemsToAdd);
    refreshListViewInteger(listOfNumberOfGearsChosen, numberOfItemsToAdd);

    clearFields();

    // Catch and output the error if there's one

    ViewUtils.showError(error);
    return;

  }

  // Event Listener on Button[#deleteMemberButton].onAction
  @FXML
  /**
   * This method will delete the member by calling the controller when the user presses the button
   * 
   * @param event - press of the delete button
   * @author Jacques Zaarour
   */
  public void deleteMemberUI(ActionEvent event) throws InvalidInputException {

    // Get the required parameters from the UI
    String email = addEmail.getText();

    // Check if information entered is not empty
    if (email.equals("")) {

      ViewUtils.showError("The email field must not be empty.");
      return;

    }

    // Return the User with the associated email
    User toBeDeleted = User.getWithEmail(email);

    // If the user exists and is a member
    if (toBeDeleted != null && toBeDeleted instanceof Participant) {

      // Delete the member with the select email
      BikeTourPlusFeatureSet2Controller.deleteParticipant(email);
      ViewUtils.showSuccess("Successfully deleted the member with email " + email);
      addEmail.clear();
    } else {
      ViewUtils.showError("The member with email " + email + " does not exist.");
      return;
    }
  }

  // Event Listener on ListView[#listOfItemsChosen].onMouseClicked
  @FXML
  /**
   * Select the item chosen of the user in register
   * 
   * @param event
   * @author Jacques Zaarour
   */
  public void selectItem(MouseEvent event) {
    listOfNumberOfGearsChosen.getSelectionModel()
        .select(listOfNumberOfGearsChosen.getSelectionModel().getSelectedIndex());
  }

// Event Listener on Button[#removeGears].onAction
@FXML
/**
 * Remove the item chosen of the user in register
 * 
 * @param event
 * @author Jacques Zaarour
 */
public void removeItemsFromChosen(ActionEvent event) {

  // Check that the user chose an item
  if (listOfGearsChosen.getSelectionModel().isEmpty()) {
    ViewUtils.showError("Please select an item to delete.");
    return;
  }

  // Remove the selected item and its quantity
  bookedItemsToAdd.remove(listOfGearsChosen.getSelectionModel().getSelectedIndex());

  // Refresh the lists of items
  refreshListViewString(listOfGearsChosen, bookedItemsToAdd);
}


  /**
    * Refresh the list of names
    * 
    * @param listView
    * @param names
    * @author Jacques
    */
  private void refreshListViewString(ListView<Label> listView, List<String> names) {

    // Clear the list
    listView.getItems().clear();

    for (String string : names) {

      // Add the names to the selected list
      listView.getItems().add(new Label(string));
    }

    listView.refresh();
  }

  /**
   * Refresh the list of quantities
   * 
   * @param listView
   * @param quantities
   * @author Jacques Zaarour
   */
  private void refreshListViewInteger(ListView<Label> listView, List<Integer> quantities) {

    // Clear the list
    listView.getItems().clear();

    for (Integer ints : quantities) {

      // Add the quantities to the selected list
      listView.getItems().add(new Label(String.valueOf(ints)));
    }

    listView.refresh();
    }

  /**
   * Private helper method that Clear all fields in the register Tab
   * 
   * @author Jacques Zaarour
   */
  private void clearFields() {
    addFirstName.clear();
    addLastName.clear();
    addEmail.clear();
    addPassword.clear();
    addEmergencyPhone.clear();
    lodgeRequiredCheck.disarm();
    addNumberWeeks.setText("");
    addStartweek.setText("");
    addEndWeek.setText("");
  }

  /**
   * Set the prompt tex in the combobox
   * 
   * @param box
   * @param prompt
   * @author Jacques Zaarour
   */
  private void setPromptText(ComboBox<String> box, String prompt) {
    box.setPromptText(prompt);
  }

  /**
   * Refresh everything that appears on the UI
   * 
   * @author Jacques Zaarour
   */
public void initialize() {

    // Get the name of items and bundles from the system
    List<String> gearsList = getNameOfGears();
    List<String> combosList = getNameOfCombos();

    // If there's items in the system, set them to the comboBoxes and set the prompt text
    // appropriately
    if (gearsList.size() > 0) {

      addedGearsList.setItems(FXCollections.observableList(gearsList));

      setPromptText(addedGearsList, "Available items");
    }

    // If no items in system, clear the names and set the prompt text appropriately
    else {

      try {
        // Clear the names of the items in the boxes
        addedGearsList.getItems().clear();

        setPromptText(addedGearsList, "No items in system");

      }
      // Catch null pointer exception if initial has no items because cannot clear a empty list
      catch (Exception e) {
        setPromptText(addedGearsList, "No items in system");
      }
    }


    // If there's bundle in the system, set them to the comboBoxes and set the prompt text
    // appropriately
    if (combosList.size() > 0) {
      addedCombosList.setItems(FXCollections.observableList(gearsList));

      setPromptText(addedCombosList, "Available items");

    }
    // If no bundles in system, clear the names and set the prompt text appropriately
    else {

      try {
        // Clear the names of the bundles in the boxes
        addedCombosList.getItems().clear();

        setPromptText(addedCombosList, "No items in system");

      }
      // Catch null pointer exception if initial has no items because cannot clear a empty list
      catch (Exception e) {
        setPromptText(addedCombosList, "No items in system");
      }
    }
  }

    /**
   * private helper method to return the names of the items in the system
   *
   * @author Jacques Zaarour
   * @return
   */
  private List<String> getNameOfGears() {

    // refresh the system instance
    system = BikeTourPlusApplication.getBikeTourPlus();

    List<String> listOfNames = new ArrayList<>();

    // Get all equipment from the system
    for (Gear item : system.getGear()) {

      // add the names of the items to the list
      listOfNames.add(item.getName());

    }
    return listOfNames;
  }

  /**
   * private helper method to return the names of the bundles in the system
   * 
   * @author Jacques Zaarour
   * @return listOfNames - the list of names of the bundles
   */
  private List<String> getNameOfCombos() {

    // refresh the system instance
    system = BikeTourPlusApplication.getBikeTourPlus();

    List<String> listOfNames = new ArrayList<>();

    // Get all equipment from the system
    for (Combo bundle : system.getCombos()) {

      // add the names of the bundles to the list
      listOfNames.add(bundle.getName());

    }
    return listOfNames;
  }

}
